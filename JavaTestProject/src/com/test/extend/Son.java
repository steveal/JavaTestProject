package com.test.extend;

public class Son extends Father{
	private int age = 0;
	public Son(int _age) {
		this.age = _age;
		System.out.println("Son speaking" + " ,age :" + age );
	}

}
