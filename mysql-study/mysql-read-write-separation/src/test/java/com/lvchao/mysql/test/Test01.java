package com.lvchao.mysql.test;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/16
 */
public class Test01 {
    private static final SimpleDateFormat SDFYMD = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf =  new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Test
    public void test01(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-30);
        String endTime = SDFYMD.format(calendar.getTime()) + " 23:59:59.999";
        System.out.println(endTime);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.YEAR,-10);
        String startTime  = sdf.format(calendar1.getTime());
        System.out.println(startTime);

    }

}
