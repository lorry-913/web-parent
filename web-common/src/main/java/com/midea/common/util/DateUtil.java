package com.midea.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getDatefromStr(String dateStr) {
		Date date = null;
		try {
			if (dateStr != null && !"".equals(dateStr)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.parse(dateStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			date = null;
		}
		return date;
	}

	//2018-08-05T16:00:00.000Z
	public static String getDateFormat1(String create_time) throws ParseException{
		create_time = create_time.replace("Z", " UTC");//注意是空格+UTC
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");//注意格式化的表达式
		Date d = sformat.parse(create_time );
		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(d);
		return dateStr;
	}

	public static Date getDatefromStrHHMMSS(String dateStr) {
		Date date = null;
		try {
			if (dateStr != null && !"".equals(dateStr)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				date = sdf.parse(dateStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			date = null;
		}
		return date;
	}
	public static String getFromatStr(Date date, String destFormat) {
		if (date == null)
			return null;
		DateFormat dest = new SimpleDateFormat(destFormat);
		return dest.format(date);
	}

	public static Date getDatefromStryyyymmdd(String dateStr) {
		Date date = null;
		try {
			if (dateStr != null && !"".equals(dateStr)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				date = sdf.parse(dateStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			date = null;
		}
		return date;
	}

	public static String getDateYYYY_MM_dd(Date date) {
		String dateStr = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dateStr = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			dateStr = "";
		}
		return dateStr;
	}

	public static String getDateYYYY_MM_dd_hh(Date date) {
		String dateStr = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateStr = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			dateStr = "";
		}
		return dateStr;
	}

	public static String getDateYYYYMMdd(Date date) {
		String dateStr = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			dateStr = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			dateStr = "";
		}
		return dateStr;
	}

	public static String getDateYYYYMMddHHMMSS(Date date) {
		String dateStr = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateStr = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			dateStr = "";
		}
		return dateStr;
	}

	public static String getPushDate(Date date) {
		String dateStr = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			dateStr = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			dateStr = "";
		}
		return dateStr;
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetweenFromDate(Date smdate, Date bdate)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算 两时间差多少天
	 */
	public static int daysBetweenFromStr(String smdate, String bdate)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	public static int differentDays (String smdate, String bdate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(sdf.parse(smdate));

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(sdf.parse(bdate));
		int day1= cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) {
			int timeDistance = 0 ;
			for (int i = year1; i < year2; i++) {
				if (i%4 == 0 && i%100 != 0 || i%400 == 0) {
					timeDistance += 366;
				} else {
					timeDistance += 365;
				}
			}
			return timeDistance + ( day2 - day1) ;
		} else {
			return day2-day1;
		}
	}

	/**
	 *  判断两个日期天数差
	 */
	public static int daysBetweenFromPara(String smdate, String bdate)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		int time1 = cal.get(Calendar.DAY_OF_YEAR);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(sdf.parse(bdate));
		int time2 = cal1.get(Calendar.DAY_OF_YEAR);
		int between_days = time2 - time1;
		return between_days;
	}

	/**
	 *  判断两个日期天数差
	 */
	public static int daysBetweenFromHour(String smdate, String bdate)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = sdf.parse(smdate);
	    long timeStemp1 = date1.getTime();
	    Date date2 = sdf.parse(bdate);
	    long timeStemp2 = date2.getTime();
	    int days = 0;
	    long timestemp = (timeStemp2 - timeStemp1) /1000 / 3600;
//		return  Math.abs(new Long(timestemp).intValue() / 24);

		if (Math.abs(timestemp % 24) == 0) {
			days = Math.abs(new Long(timestemp).intValue() / 24);
		} else {
			days = Math.abs(new Long(timestemp).intValue() / 24) + 1;
		}
		return days;
	}

	/**
	 * 当前时间之后N天
	 *
	 * @param n
	 * @return
	 */
	public static String afterNDay(int n) {
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		c.setTime(new Date());
		c.add(Calendar.DATE, n);
		Date d2 = c.getTime();
		String s = df.format(d2);
		return s;
	}

	/**
	 * 得到本日的前几个月时间 如果number=2当日为2007-9-1,那么获得2007-7-1
	 */
	public static String getDateBeforeMonth(int number) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -number);
		return getDateYYYY_MM_dd(cal.getTime());
	}

	/**
	 * 系统毫秒转日期
	 *
	 * @param millis
	 * @return
	 */
	public static Date getDateByMillis(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/**
	 * 系统毫秒获取当前小时值
	 *
	 * @param millis
	 * @return
	 */
	public static int getHourByMillis(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 系统毫秒获取当前小时值
	 *
	 * @param millis
	 * @return
	 */
	public static int getMinuteByMillis(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * string to 毫秒
	 *
	 * @param
	 * @return
	 */
	public static long getMillisByDate(String dateStr) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss.SSS");
			Date date = sdf.parse(dateStr);
			calendar.setTime(date);
			return calendar.getTimeInMillis();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 *
	 * @return
	 */
	public static long getCurrentSec() {
		return System.currentTimeMillis() / 1000;
	}

	public static long getToday() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis() / 1000;
	}
	/**
	 * 获取两时间相差小时数
	 *
	 * @return
	 */
	public static long getDifferHours(Date str1, Date str2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long hours = 0L;
		try {
			Date d1 = df.parse(df.format(str1));
			Date d2 = df.parse(df.format(str2));
			long diff = d1.getTime() - d2.getTime();
			hours = diff / (1000 * 60 * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hours;
	}

	public static long getDifferMinutes(Date str1, Date str2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long minutes = 0L;
		try {
			Date d1 = df.parse(df.format(str1));
			Date d2 = df.parse(df.format(str2));
			long diff = d1.getTime() - d2.getTime();
			minutes = diff / (1000 * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return minutes;
	}

	/**
	 * 当前日期零点
	 *
	 * @param n
	 * @return
	 */
	public static Date getZero(Date d) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String s = df.format(d);
		Date date = null;
		try {
			date = df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 当前月零点
	 *
	 * @param n
	 * @return
	 */
	public static Date getMothZero(Date d) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-01 00:00:00");
		String s = df.format(d);
		Date date = null;
		try {
			date = df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}


	/**
	 * 得到本周周一
	 *
	 * @return yyyy-MM-dd
	 */
	public static Date getMondayOfThisWeek(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return c.getTime();
	}

	public static String getTimeByMinuteBefore(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}

	public static long getDifferSecond(Date str1, Date str2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long second = 0L;
		try {
			Date d1 = df.parse(df.format(str1));
			Date d2 = df.parse(df.format(str2));
			long diff = d1.getTime() - d2.getTime();
			second = diff / (1000 * 60 * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return second;
	}

	public static void main(String[] args) {
		String yday = "2017-08-09 19:52:37";
		String tday = "2017-08-10 09:52:37";
		int mi = 0;
		int lessDate = 0;
		try {
			mi = daysBetweenFromPara(yday, tday);
			lessDate = 3 - mi;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mi);
		System.out.println(lessDate);
	}
}
