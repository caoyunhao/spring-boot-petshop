package com.caoyunhao.petshop.module.image;

import com.caoyunhao.petshop.common.exception.ErrorCode;
import com.caoyunhao.petshop.common.exception.WebBackendException;
import com.caoyunhao.petshop.common.util.DataUtil;
import com.caoyunhao.petshop.common.util.HashCodeUtil;
import com.caoyunhao.petshop.entity.*;
import com.caoyunhao.petshop.repository.CommodityImageRepository;
import com.caoyunhao.petshop.repository.ImageStoreRepository;
import com.caoyunhao.petshop.repository.CustomImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@Service
@Transactional
public class ImageService {

    @Autowired
    ImageStoreRepository imageStoreRepository;

    @Autowired
    CommodityImageRepository commodityImageRepository;

    @Autowired
    CustomImageRepository customImageRepository;

    private final int COMMODITY_DEFAULT_IMAGE_NUMBER = 1;

    /**
     * 查找商品图片
     *
     * @param commodityId
     * @param httpServletResponse
     * @throws Exception
     */
    public void findCommodityImage(Long commodityId, HttpServletResponse httpServletResponse) throws Exception {
        CommodityImage commodityImage = getCommodityImageByCommodityId(commodityId);
        if (commodityImage == null) {
            return;
            // throw new WebBackendException(ErrorCode.QUERY_DATA_EMPTY);
        }
        Long imageId = commodityImage.getImageId();
        this.findImage(imageId, httpServletResponse);
    }

    /**
     * 添加商品图片
     *
     * @param multipartFile
     * @param commodityId
     * @throws Exception
     */
    public void addCommodityImage(MultipartFile multipartFile, Long commodityId) throws Exception {
        if (null == multipartFile) {
            throw new WebBackendException(ErrorCode.FILE_PARAM_IS_REQUIRED);
        }

        Long imageId = this.addImage(multipartFile);

        commodityImageRepository.deleteAllByCommodityId(commodityId);

        CommodityImage commodityImage = new CommodityImage();

        commodityImage.setCommodityId(commodityId);
        commodityImage.setImageId(imageId);
        commodityImage.setImageNumber(COMMODITY_DEFAULT_IMAGE_NUMBER);

        commodityImageRepository.save(commodityImage);
    }

    /**
     * 查找顾客图片
     */
    public void findCustomImage(Long customId, HttpServletResponse httpServletResponse) throws Exception {
        CustomImage customImage = getCustomImageByCustomId(customId);
        if (customImage == null) {
            return;
            //throw new WebBackendException(ErrorCode.QUERY_DATA_EMPTY);
        }
        Long imageId = customImage.getImageId();
        this.findImage(imageId, httpServletResponse);
    }

    /**
     * 添加顾客头像
     */
    public void addCustomImage(MultipartFile multipartFile, Long customId) throws Exception {
        if (null == multipartFile) {
            throw new WebBackendException(ErrorCode.FILE_PARAM_IS_REQUIRED);
        }
        Long imageId = this.addImage(multipartFile);
        customImageRepository.deleteAllByCustomId(customId);
        CustomImage customImage = new CustomImage();
        customImage.setCustomId(customId);
        customImage.setImageId(imageId);
        customImage.setImageNumber(COMMODITY_DEFAULT_IMAGE_NUMBER);

        customImageRepository.save(customImage);
    }

    /**
     * 查找图片
     */
    public void findImage(Long id, HttpServletResponse httpServletResponse) throws Exception {
        if (null == id) {
            throw new WebBackendException(ErrorCode.IMAGE_ID_NOT_VALID);
        }

        ImageStore imageStore = getImageStore(id);

        ServletOutputStream out = httpServletResponse.getOutputStream();

        out.write(imageStore.getImage());

        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * 添加图片
     *
     * @return image id
     */
    private Long addImage(MultipartFile multipartFile) throws Exception {
        if (null == multipartFile) {
            throw new WebBackendException(ErrorCode.NOT_VALID_PARAM);
        }

        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        // 是否为图片
        if (bufferedImage == null) {
            throw new WebBackendException(ErrorCode.FILE_NOT_VALID);
        }
        System.out.println(bufferedImage.getType());

        byte[] imageBytes = multipartFile.getBytes();

        String imageHash = HashCodeUtil.getHash(imageBytes);
        if (null == imageHash) {
            throw new WebBackendException(ErrorCode.FILE_NOT_VALID);
        }

        ImageStore existedImageStore = getImageStoreByHash(imageHash);
        if (null != existedImageStore) {
            return existedImageStore.getId();
        } else {
            String fileName = multipartFile.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            if (suffixName.length() <= 1) {
                throw new WebBackendException(ErrorCode.FILE_NOT_VALID);
            }

            ImageStore imageStore = new ImageStore();
            imageStore.setImage(multipartFile.getBytes());
            imageStore.setFormat(suffixName.replace(".", "").toLowerCase());
            imageStore.setHash(imageHash);

            return imageStoreRepository.save(imageStore).getId();
        }
    }

    public CustomImage getCustomImageByCustomId(Long customId)
            throws WebBackendException {
        return DataUtil.getOrElse(id_ -> customImageRepository.findByCustomId(id_), customId, false);
    }

    public CommodityImage getCommodityImageByCommodityId(Long commodityId)
            throws WebBackendException {
        return DataUtil.getOrElse(id_ -> commodityImageRepository.findByCommodityId(id_), commodityId, false);
    }

    public ImageStore getImageStore(Long id)
            throws WebBackendException {
        return DataUtil.getOrElse(id_ -> imageStoreRepository.findById(id_), id);
    }

    public ImageStore getImageStoreByHash(String hash)
            throws WebBackendException {
        Optional<ImageStore> imageStoreOptional = imageStoreRepository.findByHash(hash);
        return imageStoreOptional.orElse(null);
    }
}
