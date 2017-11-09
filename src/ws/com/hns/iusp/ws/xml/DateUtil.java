/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      ScopeTec
 * @author       David Hu
 * @version 1.0  Jun 7,2001
 */
package com.hns.iusp.ws.xml;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

//public common class
/**
 * Date utility class
 *
 */
public class DateUtil {
    public static final String DEFAULT_DATE_FORMAT = "M/d/yyyy";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_DATETIME_FORMAT = "M/d/yyyy HH:mm:ss";
    public static final String ALIGN_DATETIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

    /**
     * Return the current Date
     *
     *
     * @return current Date
     *
     */
    public static Date getCurrentDate() {
        return new Date();
    }
    
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * extract the year from a Date object
     *
     *
     * @param d  a Date object
     *
     * @return year of Date,start from 1900
     *
     */
    public static int getYear(Date d) {
        GregorianCalendar cal = new GregorianCalendar();
        //Date		  date = new Date();

        if (d != null) {
            cal.setTime(d);

            return cal.get(Calendar.YEAR);
        } else {
            return 1900;
        }
    }

    /**
     * extract the month from a Date object
     *
     *
     * @param d a Date object.
     *
     * @return  month of year, start from 0.
     *
     * @see
     */
    public static int getMonth(Date d) {
        GregorianCalendar cal = new GregorianCalendar();
        //Date		  date = new Date();

        if (d != null) {
            cal.setTime(d);

            return cal.get(Calendar.MONTH);
        } else {
            return 0;
        }
    }

    /**
     * extract the day from a Date object
     *
     *
     * @param d a Date object.
     *
     * @return Day of Month,start from 1
     *
     */
    public static int getDay(Date d) {
        GregorianCalendar cal = new GregorianCalendar();
        //Date		  date = new Date();

        if (d != null) {
            cal.setTime(d);

            return cal.get(Calendar.DAY_OF_MONTH);
        } else {
            return 1;
        }
    }

    /**
     * extract the day from a Date object
     *
     *
     * @param d a Date object.
     *
     * @return hour,start from 0
     *
     */
    public static int getHour(Date d) {
        GregorianCalendar cal = new GregorianCalendar();
        //Date		  date = new Date();

        if (d != null) {
            cal.setTime(d);

            return cal.get(Calendar.HOUR_OF_DAY);
        } else {
            return 0;
        }
    }

    /**
     * extract the minute from a Date object
     *
     *
     * @param d a Date object.
     *
     * @return minute, start from 0
     *
     */
    public static int getMinute(Date d) {
        GregorianCalendar cal = new GregorianCalendar();
        //Date		  date = new Date();

        if (d != null) {
            cal.setTime(d);

            return cal.get(Calendar.MINUTE);
        } else {
            return 0;
        }
    }

    /**
     * extract the second from a Date object
     *
     *
     * @param d a Date object.
     *
     * @return second,start form 0
     *
     */
    public static int getSecond(Date d) {
        GregorianCalendar cal = new GregorianCalendar();
        //Date		  date = new Date();

        if (d != null) {
            cal.setTime(d);

            return cal.get(Calendar.SECOND);
        } else {
            return 0;
        }
    }

    /**
     * form a date object to string, use the  "M/d/yyyy H:m:s" format
     *
     *
     * @param d  a Date object.
     *
     * @return   a string like "M/d/yyyy H:m:s"
     *
     */
    public static String toString(Date d) {
        if (d == null) {
            return null;
        }

        return toString(d, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * form a date object to string, use specified format
     *
     *
     * @param date            a Date object.
     * @param format          a String specifies the date format
     *
     * @return                a string like describle the date
     *
     */
    public static String toString(Date date, String format) {
        if (date == null) {
            return "";
        }

        if ((format == null) || (format.length() == 0)) {
            return "";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(format);

        return formatter.format(date);
    }

    /**
     * form a date object to string, use specified format
     *
     *
     * @param date            a Date object.
     * @param format          a String specifies the date format
     * @param locale     a locale determine the format
     *
     * @return                a string like describle the date
     *
     */
    public static String toString(Date date, String format,Locale locale) {
        if (date == null) {
            return "";
        }

        if ((format == null) || (format.length() == 0)) {
            return "";
        }

        if (locale==null){
            return "";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(format,locale);

        return formatter.format(date);
    }

    /**
     * decode a Date object from string
     *
     *
     * @param s  string conatins the date infomation
     *
     * @return   a Date object
     *
     */
    public static Date toDate(String s) {
        if ((s == null) || (s.length() == 0)) {
            return null;
        }

        if (s.indexOf(':') > 0) {
            return toDate(s, DEFAULT_DATETIME_FORMAT);
        } else {
            return toDate(s, DEFAULT_DATE_FORMAT);
        }
    }

    /**
     * decode a Date object from string, use specified format
     *
     *
     * @param s          string conatins the date infomation
     * @param format     a String specifies the date format
     *
     * @return           a Date object
     *
     */
    public static Date toDate(String s, String format) {
        if ((s == null) || (s.length() == 0)) {
            return null;
        }

        if ((format == null) || (format.length() == 0)) {
            return null;
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition    pos = new ParsePosition(0);
        Date		 d = formatter.parse(s, pos);

        return d;
    }

    /**
     * decode a Date object from string, use specified format and locale.
     *
     * @param s          string conatins the date infomation
     * @param format     a String specifies the date format
     * @param locale     a locale determine the format
     *
     * @return           a Date Oject
     *
     */
    public static Date toDate(String s, String format, Locale locale) {
        Locale loc;

        if ((s == null) || (s.length() == 0)) {
            return null;
        }

        if ((format == null) || (format.length() == 0)) {
            return null;
        }

        if (locale == null) {
            loc = Locale.getDefault();
        } else {
            loc = locale;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(format, loc);
        ParsePosition    pos = new ParsePosition(0);
        Date		 d = formatter.parse(s, pos);

        return d;
    }

    /**
     * Check whether the string is a date format
     *
     *
     * @param s   a string contains date infomation
     *
     * @return  <code>true</code> when the string conatins a recongnizable date
     * string,<code>false</code> otherwise.
     *
     */
    public static boolean isDate(String s) {
        Date date = toDate(s);
        if (date!=null){
            int year = getYear(date);
            int month = getMonth(date);
            int day = getDay(date);
            if ((year>9999) ||(month>12) ||(day>31)){
                return false;
            }
        }
        return date != null;
    }

    /**
     * Check whether the string is a date format
     *
     *
     * @param s        a string contains date infomation.
     * @param format   a String specifies the date format.
     *
     * @return  <code>true</code> when the string conatins a recongnizable date
     * string,<code>false</code> otherwise.
     */
    public static boolean isDate(String s, String format) {
        Date date = toDate(s, format);
        if (date!=null){
            int year = getYear(date);
            int month = getMonth(date);
            int day = getDay(date);
            if ((year>9999) ||(month>12) ||(day>31)){
                return false;
            }
        }

        return date != null;
    }

    /**
     * Check whether the string is a date format
     *
     *
     * @param s        a string contains date infomation.
     * @param format   a String specifies the date format.
     * @param locale     a locale determine the format
     *
     * @return  <code>true</code> when the string conatins a recongnizable date
     * string,<code>false</code> otherwise.
     */
    public static boolean isDate(String s, String format, Locale locale) {
        Date date = toDate(s, format, locale);
        if (date!=null){
            int year = getYear(date);
            int month = getMonth(date);
            int day = getDay(date);
            if ((year>9999) ||(month>12) ||(day>31)){
                return false;
            }
        }

        return date != null;
    }

    public static boolean compare(Date d0,Date d1) {
      if (d0 == null && d1 == null) {
        return true;
      }
      if (d0 == null || d1 == null)
        return false;
      else
        return d0.getTime()==d1.getTime();


    }
    
    public static boolean isSameDay(long time0, long time1){
        Calendar d = Calendar.getInstance();
        d.setTimeInMillis(time0);
        Calendar d1 = Calendar.getInstance();
        d1.setTimeInMillis(time1);
        return d.get(Calendar.YEAR)==d1.get(Calendar.YEAR) &&
               d.get(Calendar.MONTH)==d1.get(Calendar.MONTH) &&
               d.get(Calendar.DAY_OF_MONTH)==d1.get(Calendar.DAY_OF_MONTH);
        
    }

    public static java.sql.Date toSqlDate(java.util.Date d) {
        return d!=null?new java.sql.Date(d.getTime()):null;
    }

    public static java.sql.Timestamp toTimestamp(java.util.Date d) {
        return d!=null?new java.sql.Timestamp(d.getTime()):null;
    }

    //�ض�ʱ����
    public static Date trunc(Date dd ){
        Calendar d = Calendar.getInstance();
        d.setTimeInMillis(dd.getTime());
        d.set(Calendar.HOUR_OF_DAY,0);
        d.set(Calendar.MINUTE,0);
        d.set(Calendar.SECOND,0);
        d.set(Calendar.MILLISECOND,0);
        return d.getTime();
        
    }

    public static java.util.Date getWeekFirstDay(java.util.Date d){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(d.getTime());
        c.set(Calendar.DAY_OF_WEEK, 1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return new Date(c.getTimeInMillis());
    }
    
    public static java.util.Date getWeekLastDay(java.util.Date d){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(d.getTime());
        c.add(Calendar.WEEK_OF_YEAR, 1);
        c.set(Calendar.DAY_OF_WEEK, 1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return new Date(c.getTimeInMillis()-24*3600*1000L);
    }

    public static java.util.Date getMonthFirstDay(java.util.Date d){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(d.getTime());
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return new Date(c.getTimeInMillis());
    }
    
    public static java.util.Date getMonthLastDay(java.util.Date d){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(d.getTime());
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return new Date(c.getTimeInMillis()-24*3600*1000L);
    }
    
    public static java.util.Date addDays(java.util.Date d, int days)
    {
    	Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(d.getTime());
    	c.add(Calendar.DATE, days);
    	return new java.util.Date(c.getTimeInMillis());
    }
    
    public static java.sql.Timestamp addDays(java.sql.Timestamp d, int days)
    {
    	Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(d.getTime());
    	c.add(Calendar.DATE, days);
    	return new java.sql.Timestamp(c.getTimeInMillis());
    }

    public static void main(String[] args) {
    	String s= "2012-01-01 01:00:09";
    	Date d = toDate(s,"yyyy-MM-dd");
    	System.out.println(d);
    }

}

/**
 * Change Log
 *
 * 2002/03/27  David Hu modify the isDate series method
 *
 */

