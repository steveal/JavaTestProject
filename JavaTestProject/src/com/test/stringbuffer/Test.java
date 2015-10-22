package com.test.stringbuffer;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		doWithStringBuffer();
		doWithStringBuilder();
		doWithStringPlus();
		doWithStringContact();
	}
	
	public static void doWithStringBuffer() {
		long start = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer("StringBuffer");
		for(int i=0;i<50000;i++) {
			sb.append("c");
		}
		long end = System.currentTimeMillis();
		System.out.println("StringBuffer.append:" + (end-start));
	}
	
	public static void doWithStringBuilder() {
		long start = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder("StringBuffer");
		for(int i=0;i<50000;i++) {
			sb.append("c");
		}
		long end = System.currentTimeMillis();
		System.out.println("StringBuilder.append:" + (end-start));
	}
	
	public static void doWithStringPlus() {
		long start = System.currentTimeMillis();
		String sb = "StringBuffer";
		for(int i=0;i<50000;i++) {
			sb += "c";
		}
		long end = System.currentTimeMillis();
		System.out.println("String + :" + (end-start));
	}
	
	
	public static void doWithStringContact() {
		long start = System.currentTimeMillis();
		String sb = "StringBuffer";
		for(int i=0;i<50000;i++) {
			sb.concat("c");
		}
		long end = System.currentTimeMillis();
		System.out.println("String.contact :" + (end-start));
	}
	
	
}
