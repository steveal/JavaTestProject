package com.test.arrays;

import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.test.arrays.Employee.Position;

public class ComparableTest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 2;
		int j = 3;
		System.out.println(compare(i,j));
		String a = "a";
		String b = "b";
		System.out.println(compare(a,b));
		System.out.println(compare(b,b));
		System.out.println(compare(b,a));
		

	}
	
	public static int compare(Object i,Object j) {
		return compare(i,j,null);
		
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
		System.out.println(compare(p1,p2));
	}
	
	@Test(expected=ClassCastException.class)
	public void unComparaeToBuilderClass() {
		Person p1 = new Person("p1");
		Person p2 = new Person("p2");
		System.out.println(new CompareToBuilder().append(p1, p2));
	}
	
	@Test
	public void comparableClass() {
		Employee staff1 = new Employee(1,"staff1",Position.Staff);
		Employee staff2 = new Employee(2,"staff2",Position.Staff);
		Assert.assertTrue(compare(staff1,staff2)<0);
	}
}
