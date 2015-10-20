package com.test.bigdecimal;

import java.math.BigDecimal;

public class BigDecimalTest {
	public static void main(String[] args) {
		System.out.println(10.00-9.6);
		
		BigDecimal d1 = new BigDecimal(10.00);
		BigDecimal d2 = new BigDecimal(9.6);
		System.out.println(d1.subtract(d2));
		
		
		BigDecimal d3 = new BigDecimal("10.00");
		BigDecimal d4 = new BigDecimal("9.6");
		System.out.println(d3.subtract(d4));
	}
}
