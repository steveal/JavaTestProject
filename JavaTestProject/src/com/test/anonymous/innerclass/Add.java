package com.test.anonymous.innerclass;

public class Add extends Calculator {
	{
		setOperator(Ops.ADD);
	}
	
	public Add(int _i,int _j) {
		
	}
	
	public static void main(String[]  args) {
		Add a = new Add(1,2);		
		a.setOperator(Ops.ADD);
		System.out.println(a.getI());
		System.out.println(a.getJ());
		System.out.println(a.getResult());
	}
}
