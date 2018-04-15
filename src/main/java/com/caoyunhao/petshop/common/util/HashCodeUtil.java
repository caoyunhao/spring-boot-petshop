package com.caoyunhao.petshop.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class HashCodeUtil {
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    /**
     * 计算message的MD5编码
     *
     * @param message
     *            摘要信息
     * @return MD5编码之后的字符串
     */
    public static String md5Hash(String message) {
        return hash("MD5", message);
    }

    /**
     * 计算message的SHA编码
     *
     * @param message
     *            摘要信息
     * @return SHA编码之后的字符串
     */
    public static String shaHash(String message) {
        return hash("SHA", message);
    }

    /**
     * 计算message的SHA-256编码
     *
     * @param message
     *            摘要信息
     * @return SHA-256编码之后的字符串
     */
    public static String sha256Hash(String message) {
        return hash("SHA-256", message);
    }

    /**
     * 计算message的SHA-512编码
     *
     * @param message
     *            摘要信息
     * @return SHA-512编码之后的字符串
     */
    public static String sha512Hash(String message) {
        return hash("SHA-512", message);
    }

    /**
     * 计算bytes的SHA-512编码
     *
     * @param bytes
     *            摘要信息
     * @return SHA-512编码之后的字符串
     */
    public static String getHash(byte[] bytes) {
        MessageDigest md;
        String encode = null;

        try {
            md = MessageDigest.getInstance("SHA-512");
            encode = byteArrayToHexString(md.digest(bytes));

            return encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将字节数组转换为16进制的字符串
     *
     * @param byteArray
     *            字节数组
     * @return 16进制的字符串
     */
    private static String byteArrayToHexString(byte[] byteArray) {
        StringBuffer sb = new StringBuffer();
        for (byte byt : byteArray) {
            sb.append(byteToHexString(byt));
        }
        return sb.toString();
    }

    /**
     * 将字节转换为16进制字符串
     *
     * @param b
     *            字节
     * @return 16进制字符串
     */
    private static String byteToHexString(byte b) {
        return hexDigits[(b >> 4) & 0x0f] + hexDigits[b & 0x0f];
    }

    /**
     * 将摘要信息转换为相应的编码
     *
     * @param code
     *            编码类型
     * @param message
     *            摘要信息
     * @return 相应的编码字符串
     */
    private static String hash(String code, String message) {
        MessageDigest md;
        String encode = null;

        try {
            md = MessageDigest.getInstance(code);
            encode = byteArrayToHexString(md.digest(message.getBytes()));

            return encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
