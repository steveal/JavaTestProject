package com.test.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashMapSerach {
	public static void main(String[] args) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		int size = 80000;
		for(int i = 0;i<size;i++) {
			map.put("key" + i, i);			
		}
		long start = System.nanoTime();
		System.out.println(map.containsKey("key" + (size-1)));
		long t = System.nanoTime() - start;
		System.out.println(t + "ns");
		
		List<String> list = new ArrayList<String>(size);
		for(int j = 0; j<size;j++) {
			list.add("value" + j);
		}
		
		start = System.nanoTime();
		System.out.println(list.contains("value" + (size-1)));
		long t2 = System.nanoTime() - start;
		System.out.println(t2 + "ns");
		
		System.out.println(t2/t*100+"%");
		
	}
}
