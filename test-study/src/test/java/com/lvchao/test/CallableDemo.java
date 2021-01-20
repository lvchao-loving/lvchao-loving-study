package com.lvchao.test;

import java.util.concurrent.*;

/**
 * Description: 测试学习 Future 接口
 *
 * @author lvchao
 * @date Create on 2021/1/19
 */

class TaskWithResult implements Callable<String> {
    private String parameter;

    public TaskWithResult(String parameter){
        this.parameter = parameter;
    }

    public String call() throws Exception {
        // TODO Auto-generated method stub
        Thread.sleep(5000);
        return "OK" + ":" + parameter;
    }

}

public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, Exception {
        // TODO Auto-generated method stub
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<String> st = exec.submit(new TaskWithResult("123"));
        System.out.println("开始");
        /*同步结果，而且设置超时时间*/
        System.out.println(st.get(10000, TimeUnit.MILLISECONDS));
        System.out.println("结束");
        exec.shutdown();
        System.out.println("over");
    }
}
