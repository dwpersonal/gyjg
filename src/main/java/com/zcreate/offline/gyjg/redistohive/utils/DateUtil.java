package com.zcreate.offline.gyjg.redistohive.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Author: majun
 * @CreateDate: 2018/11/28 9:22
 * @Version: 1.0
 * @Description: 日期工具类
 */

public class DateUtil {

    public static String getYesterday(String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        date = calendar.getTime();
        return sdf.format(date);
    }

    public static Date parse(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    /**
     * 给定日期与当前日期差值
     * @param date
     * @return
     */
    public static int dValue(String date) throws ParseException {
        Date subDate = parse(date, "yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        int nowDay = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(subDate);
        int subDay = calendar.get(Calendar.DAY_OF_YEAR);

        return nowDay - subDay;
    }
}
