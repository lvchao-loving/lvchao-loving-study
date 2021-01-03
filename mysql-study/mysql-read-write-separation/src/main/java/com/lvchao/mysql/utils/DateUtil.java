package com.lvchao.mysql.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static  DateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String format(Date date){
        String s = sdf.format(date);
        return s;
    }

    public static Date parse(String dateStr) throws ParseException {
        Date date = sdf.parse(dateStr);
        return date;
    }
}
