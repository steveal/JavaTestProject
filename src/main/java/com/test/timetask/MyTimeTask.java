package com.test.timetask;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimeTask extends TimerTask {

	private static int count = 0;

	@Override
	public void run() {
		System.out.println(count++);
	}

	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer(true);
		// 设置任务计划，启动和间隔时间
		timer.schedule(new MyTimeTask(), 0, 2000);
		Thread.sleep(1000);
		System.out.println("start task");
		Thread.sleep(15000);
		System.out.println("end");
	}

}
