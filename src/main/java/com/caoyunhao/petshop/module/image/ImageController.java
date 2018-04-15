package com.caoyunhao.petshop.module.image;

import com.caoyunhao.petshop.entity.Custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@RequestMapping(value = "/images")
@RestController
public class ImageController {

    @Autowired
    ImageService imageService;

    /**
     * 获取用户头像
     */
    @RequestMapping(value = "/get/customs", method = RequestMethod.GET)
    public void findCustomImage(@RequestAttribute("custom") Custom custom, HttpServletResponse httpServletResponse) throws Exception {
        imageService.findCustomImage(custom.getId(), httpServletResponse);
    }

    /**
     * 获取商品图片
     */
    @RequestMapping(value = "/get/commodities/{id}", method = RequestMethod.GET)
    public void findCommodityImage(@PathVariable Long id, HttpServletResponse httpServletResponse) throws Exception {
        imageService.findCommodityImage(id, httpServletResponse);
    }

    /**
     * 获取图片
     */
    @RequestMapping(value = "/get/{imageId}", method = RequestMethod.GET)
    public void findImage(@PathVariable Long imageId, HttpServletResponse httpServletResponse) throws Exception {
        imageService.findImage(imageId, httpServletResponse);
    }
}
