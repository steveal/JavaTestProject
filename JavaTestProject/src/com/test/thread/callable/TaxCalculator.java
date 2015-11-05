package com.test.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TaxCalculator implements Callable<Integer> {

	private int seedMoney;
	
	public TaxCalculator(int _seedMoney) {
		this.seedMoney = _seedMoney;
	}
	
	@Override
	public Integer call() throws Exception {
		TimeUnit.MILLISECONDS.sleep(10000);
		return this.seedMoney/10; 
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newSingleThreadExecutor();
		Future<Integer> future = es.submit(new TaxCalculator(100));
		es.shutdown();
		int i = 1;
		while(!future.isDone()) {
			TimeUnit.MILLISECONDS.sleep(200);
			System.out.print("#");
			i++;
		}
		System.out.println("tax calculate finished, tax is : " + future.get() + " i:" + i);
		
		
	}

}
