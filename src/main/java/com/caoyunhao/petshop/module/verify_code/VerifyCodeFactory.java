package com.caoyunhao.petshop.module.verify_code;

import com.caoyunhao.petshop.common.util.VerifyCodeUtil;
import com.google.code.kaptcha.Producer;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/4
 */
public class VerifyCodeFactory {
    public VerifyCode creator() {
        VerifyCode verifyCode = new VerifyCode();
        Producer producer = VerifyCodeUtil.captchaProducer();

        String createText;
        verifyCode.setText(createText = producer.createText());
        BufferedImage bufferedImage = producer.createImage(createText);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        verifyCode.setImageData("data:image/png;base64," + DatatypeConverter.printBase64Binary(byteArrayOutputStream.toByteArray()));
        verifyCode.setToken(UUID.randomUUID().toString().replace("-", ""));

        return verifyCode;
    }
}
