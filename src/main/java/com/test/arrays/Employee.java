package com.test.arrays;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Employee implements Comparable<Employee> {

	enum Position {
		Boss, Manager, Staff;
	}

	private int id;

	private String name;

	private Position position;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Employee(int _id, String _name, Position _position) {
		this.id = _id;
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
		if (null == o) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if (this.getClass() != o.getClass()) {
			return false;
		}

		Employee emp = (Employee) o;
		return new EqualsBuilder().append(this.id, emp.id).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.DEFAULT_STYLE).append("id", id).append("position", position).append("name", name).toString();
	}

}
