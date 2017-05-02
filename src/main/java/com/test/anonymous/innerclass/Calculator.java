package com.test.anonymous.innerclass;

enum Ops{ADD,SUB}
public class Calculator {
	private int i, j, result;
	
	public Calculator(){
		
	}
	
	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public Calculator(int _i, int _j){
		i = _i;
		j = _j;
	}
	
	protected void setOperator(Ops _op) {
		result = _op.equals(Ops.ADD) ? i+j : i-j;
	}
	
	public int getResult(){
		return result;
	}
	
	public static void main(String[]  args) {
		Calculator c1 = new Calculator(1,2);
		c1.setOperator(Ops.ADD);
		System.out.println(c1.getResult());
		String s = "xx";
	}
}
