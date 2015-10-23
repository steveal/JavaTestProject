package com.test.arrays;

import java.util.Arrays;

public class CopyOfTest {
	
	public static void main(String[] args) {
		Person[] plist = new Person[5];
		for(int i=0;i<plist.length; i++) {
			Person p = new Person("Person" + String.valueOf(i));
			plist[i]=p;
		}
		
		Person[] plist2 = plist.clone();
		
		Person[] plist3 = Arrays.copyOf(plist, plist.length);
		plist3[4].setName("PersonX");
		
		System.out.println("Plist");
		
		for(int i=0;i<plist.length; i++) {
			System.out.println(plist[i]);
		}
		
		System.out.println("Plist2 by plist.clone();");
		for(int i=0;i<plist3.length; i++) {
			System.out.println(plist3[i]);
		}
		
		System.out.println("Plist3 by Arrays.copyOf(plist, plist.length)");
		for(int i=0;i<plist3.length; i++) {
			System.out.println(plist3[i]);
		}
		
		
	}
}
