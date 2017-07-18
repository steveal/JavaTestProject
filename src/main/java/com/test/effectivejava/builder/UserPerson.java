package com.test.effectivejava.builder;

public class UserPerson {

	
	public static void main(String[] args) {
		Person p = new Person.Builder("hyq", "男").age(28).nationality("China").build();
		p.getAge();		
	}

}
