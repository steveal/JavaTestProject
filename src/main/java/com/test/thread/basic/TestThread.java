package com.test.thread.basic;

public class TestThread {
	public static void main(String[] args) throws Exception
	{
/*	    Object lock = new Object();
	    MyThread30_0 mt0 = new MyThread30_0(lock);
	    mt0.start();
	    Thread.sleep(3000);
	    MyThread30_1 mt1 = new MyThread30_1(lock);
	    mt1.start();*/
	    
	    ThreadDomain13 td0 = new ThreadDomain13();
//	    ThreadDomain13 td1 = new ThreadDomain13();
	    MyThread13_0 mt0 = new MyThread13_0(td0);
	    MyThread13_1 mt1 = new MyThread13_1(td0);
	    mt0.start();
	    mt1.start();
	}
}
