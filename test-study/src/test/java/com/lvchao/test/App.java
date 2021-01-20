package com.lvchao.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

    public static void main(String[] args) {
        try {
            List<String> list = new ArrayList<String>();

            for (int i = 0; i < 1000000; i++) {
                list.add(i + ",");
            }

            System.out.println(new Date());
            System.out.println(list2Str(list, 5));
            System.out.println(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String list2Str(List<String> list, final int nThreads)
            throws Exception {
        if (list == null || list.isEmpty()) {
            return null;
        }
        StringBuffer ret = new StringBuffer();

        int size = list.size();
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        List<Future<String>> futures = new ArrayList<Future<String>>(nThreads);
        for (int i = 0; i < nThreads; i++) {
            final List<String> subList = list.subList(size / nThreads * i, size/ nThreads * (i + 1));
            Callable<String> task = new Callable<String>() {
                public String call() throws Exception {
                    StringBuffer sb = new StringBuffer();
                    for (String str : subList) {
                        sb.append(str);
                    }
                    return sb.toString();
                }
            };
            futures.add(executorService.submit(task));
        }

        for (Future<String> future : futures) {
            ret.append(future.get());
        }
        executorService.shutdown();

        return ret.toString();
    }

}