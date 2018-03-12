package com.bigdata.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Desciption 时间工具类
 * Create By  li.bo
 * CreateTime 2018/3/12 11:05
 * UpdateTime 2018/3/12 11:05
 */
public class DateUtils {

    private DateUtils() {
        throw new Error("Don't instantiate " + getClass());
    }

    /**
     * 获取X月前（负值）/后（正值）的年份
     *
     * @return
     */
    public static int getYear(int number) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, number);
        date = calendar.getTime();
        SimpleDateFormat year = new SimpleDateFormat("YYYY");
        return Integer.parseInt(year.format(date));
    }

    /**
     * 获取X月前（负值）/后（正值）的月份
     *
     * @return
     */
    public static int getMonth(int number) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, number);
        date = calendar.getTime();
        SimpleDateFormat month = new SimpleDateFormat("MM");
        return Integer.parseInt(month.format(date));
    }
}
