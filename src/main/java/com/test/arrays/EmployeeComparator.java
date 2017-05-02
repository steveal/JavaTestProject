package com.test.arrays;

import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;

public class EmployeeComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
//		if(null == o1 && null == o2) {
//			return 0;
//		}
//		if(null == o1 && null != o2) {
//			return -1;
//		}
//		if(null != o1 && null == o2) {
//			return 1;
//		}
		
		return new CompareToBuilder().append(o2.getId(), o1.getId()).toComparison();
	}

}
