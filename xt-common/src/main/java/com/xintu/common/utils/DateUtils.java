package com.xintu.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;


/**
 * @author kyon
 *
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	public static String[] DATE_FORMATS;
    static{
    	DATE_FORMATS = new String[10];
    	DATE_FORMATS[0] = "yyyyMMdd";
    	DATE_FORMATS[1] = "yyyy-MM-dd";
    	DATE_FORMATS[2] = "yyyyMMdd HHmm";
    	DATE_FORMATS[3] = "yyyy-MM-dd HHmm";
    	DATE_FORMATS[4] = "yyyyMMdd HHmmss";
    	DATE_FORMATS[5] = "yyyy-MM-dd HHmmss";
    	DATE_FORMATS[6] = "yyyyMMdd HH:mm:ss";
    	DATE_FORMATS[7] = "yyyy-MM-dd HH:mm:ss";
    	DATE_FORMATS[8] = "yyyy-MM-dd'T'HH:mm";
    	DATE_FORMATS[9] = "yyyyMMdd-HHmm";
    }

    /**
     * 将字符串转换成Date类型，使用用内置的dateFormat
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parseDateUsingDefaultFormats(String dateStr) throws ParseException{
    	if(StringUtils.isBlank(dateStr))return null;
    	return parseDate(dateStr, DATE_FORMATS);
    }
    
    
	/**
	 * 转化数字月份为3位英文月份
	 * 
	 * @param month
	 * @return
	 */
	public static String monthNumToEn(String month) {
		String mon = null;
		int monthInt = Integer.valueOf(month).intValue();
		switch (monthInt) {
		case 1:
			mon = "Jan";
			break;
		case 2:
			mon = "Feb";
			break;
		case 3:
			mon = "Mar";
			break;
		case 4:
			mon = "Apr";
			break;
		case 5:
			mon = "May";
			break;
		case 6:
			mon = "Jun";
			break;
		case 7:
			mon = "Jul";
			break;
		case 8:
			mon = "Aug";
			break;
		case 9:
			mon = "Sep";
			break;
		case 10:
			mon = "Oct";
			break;
		case 11:
			mon = "Nov";
			break;
		case 12:
			mon = "Dec";
			break;
		default:
			mon = "";
			break;
		}
		return mon;
	}
	
	/**
	 * 工具方法，转化票价日期成为av查询格式的日期
	 * 
	 * @param month
	 */
	public static int monthEnToNum(String month) {
		String monthString = "JAN|FEB|MAR|APR|MAR|JUN|JUL|AUG|SEP|OCT|NOV|DEC";
		int mon = 1;
		int monthInt = monthString.indexOf(month.toUpperCase()) / 4;
		switch (monthInt) {
		case 0:
			mon = 1;
			break;
		case 1:
			mon = 2;
			break;
		case 2:
			mon = 3;
			break;
		case 3:
			mon = 4;
			break;
		case 4:
			mon = 5;
			break;
		case 5:
			mon = 6;
			break;
		case 6:
			mon = 7;
			break;
		case 7:
			mon = 8;
			break;
		case 8:
			mon = 9;
			break;
		case 9:
			mon = 10;
			break;
		case 10:
			mon = 11;
			break;
		case 11:
			mon = 12;
			break;
		}
		return mon;
	}
	
	/**
	 * 计算两个日期的差，结果以日数表示
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getDateDiffer(Date start, Date end, TimeUnit tu){
		long e = end.getTime();
		long s = start.getTime();
		return tu.convert(e-s, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 将一个字符串转换成想要的日期格式
	 * @param date
	 * @param format
	 * @return dateStr
	 */
	public static String format(String date, String pattern) throws ParseException {
		Date d = parseDateUsingDefaultFormats(date);
		return format(d, pattern);
	}
	
	/**
	 * 将一个date按给定的格式转换成字符串
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String format(Date date, String pattern) throws ParseException {		
		return format(date, pattern, null);
	}
	
	/**
	 * 将一个date按给定的格式转换成字符串
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String format(String date, String pattern, Locale locale) throws ParseException {
		Date d = parseDateUsingDefaultFormats(date);
		return format(d, pattern, locale);
	}
	
	/**
	 * 将一个date按给定的格式转换成字符串
	 * @param depDate
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String format(Date depDate, String pattern, Locale locale) throws ParseException {
		SimpleDateFormat sdf = null;
		if(locale!=null){
			sdf = new SimpleDateFormat(pattern, locale);
		}else{
			sdf = new SimpleDateFormat(pattern);
		}
		return sdf.format(depDate);
	}
	
	public static Date ConverDateGMT(Date date,String sourceTimeZone,String targetTimeZone) throws ParseException
	 {
	  Calendar cal = Calendar.getInstance();
	  TimeZone timeZone = cal.getTimeZone();
	  if(sourceTimeZone==null){
		sourceTimeZone = timeZone.getID();
	  }
	  //获取传入的时间值
	  Long time = date.getTime();
	  //获取源时区时间相对的GMT时间
	  Long sourceRelativelyGMT=time-TimeZone.getTimeZone(sourceTimeZone).getRawOffset();
	  //GMT时间+目标时间时区的偏移量获取目标时间
	  Long targetTime=sourceRelativelyGMT+TimeZone.getTimeZone(targetTimeZone).getRawOffset();
	  
	  date= new Date(targetTime);
	  //返回目标时区的时间
	  return date;
	  
	 }
	
	
	/**
	 * 字符串转为日期
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		try {
			java.text.DateFormat df = new java.text.SimpleDateFormat(format);			
			String dt = dateStr;
			if ((!dt.equals("")) && (dt.length() < format.length())) {
				dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]","0");
			}
			date = (Date) df.parse(dt);
		} catch (Exception e) {

		}
		return date;
	}
}
