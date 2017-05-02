package com.test.arrays;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class CopyOfTest2 {

	public static void main(String[] args) {

		Person2 p1 = new Person2("p1");
		Person2 p2;
		p2 = (Person2) p1.clone();
		p2.setName("p2");
		System.out.println("P1:" + p1);
		System.out.println("P2:" + p2);

		Person2[] plist = new Person2[1];
		for (int i = 0; i < plist.length; i++) {
			Person2 p = new Person2("Person" + String.valueOf(i));
			plist[i] = p;
		}

		Person2[] plist2 = plist.clone();

		Object[] plist3 = arrayDeepClone(plist);

		Person2[] plist4 = personArrayDeepClone(plist);

		Person2[] plistlast = Arrays.copyOf(plist, plist.length);
		plistlast[plist.length - 1].setName("PersonX");

		printArrays(plist, "Plist");

		printArrays(plist2, "Plist2 by plist.clone();");

		printArrays(plist3, "plist3 by arrayDeepClone");

		printArrays(plist4, "plist4 by personArrayDeepClone");

		printArrays(plistlast, "plistlast by Arrays.copyOf(plist, plist.length)");
		
		

	}

	public static Person2[] personArrayDeepClone(Person2[] arrays) {
		Person2[] plist = new Person2[arrays.length];
		for (int i = 0; i < arrays.length; i++) {
			plist[i] = (Person2) arrays[i].clone();
		}
		return plist;

	}

	public static <T> Object[] arrayDeepClone(T[] arrays) {
		List<T> copyList = new ArrayList<T>();
		for (int i = 0; i < arrays.length; i++) {
			
			Object o = arrays[i];
			if(null!=o && o instanceof Cloneable) {
				Class<? extends Object> cls = o.getClass();
				Method method;
				try {
					method = cls.getMethod("clone");
					Object result = method.invoke(o);
					copyList.add((T) result);
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				copyList.add((T) o);
			}
			
		}
		return  copyList.toArray();
	}

	public static <T> void printArrays(T[] arrays, String startInfo) {
		System.out.println("--" + startInfo + "--");
		for (T o : arrays) {
			System.out.println("Class: " + o.getClass().getName()  + " "+  o);
		}
	}
}
