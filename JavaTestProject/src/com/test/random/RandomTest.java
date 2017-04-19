package com.test.random;

import java.util.Random;

import com.test.anonymous.innerclass.Add;

/**
 * 种子相同时，多次运行随机数是相同的
 * @author Steve
 *
 */
public class RandomTest {

	public static void main(String[] args) {
		Random r = new Random(1000);
		for (int i = 1; i<4;i++) {
			System.out.println("第 " + i + "次: " + r.nextInt());
		}
		Add a = new Add(1, 2);
	}

}
