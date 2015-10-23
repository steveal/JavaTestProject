package com.test.arrays;

public class Person {
	
	private String name;

	public Person(String _name) {
		this.name = _name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
