package com.centyun.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IP与Long的相互转换工具类, 因为MySQL数据库中存取Long型ip的效率更高
 * 
 * @author yinww
 *
 */
public class IpUtils {
    public static Logger log = LoggerFactory.getLogger(IpUtils.class);
    
    /**
     * ip地址转长整型
     * 
     * @param ipStr, ip地址
     * @return
     */
    public static Long ipToLong(String ipStr) {
        if(ipStr == null || ipStr.trim().length() == 0) {
            return null;
        }
        
        try {
            String[] ip = ipStr.split("\\.");
            return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16) + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
        } catch (Exception e) {
            System.out.println("error ip: " + ipStr);
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 长整型转ip地址
     * 
     * @param longIp
     * @return
     */
    public static String longToIP(long longIp) {
        StringBuffer sb = new StringBuffer("");
        // 直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

}
