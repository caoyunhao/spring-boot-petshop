package com.caoyunhao.petshop.module.purchase_record;

import com.caoyunhao.petshop.common.BaseRequest;
import com.caoyunhao.petshop.common.page.PageParams;
import com.caoyunhao.petshop.common.response.BaseResponse;
import com.caoyunhao.petshop.common.response.PageResponse;
import com.caoyunhao.petshop.entity.Custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@RestController
@RequestMapping("/purchase_records")
public class PurchaseRecordController {
    @Autowired
    PurchaseRecordService purchaseRecordService;

    /**
     * 获取所有购买记录
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageResponse<PurchaseRecordForm> findPurchaseRecords(@RequestAttribute("custom") Custom custom, PageParams pageParams)throws Exception{
        return new PageResponse<>(purchaseRecordService.findPurchaseRecords(custom.getId(),pageParams.getPageRequest()));
    }

    /**
     *获取购买记录
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public BaseResponse<PurchaseRecordForm> findPurchaseRecord(@RequestAttribute("custom") Custom custom,@PathVariable Long id)throws Exception{
        return new BaseResponse<>(purchaseRecordService.findPurchaseRecord(custom.getId(),id));
    }

    /**
     * 根据日期查询购买记录
     */
    @RequestMapping(value = "/time",method = RequestMethod.POST)
    public PageResponse<PurchaseRecordForm> findPurchaseRecordsByTimePeriod(@RequestAttribute("custom") Custom custom,@RequestBody BaseRequest<DateForm> request)throws Exception{
        return new PageResponse<>(purchaseRecordService.findPurchaseRecordsByTimePeriod(request.getData(),custom.getId()));
    }

//    /**
//     * 生成购买记录
//     */
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
//    public BaseResponse addPurchaseRecord(@RequestBody BaseRequest<PurchaseOrderForm>request)throws Exception{
//        purchaseRecordService.addPurchaseRecord(request.getData());
//        return new BaseResponse();
//    }

    /**
     * 支付
     */
    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public BaseResponse pay(@RequestBody BaseRequest<PurchaseOrderForm> request, @RequestAttribute("custom") Custom custom)throws Exception{
        purchaseRecordService.pay(request.getData(), custom.getId());
        return new BaseResponse();
    }
}
