package com.test.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.test.arrays.Employee.Position;

public class TestArraySort {

	Employee boss;
	Employee manager;
	Employee staff1;
	Employee staff2;
	Employee staff3;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Before
	public void init() {
		boss = new Employee(1, "boss", Position.Boss);
		manager = new Employee(2, "manager", Position.Manager);
		staff1 = new Employee(3, "staff1", Position.Staff);
		staff2 = new Employee(4, "staff2", Position.Staff);
		staff3 = new Employee(5, "staff3", Position.Staff);
	}

	@Test
	public void sortList() {
		{
			List<Employee> emplist = new ArrayList<Employee>();

			emplist.add(boss);
			emplist.add(manager);
			emplist.add(staff1);
			emplist.add(staff2);
			emplist.add(staff3);
			Collections.shuffle(emplist);
			printList(emplist, "shuffle list");

			Collections.sort(emplist);

			printList(emplist, "after Collections.sort");
		}
	}

	@Test
	public void SortArrays() {
		Employee[] emparrays = new Employee[5];
		emparrays[0] = staff3;
		emparrays[1] = staff1;
		emparrays[2] = manager;
		emparrays[3] = staff2;
		emparrays[4] = boss;

		printArrays(emparrays, "shuffle arrays");
		Arrays.sort(emparrays);
		printArrays(emparrays, "sort arrays by implements Comparable<Employee>");
		Arrays.sort(emparrays, new EmployeeComparator());
		printArrays(emparrays, "sort arrays by class EmployeeComparator implements Comparator<Employee>");
	}

	public static void printList(List list, String startInfo) {
		System.out.println("--" + startInfo + "--");
		for (Object o : list) {
			System.out.println(o);
		}
	}

	public static <T> void printArrays(T[] arrays, String startInfo) {
		System.out.println("--" + startInfo + "--");
		for (Object o : arrays) {
			System.out.println(o);
		}
	}

}
