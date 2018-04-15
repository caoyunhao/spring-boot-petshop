package com.caoyunhao.petshop.module.category;

import com.caoyunhao.petshop.common.page.PageParams;
import com.caoyunhao.petshop.common.response.BaseResponse;
import com.caoyunhao.petshop.common.response.PageResponse;
import com.caoyunhao.petshop.entity.Category;
import com.caoyunhao.petshop.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@RequestMapping(value = "/categories")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分页显示品类列表
     * @param pageParams
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageResponse<Category> findAllCategories(PageParams pageParams) throws Exception {
        return new PageResponse<>(categoryService.findAllCategories(pageParams.getPageRequest()));
    }

    /**
     * 根据id查找品类信息
     * @param id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public BaseResponse findCategoryById(@PathVariable Long id) throws Exception{
        return new BaseResponse<>(categoryService.findByCategoryId(id));
    }

    /**
     * 模糊查询品类列表
     * @param cateName
     * @param pageParams
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search/{cateName}",method = RequestMethod.GET)
    public PageResponse<Category> searchAllByCateName(@PathVariable(name = "cateName") String cateName, PageParams pageParams) throws Exception {
        return new PageResponse<>(categoryService.findAllByCategorySearchName("%"+cateName+"%",pageParams.getPageRequest()));
    }

    /**
     * 根据商品所属的所有品类
     * @param categoryId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showAllCommodities/{categoryId}",method = RequestMethod.GET)
    public PageResponse<Commodity> findCommoditiesByCategoryId(@PathVariable(name = "categoryId") long categoryId, PageParams pageParams) throws Exception {
        return new PageResponse<>(categoryService.findAllCommoditiesByCategoryId(categoryId,pageParams.getPageRequest()));
    }
}
