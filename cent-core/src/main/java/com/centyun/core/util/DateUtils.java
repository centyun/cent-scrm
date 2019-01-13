package com.centyun.core.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * @author yinww
 *
 */
public class DateUtils {

    public static Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String YEAR_MONTH = "yyyyMM";
    public static final String DAY = "dd";
    public static final String HOURE = "HH";
    public static final String FULL = "yyyyMMddHHmmssSSS";

    /**
     * 按自定义日期格式格式化日期
     *
     * @param target
     * @param format
     * @return 格式化后的日期字符串，如果传入的日期对象为NULL，返回空字符串
     */

    public static String formatDate(String target, String format) {
        if (target == null) {
            return "";
        }
        return target;
    }

    /**
     * 当前时间字符串
     */
    public static String dateToString() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss").toString();
    }

    public static String formatDate(Date target, String format) {
        if (target == null) {
            return "";
        }
        return new SimpleDateFormat(format).format(target);
    }

    public static Timestamp parseDate(String date, String format) throws ParseException {
        return new Timestamp(new SimpleDateFormat(format).parse(date).getTime());
    }

    public static String formatDate(String date, String fromFormat, String toFormat) throws ParseException {
        if (date == null || date.trim().length() == 0) {
            return null;
        }
        return formatDate(parseDate(date, fromFormat), toFormat);
    }

    public static long calculateTimeDifference(String date1, String date2) throws ParseException {
        return parseDate(date2, DATETIME_FORMAT).getTime() - parseDate(date1, DATETIME_FORMAT).getTime();
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String formatTimeLongToStr(Long time, Long split) {
        return String.valueOf(time / split);
    }

    public static String formatHourDisplay(String hour) {
        try {
            Timestamp time = parseDate(hour, "yyyy-MM-dd HH");
            return formatDate(time, "yyyy-MM-dd HH:mm") + "-"
                    + formatDate(new Timestamp(time.getTime() + 3600000), "HH:mm");
        } catch (ParseException e) {
//            log.error(e.getMessage(), e);
            return "";
        }
    }

    public static String getDate(String str) {
        return str.substring(0, 10);
    }

    /**
     * 将String 类型的秒数 转换为 HH:mm:ss 类型
     * 
     * @param time
     * @return
     */
    public static String parseString2DateString(String time) {
        long ms = Integer.parseInt(time) * 1000;// 毫秒数
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");// 初始化Formatter的转换格式。
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return formatter.format(ms);
    }

    /**
     * 时间转换成秒数
     * 
     * @param time
     * @return
     */
    public static Integer parseString2TimeInteger(String time) {
        String[] split = time.split(":");
        Integer s = Integer.parseInt(split[0]) * 3600; // 小时
        s += Integer.parseInt(split[1]) * 60; // 分钟
        s += Integer.parseInt(split[2]); // 秒
        return s;
    }

    /**
     * 两个时间相差秒数
     * 
     * @param date 时间参数 1 格式：1990-01-01 12:00:00
     * @param date 时间参数 2 格式：2009-01-01 12:00:00
     * @return long
     */
    public static long getDistanceTimes(Date a, Date b) {
        long interval = (b.getTime() - a.getTime()) / 1000;
        return interval;
    }

    public static Date getDate(String dateStr, String format) {
        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (Exception e) {
//            log.error(e.getMessage(), e);
        }
        return new Date();
    }

}
