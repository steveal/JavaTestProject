package com.test.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ExampleTest {
	
	public static void main(String[] args) {
		Vector<String> vs = new Vector<String>();
		System.out.println(vs.isEmpty() + " " + vs.capacity());
		
		List<String> ls = new ArrayList<String>();
		System.out.println(ls.isEmpty() + " "+ ls.size());
		
		List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
		 System.out.println(stooges.size());
		 
		String[] sarrys = new String[] {"Larry", "Moe", "Curly"};
		List<String> stooges2 = Arrays.asList(sarrys);
		 System.out.println(stooges2.size());
		 
		 int[] iarrays = {1,2,3,4};
		 List ilist = Arrays.asList(iarrays);
		 System.out.println(ilist.size());
		 
		 
	}
}
