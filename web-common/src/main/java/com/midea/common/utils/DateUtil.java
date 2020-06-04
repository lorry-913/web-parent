package com.midea.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");

	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
			"yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

    private final static SimpleDateFormat sdfTimes = new SimpleDateFormat(
            "yyyyMMddHHmmss");

	/**
	 * 获取YYYY格式
	 *
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	public static String getDateByPara (int para) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, para);
		String datepara = sdfDay.format(calendar.getTime());
		return datepara;
	}


    /**
     * 获取yyyyMMddHHmmss格式
     *
     * @return
     */
    public static String getTimes() {
        return sdfTimes.format(new Date());
    }

	/**
	 * 获取YYYY-MM-DD格式
	 *
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYYMMDD格式
	 *
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 *
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws
	 * @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 *
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 *
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	/**
	 * <li>功能描述：时间相减得到天数
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 * long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr,String endDateStr){
		long day=0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate= format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
		//System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 得到n天之后的日期
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = sdf.parse("2017-11-30 18:42:00");
			Date date2 = sdf.parse("2017-11-30 15:42:00");
			int a = differentDays(date1, date2);
			System.out.println(a);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static int getMealTime () {
		int result = 1;
		try {
			Date nowTime = new Date();
			String dateStr = sdfDay.format(nowTime);
			Date breakfastStart = sdfTime.parse(dateStr + " 04:00:00");
			Date breakfastEnd = sdfTime.parse(dateStr + " 09:29:59");

			Date lunchStart = sdfTime.parse(dateStr + " 09:30:00");
			Date lunchEnd = sdfTime.parse(dateStr + " 12:59:59");

			Date teaStart = sdfTime.parse(dateStr + " 13:00:00");
			Date teachEnd = sdfTime.parse(dateStr + " 16:59:59");

			Date supperStart = sdfTime.parse(dateStr + " 17:00:00");
			Date supperEnd = sdfTime.parse(dateStr + " 19:59:59");

			boolean type1 = belongCalendar(nowTime, breakfastStart, breakfastEnd);
			boolean type2 = belongCalendar(nowTime, lunchStart, lunchEnd);
			boolean type3 = belongCalendar(nowTime, teaStart, teachEnd);
			boolean type4 = belongCalendar(nowTime, supperStart, supperEnd);

			if (type1) {
				result = 1;
			} else if (type2) {
				result = 2;
			} else if (type3) {
				result = 3;
			} else if (type4) {
				result = 4;
			} else {
				result = 5;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * date2比date1多的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1= cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) { //同一年
			int timeDistance = 0 ;
			for (int i = year1; i < year2; i++) {
				if (i % 4==0 && i % 100 != 0 || i % 400 == 0) {   //闰年
					timeDistance += 366;
				} else {  //不是闰年
					timeDistance += 365;
				}
			}
			return timeDistance + (day2-day1) ;
		} else { //不同年
			return day2-day1;
		}
	}

    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysNew(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 == year2) { //同一年
            int timeDistance = 0 ;
            for (int i = year1; i < year2; i++) {
                if (i % 4==0 && i % 100 != 0 || i % 400 == 0) {   //闰年
                    timeDistance += 366;
                } else {  //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2-day1) ;
        } else if (year2 > year1) { //不同年
            return day2-day1;
        } else {
            int timeDistance = 0 ;
            for (int i = year2; i < year1; i++) {
                if (i % 4==0 && i % 100 != 0 || i % 400 == 0) {   //闰年
                    timeDistance += 366;
                } else {  //不是闰年
                    timeDistance += 365;
                }
            }
            return -(timeDistance + (day1-day2));
        }
    }

}
