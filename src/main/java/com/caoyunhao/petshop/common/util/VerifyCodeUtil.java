package com.caoyunhao.petshop.common.util;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

@Configuration
public class VerifyCodeUtil {

    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_PRAGMA = "Pragma";

    /**
     * 注册验证码绘制类
     */
    @Bean(name = "captchaProducer")
    public static Producer captchaProducer() {

        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "0,0,0");
        properties.setProperty("kaptcha.image.width", "140");
        properties.setProperty("kaptcha.image.height", "46");
        //properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.char.string", "acdefg34578hkmnprstwxy");
        properties.setProperty("kaptcha.textproducer.font.size", "40");
        //properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        captchaProducer.setConfig(config);

        return captchaProducer;
    }

    /**
     * 返回新验证信息,写入response
     */
    public static void writeHeaderCache(HttpServletResponse response) {
        response.setContentType("image/jpeg");
        response.setHeader(HEADER_CACHE_CONTROL, "no-store, no-cache, must-revalidate");
        response.addHeader(HEADER_CACHE_CONTROL, "post-check=0, pre-check=0");
        response.setHeader(HEADER_PRAGMA, "no-cache");
    }
}
