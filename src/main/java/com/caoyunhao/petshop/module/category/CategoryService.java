package com.caoyunhao.petshop.module.category;

import com.caoyunhao.petshop.common.exception.ErrorCode;
import com.caoyunhao.petshop.common.exception.WebBackendException;
import com.caoyunhao.petshop.common.util.DataUtil;
import com.caoyunhao.petshop.entity.Category;
import com.caoyunhao.petshop.entity.Commodity;
import com.caoyunhao.petshop.entity.CommodityCategory;
import com.caoyunhao.petshop.module.commodity.CommodityService;
import com.caoyunhao.petshop.repository.CategoryRepository;
import com.caoyunhao.petshop.repository.CommodityCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CommodityCategoryRepository commodityCategoryRepository;
    @Autowired
    private CommodityService commodityService;

    /**
     * 根据id查找品类
     *
     * @param categoryId
     * @return
     * @throws Exception
     */
    public Category findByCategoryId(Long categoryId) throws Exception {
        return getCategory(categoryId);
    }

    /**
     * 展示商品分页
     *
     * @param pageable
     * @return
     * @throws Exception
     */

    public Page<Category> findAllCategories(Pageable pageable) throws Exception {
        return categoryRepository.findAll(pageable);
    }

    /**
     * 根据品类名称模糊搜索
     *
     * @param categoryName
     * @param pageable
     * @return
     * @throws Exception
     */
    public Page<Category> findAllByCategorySearchName(String categoryName, Pageable pageable) throws Exception {
        return categoryRepository.findByCategoryNameLike(categoryName, pageable);
    }

    /**
     * 展示品类的所有商品
     *
     * @param categoryId
     * @return
     * @throws Exception
     */
    public Page<Commodity> findAllCommoditiesByCategoryId(Long categoryId, Pageable pageable) throws Exception {
        getCategory(categoryId);
        List<CommodityCategory> commodityCategoryList;
        if ((commodityCategoryList = commodityCategoryRepository.findAllByCategoryId(categoryId)) == null) {
            throw new WebBackendException(ErrorCode.CATEGORY_HAVE_NO_COMMODITY);
        }
        List<Commodity> commodityList = new ArrayList<>();
        for (CommodityCategory commodityCategory : commodityCategoryList) {
            commodityList.add(commodityService.getCommodity(commodityCategory.getCommodityId()));
        }
        return new PageImpl<>(commodityList, pageable, commodityList.size());
    }

    /**
     * 根据商品id查询品类列表
     *
     * @param commodityId
     * @return
     * @throws Exception
     */
    public List<Category> findAllCategoriesByCommodityId(Long commodityId) throws Exception {
        commodityService.getCommodity(commodityId);
        if (commodityCategoryRepository.findAllByCommodityId(commodityId) == null) {
            return null;
        }
        List<Category> categoryList = new ArrayList<>();
        for (CommodityCategory commodityCategory :
                commodityCategoryRepository.findAllByCommodityId(commodityId)) {
            categoryList.add(getCategory(commodityCategory.getCategoryId()));
        }
        return categoryList;
    }

    private Category getCategory(Long id)
            throws WebBackendException {
        return DataUtil.getOrElse(id_ -> categoryRepository.findById(id_), id);
    }
}
