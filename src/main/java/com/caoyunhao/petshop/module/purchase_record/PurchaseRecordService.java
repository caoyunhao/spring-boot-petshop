package com.caoyunhao.petshop.module.purchase_record;


import com.caoyunhao.petshop.common.exception.ErrorCode;
import com.caoyunhao.petshop.common.exception.WebBackendException;
import com.caoyunhao.petshop.common.util.ConvertUtil;
import com.caoyunhao.petshop.common.util.DataUtil;
import com.caoyunhao.petshop.common.util.RowConverter;

import com.caoyunhao.petshop.entity.*;
import com.caoyunhao.petshop.module.commodity.CommodityService;
import com.caoyunhao.petshop.repository.CommodityRepository;
import com.caoyunhao.petshop.repository.CommodityStoreRepository;
import com.caoyunhao.petshop.repository.CustomWalletRepository;
import com.caoyunhao.petshop.repository.PurchaseRecordRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@Service
public class PurchaseRecordService {
    @Autowired
    PurchaseRecordRepository purchaseRecordRepository;
    @Autowired
    CommodityRepository commodityRepository;
    @Autowired
    CustomWalletRepository customWalletRepository;
    @Autowired
    CommodityService commodityService;
    @Autowired
    CommodityStoreRepository commodityStoreRepository;

    //交易状态码
    private static final String SUCCESS = "成功";
    private static final String BALANCE_NOT_ENOUGH = "余额不足";
    private static final String STORE_NOT_ENOUGH = "库存不足";

    /**
     * 获取所有购买记录
     */
    public Page<PurchaseRecordForm> findPurchaseRecords(Long customId, Pageable pageable) throws Exception {
        Page<PurchaseRecord> purchaseRecordPage = purchaseRecordRepository.findByCustomId(customId, pageable);
        Page<PurchaseRecordForm> purchaseRecordFormPage = ConvertUtil.convertPage(purchaseRecordPage, pageable, purchaseRecordPage.getTotalElements(), new RowConverter<PurchaseRecord, PurchaseRecordForm>() {
            @Override
            public PurchaseRecordForm convertRow(PurchaseRecord purchaseRecord) throws Exception {
                PurchaseRecordForm purchaseRecordForm = new PurchaseRecordForm();
                BeanUtils.copyProperties(purchaseRecord, purchaseRecordForm);
                purchaseRecordForm.setCommodityImageUrl("");
                purchaseRecordForm.setPurchaseOrderTime(purchaseRecord.getPurchaseOrderTime().toString());
                return purchaseRecordForm;
            }
        });
        return purchaseRecordFormPage;
    }

    /**
     * 获取购买记录
     */
    public PurchaseRecordForm findPurchaseRecord(Long customId, Long purchaseRecordId) throws Exception {
        PurchaseRecord purchaseRecord = getPurchaseRecord(customId, purchaseRecordId);
        PurchaseRecordForm purchaseRecordForm = new PurchaseRecordForm();
        BeanUtils.copyProperties(purchaseRecord, purchaseRecordForm, "purchaseOrderTime");
        purchaseRecordForm.setCommodityImageUrl("");
        purchaseRecordForm.setPurchaseOrderTime(purchaseRecord.getPurchaseOrderTime().toString());
        return purchaseRecordForm;
    }

    /**
     * 根据时间段查询购买记录
     */
    public Page<PurchaseRecordForm> findPurchaseRecordsByTimePeriod(DateForm dateForm, Long customId) throws Exception {
        Page<PurchaseRecord> purchaseRecordPage;
        String start = dateForm.getStart();
        String end = dateForm.getEnd();
        if (start != null && end != null) {
            Timestamp startTime = Timestamp.valueOf(start);
            Timestamp endTime = Timestamp.valueOf(end);
            if (startTime.before(endTime))
                purchaseRecordPage = purchaseRecordRepository.findByPurchaseOrderTimeBetweenAndCustomId(startTime, endTime, dateForm.getPageRequest(), customId);
            else
                throw new WebBackendException(ErrorCode.TIME_REQUIRED);
        } else if (start == null && end != null) {
            Timestamp endTime = Timestamp.valueOf(end);
            purchaseRecordPage = purchaseRecordRepository.findByPurchaseOrderTimeBeforeAndCustomId(endTime, dateForm.getPageRequest(), customId);
        } else if (start != null && end == null) {
            Timestamp startTime = Timestamp.valueOf(start);
            purchaseRecordPage = purchaseRecordRepository.findByPurchaseOrderTimeAfterAndCustomId(startTime, dateForm.getPageRequest(), customId);
        } else {
            throw new WebBackendException(ErrorCode.TIME_REQUIRED);
        }
        Page<PurchaseRecordForm> purchaseRecordFormPage = ConvertUtil.convertPage(purchaseRecordPage, dateForm.getPageRequest(), purchaseRecordPage.getTotalElements(), new RowConverter<PurchaseRecord, PurchaseRecordForm>() {
            @Override
            public PurchaseRecordForm convertRow(PurchaseRecord purchaseRecord) throws Exception {
                PurchaseRecordForm purchaseRecordForm = new PurchaseRecordForm();
                BeanUtils.copyProperties(purchaseRecord, purchaseRecordForm, "purchaseOrderTime");
                purchaseRecordForm.setPurchaseOrderTime(purchaseRecord.getPurchaseOrderTime().toString());
                purchaseRecordForm.setCommodityImageUrl("");
                return purchaseRecordForm;
            }
        });
        return purchaseRecordFormPage;
    }

    /**
     * 支付
     */
    public void pay(PurchaseOrderForm purchaseOrderForm, Long customId) throws Exception {
        //找到商品
        Commodity commodity = commodityService.getCommodity(purchaseOrderForm.getCommodityId());
        //计算总价
        int purchaseQuantity = purchaseOrderForm.getPurchaseQuantity();
        if (purchaseQuantity < 1)
            throw new WebBackendException(ErrorCode.PURCHASEQUANTITY_NOT_VALID);
        double commodityPrice = commodity.getCommodityPrice();
        double purchaseTotalPrice = purchaseQuantity * commodityPrice;
        //获取余额
        CustomWallet customWallet = getCustomWallet(customId);

        double walletBalance = customWallet.getWalletBalance();
        //是否购买成功
        String purchaseStatement = SUCCESS;
        //购买记录表单
        PurchaseRecordForm purchaseRecordForm = new PurchaseRecordForm();
        BeanUtils.copyProperties(purchaseOrderForm, purchaseRecordForm);
        purchaseRecordForm.setPurchaseTotalPrice(purchaseTotalPrice);
        purchaseRecordForm.setPurchaseQuantity(purchaseQuantity);
        purchaseRecordForm.setCommodityName(commodityRepository.findById(purchaseOrderForm.getCommodityId()).get().getCommodityName());
        //判断库存
        CommodityStore commodityStore = commodityService.getCommodityStore(purchaseRecordForm.getCommodityId());
        if (commodityStore.getCommodityLast() < purchaseRecordForm.getPurchaseQuantity())
            throw new WebBackendException(ErrorCode.COMMODITY_STORE_NOT_ENOUGH);
        //余额不足
        if (purchaseTotalPrice > walletBalance) {
            purchaseStatement = BALANCE_NOT_ENOUGH;
            addPurchaseRecord(purchaseRecordForm, customId, purchaseStatement);
            throw new WebBackendException(ErrorCode.BALANCE_NOT_ENOUGH);
        }
        try {
            commodityService.commodityStoreReduce(purchaseRecordForm);
        } catch (Exception e) {
            purchaseStatement = STORE_NOT_ENOUGH;
            addPurchaseRecord(purchaseRecordForm, customId, purchaseStatement);
            throw new WebBackendException(ErrorCode.COMMODITY_STORE_NOT_ENOUGH);
        }

        customWallet.setWalletBalance(walletBalance - purchaseTotalPrice);
        customWalletRepository.save(customWallet);
        addPurchaseRecord(purchaseRecordForm, customId, purchaseStatement);
    }

    /**
     * 生成购买记录
     */
    private void addPurchaseRecord(PurchaseRecordForm purchaseRecordForm, Long customId, String purchaseStatement) {
        PurchaseRecord purchaseRecord = new PurchaseRecord();
        BeanUtils.copyProperties(purchaseRecordForm, purchaseRecord);
        purchaseRecord.setPurchaseOrderTime(new Timestamp(System.currentTimeMillis()));
        purchaseRecord.setCustomId(customId);
        purchaseRecord.setPurchaseStatement(purchaseStatement);
        purchaseRecord.setCommodityDescription(commodityRepository.findById(purchaseRecordForm.getCommodityId()).get().getCommodityDescription());
        purchaseRecordRepository.save(purchaseRecord);
    }

    private PurchaseRecord getPurchaseRecord(Long customId, Long id)
            throws WebBackendException {

        Optional<PurchaseRecord> optional = purchaseRecordRepository.findByCustomIdAndId(customId, id);
        if (optional.isPresent())
            return optional.get();
        else
            throw new WebBackendException(ErrorCode.PURCHASERECORD_NOT_FOUND);
    }

    private CustomWallet getCustomWallet(Long customId)
            throws WebBackendException {
        return DataUtil.getOrElse(id_ -> customWalletRepository.findByCustomId(id_), customId);
    }
}
