package com.lvchao.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/19
 */
public class Tes01 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String name = new String("大大撒");
      //  String encode = URLEncoder.encode(name, "UTF-8");
     //  String encode = URLEncoder.encode(name, "ISO-8859-1");
      //  String encode = URLEncoder.encode(name, "GB2312");
        String encoderString = URLEncoder.encode(name, "utf-8");
        System.out.println(encoderString);
        String decodedString = URLDecoder.decode(name, "utf-8");
        System.out.println(decodedString);
    }
}

