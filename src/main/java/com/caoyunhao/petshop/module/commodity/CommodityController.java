package com.caoyunhao.petshop.module.commodity;

import com.caoyunhao.petshop.common.exception.ErrorCode;
import com.caoyunhao.petshop.common.page.PageParams;
import com.caoyunhao.petshop.common.response.BaseResponse;
import com.caoyunhao.petshop.common.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@RequestMapping(value = "/commodities")
@RestController
public class CommodityController {
    @Autowired
    CommodityService commodityService;

    /**
     * 获取商品列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageResponse<CommodityData> findAllCommodities(PageParams pageParams) throws Exception {
        PageResponse<CommodityData> response = new PageResponse<>(commodityService.findAllCommodities(pageParams.getPageRequest()));
        response.setError(ErrorCode.SUCCESS);
        return response;
    }

    /**
     * 获取商品
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<CommodityData> findCommodity(@PathVariable Long id) throws Exception {
        return new BaseResponse<>(commodityService.findCommodity(id));
    }

    /**
     * 搜索商品
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public PageResponse<CommodityData> searchCommodity(CommoditySearchParams searchParams) throws Exception {
        PageResponse<CommodityData> response = new PageResponse<>(commodityService.searchCommodity(searchParams.getQ(), searchParams.getCategoryId(), searchParams.getPageRequest()));
        response.setError(ErrorCode.SUCCESS);
        return response;
    }
}
