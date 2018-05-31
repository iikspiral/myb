package com.sxca.myb.common.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil implements Serializable{

    /**
     * 字符串转换成日期
     * @param date
     * @param pattern
     * @return 得到字符串对应的java.util.Date
     */
    public static Date string2Date(String date, String pattern){
        if(date == null || "".equals(date.trim())){
            return null;
        }
        java.util.Date datVal = null;
        SimpleDateFormat simp = new SimpleDateFormat();
        try{
            simp.applyPattern(pattern);
            datVal = simp.parse(date);
        }catch(Exception e){
        	e.printStackTrace();
            return null;
        }
        return datVal;
    }

    /**
     * 日期转换成字符串
     * @param date
     * @param pattern
     * @return 得到Date对应的字符串（以pattern模式�?
     */
    public static String date2String(Date date, String pattern){
        if(date == null)
            return "";
        String datVal = null;
        SimpleDateFormat simp = new SimpleDateFormat();
        try{
            simp.applyPattern(pattern);
            datVal = simp.format(date);
        }catch(Exception e){
            return null;
        }

        return datVal;
    }

    /**
     * java.util.Date转换成java.util.Calendar类型
     * @param date java.util.Date类型的一个实�?
     * @return 得到java.util.Calendar类型的实�?
     */
    public static Calendar date2Calendlar(Date date){
        if(date == null){
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        return cal;
    }

    /**
     * java.util.Calendar转换成java.sql.Timestamp类型
     * @param cal java.util.Calendar类型的实�?
     * @return 得到java.sql.Timestamp类型的实�?
     */
    public static Timestamp calendlar2Date(Calendar cal){
        if(cal == null){
            return null;
        }
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * Calendar类型的日期转换成字符�?
     * @param cal 
     * @param pattern
     * @return pattern格式的cal对应的字符串
     */
    public static String calendar2String(Calendar cal, String pattern){
        if(cal == null){
            return null;
        }
        return date2String(calendlar2Date(cal), pattern);
    }

    /**
     * 取得月份的天�?
     * @param date
     * @return
     */
    public static int getMonthDayCount(Date date){
        if(date == null){
            return 0;
        }
        int reval = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        reval = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return reval;
    }

    /**
     * 取得月份的天�?
     * @param date
     * @return
     */
    public static int getMonthDayCount(String date, String pattern){
        int reval = 0;
        Date dat = string2Date(date, pattern);
        if(dat == null){
            return reval;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dat);
        reval = cal.getMaximum(Calendar.DAY_OF_MONTH);
        return reval;
    }

    /**
     * 两个日期间隔多少type（天�?
     * @param dateOne
     * @param dateTwo
     * @param type �?Calendar.DAY_OF_YEAR)，月(Calendar.MONTH)，年(Calendar.YEAR)
     * @return
     */
    public static int calTwoTimeSpace(Date dateOne, Date dateTwo, int type){
        int reVal = 0;
        int numOne = 0;
        int numTwo = 0;
        Calendar calOne = Calendar.getInstance();
        Calendar calTwo = Calendar.getInstance();
        calOne.setTime(dateOne);
        calTwo.setTime(dateTwo);
        numTwo = calTwo.get(type);
        numOne = calOne.get(type);
        
        reVal = numTwo - numOne;
        int two = calTwo.get(Calendar.YEAR);
        int one = calOne.get(Calendar.YEAR);
        if(type == Calendar.DAY_OF_YEAR){
            if(Math.abs(two - one) > 0){
                int day = 0;
                int min = two < one ? two : one; // 取较小的年份
                int max = two > one ? two : one; // 取较大的年份
                Calendar temp = Calendar.getInstance();
                for(int i = min; i < max; i++){ 
                    temp.set(Calendar.YEAR, i);
                    day += temp.getActualMaximum(Calendar.DAY_OF_YEAR);
                } // 得到整年相差的天�?
                if(two < one){
                    day *= -1;
                } // 判断正负
                reVal += day;
            }
        } // 在月份相差天数的基础上补整年相差的天�?
        else if(type == Calendar.MONTH){
            reVal += (two - one) * 12;
        } // 在相差的月份相的基础上补整年相差的月�?
        return reVal;
    }
    
    /**
     * 获取�?��月的�?���?��
     * @param date
     * @return
     */
    public static Date lastDayOfMonth(Date date) { 
        if(date == null){
            return null;
        }
        Calendar cal = Calendar.getInstance();      
        cal.setTime(date); 
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
        cal.set(Calendar.DAY_OF_MONTH, value); 
        return cal.getTime(); 
    }

    /**
     * 下个月的今天
     * @param date
     * @return
     */
    public static Date curDayOfNextMonth(Date date) {
        if(date == null){
            return null;
        }
        Calendar cal = Calendar.getInstance();      
        cal.setTime(date); 
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1); 
        return cal.getTime(); 
    }

	/**
	 * 获取当年的第一天
	 */
	public static Date getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		currCal.clear();
		currCal.set(Calendar.YEAR, currentYear);
		Date currYearFirst = currCal.getTime();
		return currYearFirst;
	}

	/**
	 * 获取当年的最后一天
	 */
	public static Date getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		currCal.clear();
		currCal.set(Calendar.YEAR, currentYear);
		currCal.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = currCal.getTime();

		return currYearLast;

	}

    /**
     * 取得date时间的前/后i天的日期函数
     * @param date
     * @param i i为正向后计算，i为负向前计算
     * @return
     */
    public static Date getSuchDayD(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, i);
        return calendar.getTime();
    }
    
    /**
     * 取得当前时间的前/后i天的日期函数
     * @param i i为正向后计算，i为负向前计算
     * @return
     */
    public static Date getSuchDayD(int i){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, i);
        return calendar.getTime();
    }
    
    /**
     * 取得date时间的前/后i天的日期函数
     * @param date
     * @param i i为正向后计算，i为负向前计算
     * @return
     */
    public static String getSuchDay(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, i);
        return date2String(calendar.getTime(), "yyyy-MM-dd");
    }

    /**
     * 取得当前时间的前/后i天的日期函数
     * @param i i为正向后计算，i为负向前计算
     * @return
     */
    public static String getSuchDay(int i){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, i);
        return date2String(calendar.getTime(), "yyyy-MM-dd");
    }

    /**
     * 取得当前日期
     * @return
     */
    public java.util.Date getCurrentDate(){
        return (java.util.Date) getCurrentTime();
    }

    /**
     * 取得当前时间字符
     * @param pattern
     * @return
     */
    public String getCurrentDateString(String pattern){
        String reDate = "";
        Timestamp date = getCurrentTime();
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(pattern);
        reDate = format.format(date);
        return reDate;
    }

    /**
     * 取得当前时间(可精确到毫秒)
     * @return
     */
    public Timestamp getCurrentTime(){
        return new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    /**
     * 将 BigDecimal 类型日期转为 Date 类型
     * @param bigDecimal
     * @return
     * @throws ParseException
     */
    public static Date typeToDate(BigDecimal bigDecimal)  throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssSSS");
        return sdf.parse(bigDecimal.toString());
    }
    /**
     * 得到当前的时间
     * @return yyyy-MM-dd 00:00:00
     */
    public static String getTheDate(){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String dateStr = sd.format(new Date());
        return dateStr;
   }

    /**
     * 得到本周周一 和 周日的 日期
     * @return
     */
    public static Map<String,String> getWeekDay() {
        Map<String,String> map = new HashMap<String,String>();
        Calendar cal =Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
        map.put("mon", df.format(cal.getTime()));
        //这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        //增加一个星期，才是我们中国人理解的本周日的日期
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        df = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        map.put("sun", df.format(cal.getTime()));
        return map;
    }

    public static Map<String,String> getMonthDate(){
        Map<String,String> map = new HashMap<String,String>();
        // 获取Calendar  
        Calendar calendar = Calendar.getInstance(); 
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");  
        // 设置时间,当前时间不用设置  
        // calendar.setTime(new Date());  
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE)); 
        map.put("monthF", format.format(calendar.getTime()));
        // 设置日期为本月最大日期  
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));  
        format = new SimpleDateFormat("yyyy-MM-dd 23:59:59"); 
        map.put("monthL", format.format(calendar.getTime()));
        return map;
    }
    
    /**
	  * 日期减法
	  */
	 public static int getDiffDay(Date before,Date after){
		 long beforetime=before.getTime();
		 long aftertime=after.getTime();
		 long time=aftertime-beforetime;
		 long day=time/1000/60/60/24;
		 return (int) day;
	 }
	 
    

    public static void main(String[] args) {
        System.out.println(getWeekDay());
    }
}
