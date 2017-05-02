package com.test.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

public class ExampleTest {
	
	/**
	 * 需要跟踪调试，发现Vector,和List初始空时，都是初始化一个10位的数组
	 */
	@Test
	public void InitSize() {
		System.out.println("ExampleTest.InitSize()");
		Vector<String> vs = new Vector<String>();
		System.out.println(vs.isEmpty() + " " + vs.capacity());
		
		List<String> ls = new ArrayList<String>();
		System.out.println(ls.isEmpty() + " "+ ls.size());
		
	}
	
	@Test 
	public void  AsListTest(){
		List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
		 System.out.println(stooges.size());
		 
		String[] sarrys = new String[] {"Larry", "Moe", "Curly"};
		List<String> stooges2 = Arrays.asList(sarrys);
		 System.out.println(stooges2.size());
		 
		 int[] iarrays = {1,2,3,4};
		 List ilist = Arrays.asList(iarrays);
		 System.out.println(ilist.size());
	}
	
	@Test
	public void listremove() {
		System.out.println("ExampleTest.listremove()");
		ArrayList<String> as = new ArrayList<String>();
		as.add("a");
		as.add("b");
		as.add("c");
		as.add("d");
		as.add("e");
		while(as.size()>0) {
			String s = as.remove(0);
			System.out.println(s);
		}
	}
}
