package com.test.effectivejava.builder;

public class Person {
	private final String name;
	private final String sex;
	private final Integer age;
	private final String nationality ;
	
	
	
	public static class Builder {
		private String name;
		private String sex;
		private Integer age = 0;;
		private String nationality = "汉";
		
		
		public Person build() {
			Person p = new Person(this);
			return p;
		}
		
		public Builder(String name, String sex) {
			this.name = name;
			this.sex = sex;
		}
		
		public Builder age(Integer age) {
			this.age = age;
			return this;
		}
		
		public Builder nationality(String nationality) {
			this.nationality = nationality;
			return this;
		}
		 
	}
	
	private Person(Builder builder) {
		this.name = builder.name;
		this.sex = builder.sex;
		this.age = builder.age;
		this.nationality = builder.nationality;
	}

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public Integer getAge() {
		return age;
	}

	public String getNationality() {
		return nationality;
	}
	

	
	
}
