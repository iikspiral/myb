package com.sxca.myb.common.BTK.stringutilBTK;

import java.math.BigDecimal;

/**
 * 在BTK业务中将String类型转换为其他类型的工具类
 * Created by admin on 2017/3/28.
 */
public class StringUtilBTK {


    /**
     * 把String对象转换为Boolean对象，如String为空，则返回false
     * @param str
     *           String对象
     * @return
     */
    public static Boolean convertBoolean(String str) {
            return convertBoolean(str, false);
    }

    /**
     * 把String对象转换为Boolean对象，如String为空，则返回def
     *
     * @param str
     *            String对象
     * @param def
     *            默认值
     * @return
     */
    public static Boolean convertBoolean(String str, Boolean def) {
        if (str != null&&!"".equals(str)&&!"null".equals(str)) {
            return Boolean.valueOf(str);
        } else {
            return def;
        }
    }
    /**
     * 把String对象转换为Integer对象，如String为空，则返回0
     *
     * @param str
     *            String对象
     * @return
     */
    public static Integer convertInteger(String str) {
        return convertInteger(str, 0);
    }

    /**
     * 把String对象转换为Integer对象，如String为空，则返回def
     *
     * @param str
     *            String对象
     * @param def
     *            默认值
     * @return
     */
    public static Integer convertInteger(String str, Integer def) {

        if (str != null&&!"".equals(str)&&!"null".equals(str)) {
            return Integer.valueOf(str);
        } else {
            return def;
        }
    }

    /**
     * 把String对象转换为Long对象，如String为空，则返回0L
     *
     * @param str
     *            String对象
     * @return
     */
    public static Long convertLong(String str) {
        return convertLong(str, 0L);
    }

    /**
     * 把String对象转换为Long对象，如String为空，则返回def
     *
     * @param str
     *            String对象
     * @param def
     *            默认值
     * @return
     */
    public static Long convertLong(String str, Long def) {
        if (str != null&&!"".equals(str)&&!"null".equals(str)) {
            return Long.valueOf(str);
        } else {
            return def;
        }
    }

    /**
     * 把String对象转换为Float对象，如String为空，则返回0.0F
     *
     * @param str
     *            String对象
     * @return
     */
    public static Float convertFloat(String str) {
        return convertFloat(str, 0.0F);
    }

    /**
     * 把String对象转换为Float对象，如String为空，则返回def
     *
     * @param str
     *            String对象
     * @param def
     *            默认值
     * @return
     */
    public static Float convertFloat(String str, Float def) {
        if (str != null&&!"".equals(str)&&!"null".equals(str)) {
            return Float.valueOf(str);
        } else {
            return def;
        }
    }

    /**
     * 把String对象转换为Double对象，如String为空，则返回0.0
     *
     * @param str
     *            String对象
     * @return
     */
    public static Double convertDouble(String str) {
        return convertDouble(str, 0.0);
    }

    /**
     * 把String对象转换为Double对象，如String为空，则返回def
     *
     * @param str
     *            String对象
     * @param def
     *            默认值
     * @return
     */
    public static Double convertDouble(String str, Double def) {
        if (str != null&&!"".equals(str)&&!"null".equals(str)) {
            return Double.valueOf(str);
        } else {
            return def;
        }
    }
    /**
     * 把String对象转换为Timestamp对象，如String为空，则返回null
     *
     * @param str
     *            String对象
     * @return
     */
    public static BigDecimal convertBigDecimal(String str) {
        return convertBigDecimal(str, null);
    }

    /**
     * 把String对象转换为Timestamp对象，如String为空，则返回null
     *
     * @param str
     *            String对象
     * @param def
     *            默认值
     * @return
     */
    public static BigDecimal convertBigDecimal(String str, BigDecimal def) {
        if (str != null&&!"".equals(str)&&!"null".equals(str)) {
            return new BigDecimal(str);
        } else {
            return def;
        }
    }


}
