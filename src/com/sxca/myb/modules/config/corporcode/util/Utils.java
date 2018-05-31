package com.sxca.myb.modules.config.corporcode.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {

	public static int getDiffDay(String startTime, String endTime, String format)
			throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		// long nh = 1000*60*60;//一小时的毫秒数
		// long nm = 1000*60;//一分钟的毫秒数
		// long ns = 1000;//一秒钟的毫秒数
		long diff = 0;
		diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
		long day = diff / nd;// 计算差多少天
		return (int) day;
	}

	public static String to_Formatdate(String datestr) {
		// String format1="yyyyMMddHHmmssSSS";
		SimpleDateFormat formatter = new SimpleDateFormat("SSS");
		String s = datestr.replaceAll("-", "").replaceAll(" ", "")
				.replaceAll(":", "")
				+ formatter.format(new Date());
		return s;
	}

	/**
	 * 判断日期是否过期
	 * 
	 * @param sxsj
	 *            失效时间
	 * @param nowtime
	 *            当前时间
	 * @return
	 */
	public static int checkIsSx(String sxsj, String nowtime) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		long diff = 0;
		try {
			diff = sd.parse(nowtime).getTime() - sd.parse(sxsj).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (diff <= 0) {
			return 0;// 有效
		}
		return 1;// 失效
	}


	public static Timestamp calendlar2Date(Calendar cal) {
		if (cal == null)
			return null;

		return new Timestamp(cal.getTimeInMillis());
	}

	public static Date string2Date(String date, String pattern) {
		if (date == null || date.equals(""))
			return null;
		Date datVal = null;
		SimpleDateFormat simp = new SimpleDateFormat();
		try {
			simp.applyPattern(pattern);
			datVal = simp.parse(date);
		} catch (Exception e) {
			return null;
		}
		return datVal;
	}

	public static Calendar date2Calendlar(Date date) {
		if (date == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		return cal;
	}

	public static String date2String(Date date, String pattern) {
		if (date == null)
			return "";
		String datVal = null;
		SimpleDateFormat simp = new SimpleDateFormat();
		try {
			simp.applyPattern(pattern);
			datVal = simp.format(date);
		} catch (Exception e) {
			return null;
		}

		return datVal;
	}


	/**
	 * 将 字符串格式的日期 转为 数据库中 的数字格式
	 * 
	 * @param data
	 *            需要转换的字符串日期
	 * @param fmt
	 *            需要转换的字符串日期格式
	 * @return 转换后的数字日期，起始日期
	 */
	public static BigDecimal TypeStartDate(String date, String fmt) {
		BigDecimal b = null;
		if (date == null || "".equals(date)) {
			return null;
		}
		try {
			SimpleDateFormat sd = new SimpleDateFormat(fmt);
			Date d = sd.parse(date);
			if (fmt.length() == 10) {
				sd = new SimpleDateFormat("yyyyMMdd");
				String rt = sd.format(d) + "000000000";
				b = new BigDecimal(rt);
			} else {
				sd = new SimpleDateFormat("yyyyMMddHHmm");
				String rt = sd.format(d) + "00000";
				b = new BigDecimal(rt);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 将 字符串格式的日期 转为 数据库中 的数字格式
	 * 
	 * @param data
	 *            需要转换的字符串日期
	 * @param fmt
	 *            需要转换的字符串日期格式
	 * @return 转换后的数字日期，结束日期
	 */
	public static BigDecimal TypeEndDate(String date, String fmt) {
		BigDecimal b = null;
		if (date == null || "".equals(date)) {
			return null;
		}
		try {
			SimpleDateFormat sd = new SimpleDateFormat(fmt);
			Date d = sd.parse(date);
			if (fmt.length() == 10) {
				sd = new SimpleDateFormat("yyyyMMdd");
				String rt = sd.format(d) + "999999999";
				b = new BigDecimal(rt);
			} else {
				sd = new SimpleDateFormat("yyyyMMddHHmm");
				String rt = sd.format(d) + "99999";
				b = new BigDecimal(rt);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 获取今天的日期的字符串格式
	 * 
	 * @param fmt
	 *            字符串格式
	 * @return 今天的起始
	 */
	public static String TypeTadayDate(String fmt) {
		return (new SimpleDateFormat(fmt)).format(new Date());
	}

	/**
	 * 获取今天的开始时间
	 * 
	 * @return 今天日期起始，符合数据库的数字类型
	 */
	public static BigDecimal TypeTadayStartDate() {
		return new BigDecimal(
				(new SimpleDateFormat("yyyyMMdd")).format(new Date())
						+ "000000000");
	}

	/**
	 * 获取今天的结束时间
	 * 
	 * @return 今天日期结束，符合数据库的数字类型
	 */
	public static BigDecimal TypeTadayEndDate() {
		return new BigDecimal(
				(new SimpleDateFormat("yyyyMMdd")).format(new Date())
						+ "999999999");
	}

	/**
	 * 将数据库中的数字日期，转为毫秒类型日期
	 * 
	 * @return 毫秒类型的数字日期
	 */
	public static BigDecimal TypeDate(BigDecimal bigDecimal) {
		BigDecimal r = null;
		try {
			r = new BigDecimal((new SimpleDateFormat("yyyyMMddHHmmssS")).parse(
					bigDecimal.toString()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 把一个List对象转换为String字符串
	 * 
	 * @param stringList
	 *            字符串list
	 * @param rule
	 *            分割标记
	 * @return
	 */
	public static String listToString(List<String> stringList, String rule) {
		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append("rule");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}

	/**
	 * 把BigDecimal格式的日期转为String日期
	 * 
	 * @param date
	 * @return
	 */
	public static String BigDecimaldate2String(String BigDecimaldate) {
		String str = BigDecimaldate;
		str = str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
				+ str.substring(6, 8) + " " + str.substring(8, 10) + ":"
				+ str.substring(10, 12) + ":" + str.substring(12, 14);
		return str;
	}
	
	
	public static Date BigDecimal2Date(BigDecimal BigDecimaldate) {
		Date date=null;
		String datestr=BigDecimaldate2String(BigDecimaldate.toPlainString());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
			date=new Date();
		}
		return date;
	}
	
}
