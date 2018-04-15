package com.caoyunhao.petshop.module.commodity;

import com.caoyunhao.petshop.common.exception.ErrorCode;
import com.caoyunhao.petshop.common.exception.WebBackendException;
import com.caoyunhao.petshop.common.util.*;
import com.caoyunhao.petshop.entity.*;
import com.caoyunhao.petshop.module.category.CategoryService;
import com.caoyunhao.petshop.module.purchase_record.PurchaseRecordForm;
import com.caoyunhao.petshop.repository.CommodityCategoryRepository;
import com.caoyunhao.petshop.repository.CommodityImageRepository;
import com.caoyunhao.petshop.repository.CommodityRepository;
import com.caoyunhao.petshop.repository.CommodityStoreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@Service
@Transactional
public class CommodityService {
    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    CommodityStoreRepository commodityStoreRepository;

    @Autowired
    CommodityCategoryRepository commodityCategoryRepository;

    @Autowired
    CommodityImageRepository commodityImageRepository;

    @Autowired
    CategoryService categoryService;

    /**
     * 获取商品列表
     */
    public Page<CommodityData> findAllCommodities(Pageable pageable) throws Exception {
        Page<Commodity> commodityPage = commodityRepository.findAll(pageable);
        return toCommodityDataPage(commodityPage, pageable);
    }

    /**
     * 搜索商品, 商品名, 品类id
     */
    public Page<CommodityData> searchCommodity(String commodityName, Long categoryId, Pageable pageable) throws Exception {
        if (commodityName != null) {
            commodityName = commodityName.replace(" ", "");
        }
        // categoryId 为 null ?
        if (null != categoryId) {
            List<CommodityData> commodityDataList = new ArrayList<>();
            // commodityName 为 Empty ?
            if (!StringUtils.isEmpty(commodityName)) {
                // 相似Name
                List<Commodity> commodityList = commodityRepository.findAllByCommodityNameLike("%" + commodityName + "%");
                for (Commodity commodity : commodityList) {
                    Long commodityId = commodity.getId();
                    List<CommodityCategory> commodityCategoryList = commodityCategoryRepository.findAllByCommodityId(commodityId);
                    for (CommodityCategory commodityCategory : commodityCategoryList) {
                        Long categoryId1 = commodityCategory.getCategoryId();
                        if (categoryId1.equals(categoryId)) {
                            commodityDataList.add(toCommodityData(commodity));
                        }
                    }
                }
            } else {
                List<CommodityCategory> commodityCategoryList = commodityCategoryRepository.findAllByCategoryId(categoryId);
                for (CommodityCategory commodityCategory : commodityCategoryList) {
                    Long commodityId = commodityCategory.getCommodityId();
                    Commodity commodity = getCommodity(commodityId, false, null);
                    if (commodity != null) {
                        commodityDataList.add(toCommodityData(commodity));
                    }
                }
            }
            List<CommodityData> contentList = splitList(commodityDataList, pageable);
            if (null == contentList) {
                return null;
            }
            return new PageImpl<>(contentList, pageable, commodityDataList.size());
        } else if (!StringUtils.isEmpty(commodityName)) {
            Page<Commodity> commodityPage = commodityRepository.findAllByCommodityNameLike("%" + commodityName + "%", pageable);
            return toCommodityDataPage(commodityPage, pageable);
        }
        return findAllCommodities(pageable);
    }

    /**
     * 获取商品
     */
    public CommodityData findCommodity(Long commodityId) throws Exception {
        return toCommodityData(getCommodity(commodityId));
    }

    /**
     * 添加商品
     */
    public CommodityData addCommodity(CommodityForm commodityForm) throws Exception {
        Commodity commodity = new Commodity();

        BeanUtils.copyProperties(commodityForm, commodity);

        commodityRepository.save(commodity);
        Long commodityId = commodity.getId();

        CommodityStore commodityStore = new CommodityStore();
        commodityStore.setCommodityId(commodityId);

        BeanUtils.copyProperties(commodityForm, commodityStore);

        commodityStoreRepository.save(commodityStore);
        commodityCategoryRepository.saveAll(generateCommodityCategoryList(commodityForm.getCommodityCategoryIdList(), commodityId));

        return toCommodityData(commodity);
    }

    /**
     * 更新商品
     */
    public CommodityData updateCommodity(CommodityForm commodityForm, Long commodityId) throws Exception {
        // 商品属性相关
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityForm, commodity);

        commodity.setId(commodityId);
        commodityRepository.save(commodity);

        // 数量相关
        CommodityStore commodityStore = new CommodityStore();
        BeanUtils.copyProperties(commodityForm, commodityStore);

        commodityStore.setCommodityId(commodityId);
        commodityStoreRepository.save(commodityStore);

        // 类目相关
        // 删除已有的“商品类目关系”
        commodityCategoryRepository.deleteByCommodityId(commodityId);
        // 创建新的“商品类目关系”
        commodityCategoryRepository.saveAll(generateCommodityCategoryList(commodityForm.getCommodityCategoryIdList(), commodityId));

        return toCommodityData(commodity);
    }

    /**
     * 出货之后减少库存
     */
    public void commodityStoreReduce(PurchaseRecordForm purchaseRecordForm) throws Exception {
        CommodityStore commodityStore = getCommodityStore(purchaseRecordForm.getCommodityId());
        if (commodityStore.getCommodityLast() < purchaseRecordForm.getPurchaseQuantity())
            throw new WebBackendException(ErrorCode.COMMODITY_STORE_NOT_ENOUGH);
        commodityStore.setCommoditySold(commodityStore.getCommoditySold() + purchaseRecordForm.getPurchaseQuantity());
        commodityStore.setCommodityLast(commodityStore.getCommodityLast() - purchaseRecordForm.getPurchaseQuantity());
        commodityStoreRepository.save(commodityStore);
    }

    /**
     * 从Repository取商品
     */
    public Commodity getCommodity(Long commodityId)
            throws WebBackendException {
        return getCommodity(commodityId, true, null);
    }

    public Commodity getCommodity(Long commodityId, boolean throw_, Commodity orElse)
            throws WebBackendException {
        return DataUtil.getOrElse(id_ -> commodityRepository.findById(id_), commodityId, throw_);
    }

    public CommodityStore getCommodityStore(Long commodityId)
            throws WebBackendException {
        return DataUtil.getOrElse(id -> commodityStoreRepository.findByCommodityId(id), commodityId);
    }

    /**
     * 根据商品和品类列表插入关联
     */
    private List<CommodityCategory> generateCommodityCategoryList(List<Long> categoryIdList, Long commodityId) throws Exception {
        if (categoryIdList == null || categoryIdList.size() == 0) {
            return null;
        }
        List<CommodityCategory> commodityCategoryList = new ArrayList<CommodityCategory>();
        for (Long categoryId : categoryIdList) {
            CommodityCategory commodityCategory = new CommodityCategory();
            commodityCategory.setCommodityId(commodityId);
            commodityCategory.setCategoryId(categoryId);
            commodityCategoryList.add(commodityCategory);
        }
        return commodityCategoryList;
    }

    /**
     * 根据商品Id获取商品的Category列表
     */
    private List<Category> getCommodityCategoryList(Long commodityId) throws Exception {
        return categoryService.findAllCategoriesByCommodityId(commodityId);
    }

    /**
     * 将商品Commodity商品转变为CommodityData
     */
    private CommodityData toCommodityData(Commodity commodity) throws Exception {
        CommodityData commodityData = new CommodityData();
        BeanUtils.copyProperties(commodity, commodityData);

        Long commodityId = commodity.getId();

        commodityData.setCommodityCategoryList(getCommodityCategoryList(commodityId));

        CommodityStore commodityStore = getCommodityStore(commodityId);
        if (null != commodityStore) {
            BeanUtils.copyProperties(commodityStore, commodityData, "id");
        }
        commodityData.setCommodityImageUrl("");
        return commodityData;
    }

    /**
     * 将Page<Commodity>转变为Page<CommodityData>
     */
    private Page<CommodityData> toCommodityDataPage(Page<Commodity> commodityPage, Pageable pageable) throws Exception {
        return ConvertUtil.convertPage(commodityPage, pageable, commodityPage.getTotalElements(), new RowConverter<Commodity, CommodityData>() {
            @Override
            public CommodityData convertRow(Commodity commodity) throws Exception {
                return toCommodityData(commodity);
            }
        });
    }

    /**
     * 将List转变为Page
     */
    private <T> List<T> splitList(List<T> originList, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();

        if (pageNumber * pageSize >= originList.size()) {
            return null;
        }

        int fromIndex = pageNumber * pageSize;
        int toIndex = (pageNumber + 1) * pageSize;

        if (toIndex > originList.size()) {
            toIndex = originList.size();
        }

        return originList.subList(fromIndex, toIndex);
    }
}
