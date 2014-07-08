package com.galaxy.exchange.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title:
 * </p>
 * <p/>
 * <p>
 * Description: 工作流子系统process
 * </p>
 * <p/>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p/>
 * <p>
 * Company: www.baidu.com
 * </p>
 * 
 * @author fangjianping
 * @version 1.0
 */
public final class DateUtils {

	public static final String DATEFORMATLONG = "yyyy-MM-dd HH:mm:ss";
	public static final String DATEFORMATMEDIUM = "yyyy-MM-dd HH:mm";
	public static final String DATEFORMATSHORT = "yyyy-MM-dd";
	public static final String DATEFORMATSHORT_SLASH = "yyyy_MM_dd";
	public static final String DATEFORDATESEARCH = "yyyyMMdd";
	public static final String DATEFORTIMESEARCH = "yyyyMMddHHmm";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String HHMM = "HH:mm";
	public static final String DATEFORMATMEDIUMSPLASH = "yyyy/MM/dd HH:mm";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * DOCUMENT ME!
	 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * DOCUMENT ME!
	 */
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	/**
	 * DOCUMENT ME!
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	/**
	 * DOCUMENT ME!
	 */
	public static final String YYYYMMDD = "yyyyMMdd";

	/**
	 * DOCUMENT ME!
	 */
	public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";

	public static final String YYYYMMDD_HH = "yyyyMMdd_HH";
	public static final String YYYY_MM_DD_00_00_00 = "yyyy-MM-dd 00:00:00";

	/**
	 * DOCUMENT ME!
	 */
	public static String today;

	/**
	 * DOCUMENT ME!
	 */
	public static String yesterday;

	private DateUtils() {
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static Date getYesterdayDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}

	public static Date getNMonthDate(int n) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, n);
		return c.getTime();

	}

	public static Date getNWeekDate(int n) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, n);
		return c.getTime();

	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param formatDate
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static String getCurrentFormatDate(String formatDate) {
		Date date = getCurrentDate();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);

		return simpleDateFormat.format(date);
	}

	public static String getCurrentFormatDate() {
		Date date = getCurrentDate();
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
	}

	/**
	 * DOCUMENT ME!
	 */
	public static void resetToday() {
		String todayStr = getDate2String("yyyy-MM-dd", getCurrentDate());

		if ((today == null) || !today.equals(todayStr)) {
			today = todayStr;
			yesterday = getDate2String("yyyy-MM-dd", getYesterdayDate());
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param format
	 *            DOCUMENT ME!
	 * @param date
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static final String getDate2String(String format, Date date) {
		if (date != null) {
			if (StringUtils.isEmpty(format)) {
				format = YYYY_MM_DD_HH_MM_SS;
			}

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

			return simpleDateFormat.format(date);
		} else {
			return "";
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param date
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static String getDate2String(Date date) {
		return getDate2String("", date);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param l
	 *            DOCUMENT ME!
	 * @param formatDate
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static String getLong2ShortString(long l, String formatDate) {
		Date date = getLong2Date(l);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);

		return simpleDateFormat.format(date);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param format
	 *            DOCUMENT ME!
	 * @param str
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static Date getString2Date(String format, String str) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		ParsePosition parseposition = new ParsePosition(0);

		return simpleDateFormat.parse(str, parseposition);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param str
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static Date getString2Date(String str) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD);
		ParsePosition parseposition = new ParsePosition(0);
		return simpleDateFormat.parse(str, parseposition);
	}

	/**
	 * 制定locale的转换方式
	 * 
	 * @param format
	 * @param locale
	 * @param dateStr
	 * @return
	 */
	public static Date getString2Date(String format, Locale locale, String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, locale);

		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 尝试解析，如果出错就返回null
	 * 
	 * @param format
	 * @param dateStr
	 * @return 正常解析的话，就返回一个date，否则返回null
	 */
	public static Date tryParseString2Date(String format, String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param l
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static Date getLong2Date(long l) {
		return new Date(l);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param l
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static int getOffDays(long l) {
		return getOffDays(l, getCurrentTimeMillis());
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param from
	 *            DOCUMENT ME!
	 * @param to
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static int getOffDays(long from, long to) {
		return getOffMinutes(from, to) / 1440;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param l
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static int getOffMinutes(long l) {
		return getOffMinutes(l, getCurrentTimeMillis());
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param from
	 *            DOCUMENT ME!
	 * @param to
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static int getOffMinutes(long from, long to) {
		return (int) ((to - from) / 60000L);
	}

	public static String getLastNQuarterBeginDate(int n) {
		return getLastNQuarterBeginDate(n, new SimpleDateFormat("yyyy-MM-dd"));
	}

	public static String getLastNQuarterBeginDate(int n, DateFormat format) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, n * 3);

		int q = getQuarter(c.get(Calendar.MONTH));

		c.set(Calendar.MONTH, 3 * (q - 1));
		c.set(Calendar.DATE, c.getActualMinimum(Calendar.DATE));
		return format.format(c.getTime());
	}

	public static String getLastNQuarterEndDate(int n) {
		return getLastNQuarterEndDate(n, new SimpleDateFormat("yyyy-MM-dd"));
	}

	private static int getQuarter(int month) {
		if (month <= 2 && month >= 0)
			return 1;
		if (month <= 5 && month >= 3)
			return 2;
		if (month <= 8 && month >= 6)
			return 3;
		if (month <= 11 && month >= 9)
			return 4;
		throw new IllegalStateException("月份错误");
	}

	public static String getLastNQuarterEndDate(int n, DateFormat format) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, n * 3);

		int q = getQuarter(c.get(Calendar.MONTH));

		c.set(Calendar.MONTH, 2 + 3 * (q - 1));

		c.set(Calendar.DATE, c.getMaximum(Calendar.DAY_OF_MONTH));

		return format.format(c.getTime());
	}

	public static String getLastNMonthBeginDate(int n, DateFormat format) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, n);
		c.set(Calendar.DATE, c.getMinimum(Calendar.DAY_OF_MONTH));
		return format.format(c.getTime());
	}

	public static String getLastNMonthBeginDate(int n) {
		return getLastNMonthBeginDate(n, new SimpleDateFormat("yyyy-MM-dd"));
	}

	public static String getLastNMonthEndDate(int n) {
		return getLastNMonthEndDate(n, new SimpleDateFormat("yyyy-MM-dd"));
	}

	public static String getLastNMonthEndDate(int n, DateFormat format) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, n);
		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));

		return format.format(c.getTime());
	}

	public static String getLastNYearBeginDate(int n) {
		return getLastNYearBeginDate(n, new SimpleDateFormat("yyyy-MM-dd"));
	}

	public static String getLastNYearBeginDate(int n, DateFormat format) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, n);
		c.set(c.get(Calendar.YEAR), 0, 1);
		return format.format(c.getTime());
	}

	public static String getLastNYearEndDate(int n) {
		return getLastNYearEndDate(n, new SimpleDateFormat("yyyy-MM-dd"));
	}

	public static String getLastNYearEndDate(int n, DateFormat format) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, n);
		c.set(c.get(Calendar.YEAR), 11, 31);
		return format.format(c.getTime());
	}
}
