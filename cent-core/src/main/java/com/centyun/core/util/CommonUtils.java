package com.centyun.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用的几个工具方法
 * @author yinww
 *
 */
public class CommonUtils {

    /**
     * 身份证号正则
     */
    private static String REG_IDCARD = "^\\d{6}(19|20)\\d{2}((1[0-2])|0\\d)([0-2]\\d|30|31)\\d{3}[\\d|X|x]$";

    /**
     * 判断是否是身份证号
     * 
     * @param str 任意字符串(不为null)
     * @return 如果字符串满足邮箱格式返回true
     */
    public static boolean isIdCard(String str) {
        return str.matches(REG_IDCARD);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static <T> boolean isEmpty(Collection<T> values) {
        return values == null || values.size() == 0;
    }

    /**
     * 将datas按splitLen的大小拆分成多个
     * 
     * @param datas
     * @param splitLen
     * @return
     */
    public static <T> List<List<T>> splitData(List<T> datas, int splitLen) {
        int size = datas.size() / splitLen;
        List<List<T>> result = new ArrayList<List<T>>(size + 1);
        for (int i = 0; i < size; i++) {
            List<T> item = new ArrayList<T>();
            item.addAll(datas.subList(i * splitLen, (i + 1) * splitLen));
            result.add(item);
        }

        if (datas.size() % splitLen > 0) {
            List<T> item = new ArrayList<T>();
            item.addAll(datas.subList(size * splitLen, datas.size()));
            result.add(item);
        }
        return result;
    }

    public static boolean isValidFileExt(String fileName, String reg) {
        boolean flag = false;
        try {
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(fileName.toLowerCase());
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * java变量命名规范的字符串转换为db字段命名规范的字符串， 如loginName 转换为 login_name
     * 
     * @param value
     * @return
     */
    public static String toDbField(String value) {
        if (isEmpty(value)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int length = value.length();
        for (int i = 0; i < length; i++) {
            char a = value.charAt(i);
            sb.append(Character.isUpperCase(a) ? "_" + Character.toLowerCase(a) : a);
        }
        return sb.toString();
    }

    public static List<Long> strings2Longs(List<String> ids) {
        List<Long> result = new ArrayList<>(ids.size());
        for (String id : ids) {
            result.add(Long.parseLong(id));
        }
        return result;
    }

    public static String list2String(List<String> values) {
        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if(sb.length() > 0) {
                sb.append(",");
            }
            sb.append(value);
        }
        return sb.toString();
    }

}
