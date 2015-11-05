package com.test.thread.sync;

public class TextThread {

	public static void main(String[] args) {
		TxtThread tt = new TxtThread();
		new Thread(tt,"thread 1").start();
		new Thread(tt,"thread 2").start();
		new Thread(tt,"thread 3").start();
		new Thread(tt,"thread 4").start();
	}
}

class TxtThread implements Runnable {
	int num = 10;
	String str = new String();

	public void run() {
		synchronized (str) {
			while (num > 0) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.getMessage();
				}
				System.out.println(Thread.currentThread().getName() + " this is " + num--);
			}
		}
		System.out.println(Thread.currentThread().getName() + " done!!");
	}
}