package com.test.thread.basic;
public class MyThread13_1 extends Thread
{
    private ThreadDomain13 td;
    
    public MyThread13_1(ThreadDomain13 td)
    {
        this.td = td;
    }
    
    public void run()
    {
        td.addNum("b");
    }
}