package com.test.arrays;

import java.util.Comparator;

import org.junit.Test;

public class ComparableTest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 2;
		int j = 3;
		System.out.println(compare(i,j,null));
		String a = "a";
		String b = "b";
		System.out.println(compare(a,b,null));
		System.out.println(compare(b,b,null));
		System.out.println(compare(b,a,null));
		

	}
	
	public static int compare(Object i,Object j,Comparator<?> comparator) {
		Comparable c = (Comparable) i;
		if(c instanceof Integer) {
			System.out.println("yes " + c.getClass());
		}
//		System.out.println(c.getClass());

		return c.compareTo(j);
		
	}
	
	@Test(expected=ClassCastException.class)
	public void unComparableClass() {
		Person p1 = new Person("p1");
		Person p2 = new Person("p2");
		System.out.println(compare(p1,p2,null));
	}
}
