package com.test.sync;


import java.util.concurrent.TimeUnit;  
public class TestSync {

	public static Object o = new Object();
	public  static String str = "";
	
	public void change(String str_) {
//		Object c = new Object();
		synchronized(TestSync.class) {
			str = str_;
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "\t\t" + str);
			
		}
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final TestSync rs = new TestSync();
	       new Thread() {  
	           public void run() {  
	        	     
	        	   rs.change("t1");
	        	   
	        	   System.out.println(System.currentTimeMillis());
	           }  
	       }.start();  
	   
	       new Thread() {  
	           public void run() { 
	        	   
	        	   rs.change("t2");
	        	   
	        	   System.out.println(System.currentTimeMillis());
	           }  
	       }.start();  
	   
	       
      
		System.out.println(rs.str);
	}

}
