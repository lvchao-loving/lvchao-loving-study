package com.lvchao.redis.utils;

import java.util.concurrent.TimeUnit;

/**
 * 线程休眠辅助工具类
 */
public class SleepTools {
    /**
     * 按秒休眠
     * @param seconds 秒数
     */
    public  static final void second(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按毫秒数休眠
     * @param millis 毫秒数
     */
    public static final void milli(int millis){
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}