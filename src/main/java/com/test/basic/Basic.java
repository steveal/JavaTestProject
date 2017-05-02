package com.test.basic;

public class Basic {
	
	public static void main(String[] args) {
		test();
	}
	
	public static String test() {
		try {
			System.out.println("try");
			Integer.parseInt("xxx");
			return "test";
		}catch(Exception e) {
			System.out.println("catch");
			return "catch";
		}finally {
			System.out.println("finally");
		}
	}
}
