package com.test.jdk8;

public class NewFeatureJdk8 {
	public static void main(String[] args) {

		  MyCompute c = new MyCompute() {

		   @Override
		   public int subtraction(int i1, int i2) {
		    // TODO Auto-generated method stub
		    return i1 - i2;
		   }
		  };

		  //Test sum function, result = 2
		  int result = c.sum(1, 1);

		  //Test subtraction function, result2 = 0
		  int result2 = c.subtraction(1, -1);

		 }
}
