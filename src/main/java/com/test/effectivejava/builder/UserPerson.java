package com.test.effectivejava.builder;

import org.junit.Test;

import com.test.effectivejava.builder.Person.Builder;

import org.junit.Assert;

public class UserPerson {

	
	@Test
	public  void test() {
		Builder builder = new Person.Builder("hyq", "男");
		Person p = builder.age(28).nationality("China").build();
		
		System.out.println(p.getAge());
		Person p2 = builder.age(30).build();
		Assert.assertEquals(28, (int) p.getAge());
		
		Assert.assertEquals(30, (int) p2.getAge());
	}

}
