package com.caoyunhao.petshop.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class HttpUtil {

    /**
     * 获取来访者ip
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (!isIpValidIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!isIpValidIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!isIpValidIP(ip)) {
            ip = request.getRemoteAddr();
        }
        // 多代理情况，获取第一个
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    /**
     * 检查ip字符串是否合规
     *
     * @param ip
     * @return
     */
    private static boolean isIpValidIP(String ip) {
        if (ip == null || ip.isEmpty() || ip.equalsIgnoreCase("unknown")) {
            return false;
        }
        return true;
    }

}
