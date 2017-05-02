package com.test.thread.basic;
public class MyThread30_1 extends Thread
{
    private Object lock;
    
    public MyThread30_1(Object lock)
    {
        this.lock = lock;
    }
    
    public void run()
    {
 
        synchronized (lock)
        {
            System.out.println("开始------notify time = " + System.currentTimeMillis());
            lock.notify();
            System.out.println("结束------notify time = " + System.currentTimeMillis());
        }
    }
}