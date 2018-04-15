package com.caoyunhao.petshop.common.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatetimeUtil {

    /**
     * 默认日期时间格式
     */
    public static final String COMMON_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认日期格式
     */
    public static final String COMMON_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 默认DateFormat
     */
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 为一个Date对象加上指定的秒数
     */
    public static Date addSeconds(Date date, int secondNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, secondNum);
        return calendar.getTime();
    }

    /**
     * 日期操作，日期加上n天
     */
    public static Date addDay(Date date, int dayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, dayNum);
        return calendar.getTime();
    }

    /**
     * 根据当前日期时间得到当天凌晨时间
     */
    public static Date setZeroAddDay(Date date, int dayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, dayNum);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 日期操作，月份加上n月
     */
    public static Date addMonth(Date month, int monthNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(month);
        calendar.add(Calendar.MONTH, monthNum);
        return calendar.getTime();
    }

    /**
     * 获取凌晨时间并增加monthNum个月
     */
    public static Date setZeroAddMonth(Date date, int monthNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, monthNum);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 日期操作，时间变为当月第1天
     */
    public static Date setDayOfMonth(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 返回当月月初的时间
     */
    public static Date getThisMonthStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取上个季度的开始时间
     */
    public static Date getLastQuarterStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, ((int) calendar.get(Calendar.MONTH) / 3 - 1) * 3);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取本季度的开始时间
     */
    public static Date getThisQuarterStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, ((int) calendar.get(Calendar.MONTH) / 3) * 3);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取上个季度的结束时间
     */
    public static Date getLastQuarterEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, ((int) calendar.get(Calendar.MONTH) / 3 - 1) * 3 + 2);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取时间戳
     */
    public static String getQuickTimestamp() {
        return Long.toString(System.currentTimeMillis());
    }

    /**
     * 获取当前紧凑的时间
     */
    public static String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    /**
     * 转换时间格式
     */
    public static String toFormatDateTimeString(Date date) {
        return toFormatString(date, COMMON_DATETIME_FORMAT);
    }

    /**
     * 转换时间格式
     */
    public static String toFormatDateString(Date date) {
        return toFormatString(date, COMMON_DATE_FORMAT);
    }

    /**
     * 转换时间格式
     */
    public static String toFormatString(Date date, String dateFormat) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * 返回所选日期0点时间
     */
    public static Date getStartTime(Date date) {
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }

    /**
     * 返回今天0点时间
     */
    public static Date getTodayStartTime() {
        Date date = new Date();
        return getStartTime(date);
    }

    /**
     * 返回本月开始时间
     */
    public static Date getMonthStartTime() {
        Date date = new Date();
        date.setDate(1);
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }

    /**
     * 返回上个周日的日期
     */
    public static Date getLastSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return addDay(calendar.getTime(), -7);
    }

    /**
     * 返回年
     */
    public static String getYearStr(Date date) {
        return toFormatString(date, "yyyy");
    }

    /**
     * 返回年月
     */
    public static String getYearMonthStr(Date date) {
        return toFormatString(date, "yyyy-MM");
    }

    /**
     * 返回年周
     */
    public static String getYearWeekStr(Date date) {
        return toFormatString(date, "yyyy w");
    }

    /**
     * 返回年月日
     */
    public static String getDateStr(Date date) {
        return toFormatString(date, "yyyy-MM-dd");
    }

    /**
     * 返回年月日时
     */
    public static String getDateHourStr(Date date) {
        return toFormatString(date, "yyyy-MM-dd HH");
    }

    /**
     * 返回年月日时分
     */
    public static String getDateHourMinuteStr(Date date) {
        return toFormatString(date, "yyyy-MM-dd HH:mm");
    }

    /**
     * 返回年月日时分秒
     */
    public static String getDateHourSecondStr(Date date) {
        return toFormatString(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据字符串返回日期
     */
    public static Date getDate(String dateStr) throws ParseException {
        return DATE_FORMAT.parse(dateStr);
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取指定日期类型时间戳
     *
     * @param date
     * @return
     */
    public static Timestamp getTimeStamp(Date date) {
        return new Timestamp(date.getTime());
    }

}
