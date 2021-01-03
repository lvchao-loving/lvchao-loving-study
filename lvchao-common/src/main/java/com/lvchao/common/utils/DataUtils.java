package com.lvchao.common.utils;

public class DataUtils {

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    public static String getRandStr(int num){
        String strs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer buff = new StringBuffer();

        for(int i=1;i<=num;i++){
            char str = strs.charAt((int)(Math.random() * 26));
            buff.append(str);
        }

        return buff.toString();
    }
}
