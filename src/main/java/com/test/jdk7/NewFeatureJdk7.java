package com.test.jdk7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewFeatureJdk7 {
	public static void main(String[] args) {
		/*
		 * 很长的数字可读性不好，在Java 7中可以使用下划线分隔长int以及long了，如： int one_million =
		 * 1_000_000; 运算时先去除下划线，如：1_1 * 10 = 110，120 – 1_0 = 110
		 */
		int x = 5__2;
		float pi = 3.14_15F;
		System.out.println(x);
		System.out.println(pi);

		
		Map<String, List<String>> anagrams = new HashMap<String, List<String>>();
		Map<String, List<String>> anagrams2 = new HashMap<>();
		
		String name = "name";
		String age = "age";
//		 Map map = {name:"xxx",age:18};
		
		try (TryStudy ts = new TryStudy("steve")) {
			System.out.println("start exeucte study work");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
}
