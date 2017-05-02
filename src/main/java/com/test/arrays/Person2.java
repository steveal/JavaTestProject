package com.test.arrays;

public class Person2 implements Cloneable{
	
	private String name;

	public Person2(String _name) {
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
	
	@Override
    public Object clone()  {
//		return super.clone();
        return new Person2(name);
    }
}
