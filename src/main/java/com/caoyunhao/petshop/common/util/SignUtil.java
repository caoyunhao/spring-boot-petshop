package com.caoyunhao.petshop.common.util;

import javax.servlet.http.HttpServletResponse;

public class SignUtil {

    public static final String HEADER_AUTHORIZATION_INFO = "Authorization-info";
    private static final String NOUCE_KEY = "nonce___";

    public static String generateNonce(String path, Long userId) {
        /**
         * 参考维基百科https://zh.wikipedia.org/wiki/HTTP%E6%91%98%E8%A6%81%E8%AE%A4%E8%AF%81
         * 对于三次md5进行了自定义简化
         * */
        String HA1 = EncryptionUtil.encrypt(path, "md5");
        String HA2 = EncryptionUtil.encrypt(NOUCE_KEY + ":" + System.currentTimeMillis(), "md5");
        return EncryptionUtil.encrypt(HA1 + userId + HA2, "md5");
    }

    /**
     * 写入过期返回头并返回新nonce
     * @param response
     * @param nonce
     */
    public static void write401StaleResponse(HttpServletResponse response, String nonce){
        SignUtil.write401Response(response);
        SignUtil.writeStaleResponse(response);
        SignUtil.writeAuthorization(response, nonce);
    }

    /**
     * 写入验证信息已过期返回头
     */
    public static void writeStaleResponse(HttpServletResponse response) {
        response.setHeader("Stale", "true");
    }

    /**
     * 写入401返回头
     */
    public static void write401Response(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    /**
     * 生成签名，与用户送来的签名进行比对
     */
    public static String generateSign(String userId, String nouce, String token) {
        return EncryptionUtil.encrypt(token + userId + ":" + nouce);
    }

    /**
     * 返回新验证信息,写入response
     */
    public static void writeAuthorization(HttpServletResponse response, String newNouce) {
        response.setHeader(HEADER_AUTHORIZATION_INFO, "nouce=" + newNouce);
    }
}
