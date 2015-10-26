package com.test.arrays;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Employee implements Comparable<Employee>{
 
	enum Position{
		Boss,Manager,Staff;
	}

	private int id;
	private String name;
	private Position position;
	
	public Employee(int _id,String _name,Position _position) {
		this.id =_id;
		this.name = _name;
		this.position = _position;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



	@Override
	public int compareTo(Employee arg0) {
		// TODO Auto-generated method stub
		return new CompareToBuilder().append(id, arg0.id).toComparison();
	}
	
	@Override
	public boolean equals(Object o) {
		if(null == o) {
			return false;
		}
		
		if(this == o) {
			return true;
		}
		
		if(this.getClass() != o.getClass()) {
			return false;
		}
		
		Employee emp = (Employee) o;
		return new EqualsBuilder().append(this.id,emp.id).isEquals();
	}
	

}
