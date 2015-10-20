package com.test.utilset;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User u1 = new User(1, "Jacky", "123456");
		User u2 = new User(2, "Jacky", "123456");
		User u3 = new User(3, "CRT", "123456");
		User u4 = new User(4, "Lsp", "123456");

		System.out.println("equal==>" + (u1.equals(u2)));

		Set set = new LinkedHashSet();
		set.add(u1);
		set.add(u2);
		set.add(u3);
		set.add(u4);

		System.out.println("set size==>" + set.size());

		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			User temp = (User) iterator.next();
			System.out.println("temp username==>" + temp.getName());
		}

	}

}