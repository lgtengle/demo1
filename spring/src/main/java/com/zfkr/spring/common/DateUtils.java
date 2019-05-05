package com.zfkr.spring.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 时间工具类
 *
 * @author panghao
 */
public class DateUtils {

    public static final String DATE_FORMAT_STR_Y_M = "yyyy/MM";
    public static final String DATE_FORMAT_STR_YM = "yyyyMM";
    public static final String CN_DATE_FORMAT_STR_YM = "yyyy年MM月";

    public static final String DATE_FORMAT_STR = "yyyy/MM/dd";
    public static final String DATE_FORMAT_STR_YMD = "yyyyMMdd";
    public static final String CN_DATE_FORMAT_STR = "yyyy年MM月dd日";
    public static final String DATETIME_FORMAT_YMD = "yyyy-MM-dd";

    public static final String DATETIME_FORMAT_STR = "yyyy/MM/dd HH:mm:ss";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT_STR_YMDHMS = "yyyyMMddHHmmss";
    public static final String CN_DATETIME_FORMAT_STR = "yyyy年MM月dd日 HH时mm分ss秒";

    public static final String DATETIME_FORMAT_STR_YMD_HM = "yyyy/MM/dd HH:mm";
    public static final String DATETIME_FORMAT_STR_YMDHM = "yyyyMMddHHmm";
    public static final String CN_DATETIME_FORMAT_STR_YMDHM = "yyyy年MM月dd HH时mm分";

    /**
     * 获取当前日期
     * @return
     *
     */
    public static String getCurrentDate() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_FORMAT_STR);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 获取当前日期时间
     * @return
     *
     */
    public static String getCurrentDateTime() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATETIME_FORMAT_STR);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 获取当前日期时间
     * @return
     *
     */
    public static String getCurrentDateTime(String Dateformat) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(Dateformat);
        datestr = df.format(new Date());
        return datestr;
    }
    /**
     * 格式化指定的Date时间
     * @param date 指定时间
     * @param formatStr 时间字符串格式
     * @return 指定的时间为空返回null,否则返回格式化的时间字符串
     */
    public static String format(Date date, String formatStr){
        if (date == null){
            return null;
        }
        if ("--".equals(date.toString())){
            return date.toString();
        }
        return new SimpleDateFormat(formatStr).format(date);
    }

    /**
     * 获取默认格式的时间
     * 默认格式为 yyyy-MM-dd HH:mm:ss
     * @param dateStr 指定格式的时间字符串
     * @return 解析后时间
     */
    public static Date parse(String dateStr){
        return parse(dateStr,DATETIME_FORMAT_STR);
    }

    /**
     * 解析指定格式的时间
     * dateStr的格式必须与formatStr指定的格式一致
     * @param dateStr 时间字符串
     * @param formatStr 时间字符串格式
     * @return 返回解析后时间,如格式不符合捕获到解析异常,抛出运行时异常
     */
    public static Date parse(String dateStr, String formatStr){
        if (dateStr == null || dateStr.isEmpty()){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            String msg = "日期格式错误:"+dateStr+"无法转化"+formatStr;
            throw new RuntimeException(msg);
        }
    }

    /**
     * 获取dateStr所在周每一天的日期
     * 默认yyyy-MM-dd
     * @param dateStr 指定格式的时间字符串
     * @return 指定日期所在周每天的日期
     */
    public static Date[] getWeekDay(String dateStr){
        Calendar calendar = setCalendarTime(parse(dateStr,DATE_FORMAT_STR));
        setMonday(calendar);
        return getDates(calendar,7);
    }

    /**
     * 获取指定日期后指定天数(包括指定日期)
     * @param calendar 指定日期的calendar对象
     * @param dateLenght 需要的天数
     * @return 指定日期后天数的数组
     */
    private static Date[] getDates(Calendar calendar,int dateLenght) {
        Date[] weekDate = new Date[dateLenght];
        for (int i = 0; i < dateLenght; i++){
            weekDate[i] = calendar.getTime();
            calendar.add(Calendar.DATE,1);
        }
        return weekDate;
    }

    /**
     * 将指定时间设置至当前周周一
     * @param calendar 指定时间的calendar对象
     */
    private static void setMonday(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
            calendar.add(Calendar.DATE,-1);
        }
    }

    /**
     * 计算两个时间的相距天数
     * 默认yyyy-MM-dd
     * @param startDate 起始日期字符串
     * @param endDate 结束日期字符串
     * @return LocalDate天数相减
     */
    public static long betweenDay(String startDate, String endDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse(startDate,DATE_FORMAT_STR));
        LocalDate start = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        calendar.setTime(parse(endDate,DATE_FORMAT_STR));
        LocalDate end = LocalDate.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
        return end.toEpochDay() - start.toEpochDay();
    }

    /**
     * 本月1号
     * @param date 需要计算的当前时间
     * @return 计算后的本月1号的Date时间
     */
    public static Date firstDayOfMonth(Date date){
        Calendar calendar = setCalendarTime(date);
        return firstDayOfMonth(calendar);
    }

    /**
     * 本月1号
     * @param calendar 需要计算的当前时间
     * @return 计算后的本月1号的Date时间
     */
    private static Date firstDayOfMonth(Calendar calendar){
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.getTime();
    }

    /**
     * 上一个月的1号
     * @param date 需要计算的当前时间
     * @return 计算后的上月1号的Date时间
     */
    public static Date firstDayOfPreviousMonth(Date date){
        Calendar calendar = setCalendarTime(date);
        calendar.add(Calendar.MONTH,-1);
        return firstDayOfMonth(calendar);
    }

    /**
     * 获取下一个月的1号
     * @param date 指定的时间
     * @return 计算后的下一个月1号的Date对象时间
     */
    public static Date firstDayOfNextMonth(Date date){
        Calendar calendar = setCalendarTime(date);
        calendar.add(Calendar.MONTH,1);
        return firstDayOfMonth(calendar);
    }

    /**
     * 设置指定时间的Calendar对象
     * @param date 指定时间
     * @return Calendar对象
     */
    private static Calendar setCalendarTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取当前时间的时间戳
     * @return 时间戳字符串
     */
    public static String getTimeStamp(String format) {
        return format(new Date(), format);
    }

    /**
     * 获取一定秒数后的Date对象
     * @param second 秒
     * @return 一定秒数后的Date
     */
    public static Date getExpDate(int second) {
        return new Date(System.currentTimeMillis() + second*1000);
    }

    /**
     * 获取当前时间之前的时间
     * @param ihour 与当前时间的时间间隔
     * @param type 时间间隔类型（Calendar.MINUTE,Calendar.HOUR_OF_DAY...）
     * @return
     */
    public static Date getBeforeByHourTime(int ihour,int type){
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);//设置参数时间
        calendar.add(type, -ihour);//整数往后推,负数往前移动
        date = calendar.getTime();
        return date;
    }
    /**
     *计算两日期的差天数
     * @param startDate  开始日期 格式 “YYYY/MM/DD HH:mm:ss”
     * @param sendDate 结束日期 格式 “YYYY/MM/DD HH:mm:ss”
     * @return
     */
    public static BigDecimal getDatePoors(Date sendDate, Date startDate) {
        if(sendDate.getTime()>startDate.getTime()){
            long nd = 1000 * 24 * 60 * 60;
            long nh = 1000 * 60 * 60;
            long diff = sendDate.getTime()-startDate.getTime();
            long day = diff / nd;
            long hour = diff % nd / nh;
            double s = (day*24+ hour)/24.0;
            return new BigDecimal(String.format("%.1f", s));
        }
        return new BigDecimal("0");
    }
    /**
     *计算日期+天数
     * @return
     */
    public static Date getDateTime(Date date, Integer  day) {
        Calendar calendar = new GregorianCalendar(); // 定义calendar对象
        calendar.setTime(date); // 把当前系统时间赋值给calendar
        calendar.add(calendar.DATE, day); // 在日期中增加天数,1天
        return calendar.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）小时
     * @param date 输入日期
     * @param hour 要增加或减少的小时
     */
    public static Date addHour(Date date, int hour){
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);
        cd.add(Calendar.HOUR_OF_DAY, hour);
        return cd.getTime();
    }





    /**
     * 在输入日期上增加（+）或减去（-）分钟
     * @param date 输入日期
     * @param minute 分钟
     * @return
     */
    public static Date addMinute(Date date, int minute){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(Calendar.MINUTE, minute);
        return cd.getTime();
    }


    /**
     * 在输入日期上增加（+）或减去（-）秒
     * @param date 输入日期
     * @param second 秒
     * @return
     */
    public static Date addSecond(Date date, int second){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(Calendar.SECOND, second);
        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）小时
     * @param hour 要增加或减少的小时
     */
    public static Date addHour(int hour){
        return addHour(new Date(), hour);
    }
    /**
     * 在输入日期上增加（+）或减去（-）天数
     * @param date 输入日期
     * @param iday 要增加或减少的天数
     */
    public static Date addDay(Date date, int iday) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.DAY_OF_MONTH, iday);

        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）月份
     * @param date 输入日期
     * @param imonth 要增加或减少的月分数
     */
    public static Date addMonth(Date date, int imonth) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.MONTH, imonth);

        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）年份
     * @param date 输入日期
     * @param iyear 要增加或减少的年数
     */
    public static Date addYear(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.YEAR, iyear);

        return cd.getTime();
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date getDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return calendar.getTime();
    }

    /**
     * 修改时间（按时间查询用）
     */
    public static Date updateDate(Date date){
        if(date!=null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY,23);
            cal.set(Calendar.MINUTE,59);
            cal.set(Calendar.SECOND,59);
            date= cal.getTime();
            return date;
        }
        return null;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s, String format) {
        String res = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s, String format){
        String res = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     *
     * @return
     */
    public static Date getDayEnd(Date date){

        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }


    public static Date getTodayStart(){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 0);
        todayEnd.set(Calendar.MINUTE, 0);
        todayEnd.set(Calendar.SECOND, 0);
        todayEnd.set(Calendar.MILLISECOND, 0);
        return todayEnd.getTime();
    }

    public static String getYear(){
        return new SimpleDateFormat("yy").format(getDate());
    }
    public static void main(String[] args){
        System.out.println(now());
    }

    public static String now(){
        return DateUtils.getCurrentDateTime("HH:mm:ss");
    }

}