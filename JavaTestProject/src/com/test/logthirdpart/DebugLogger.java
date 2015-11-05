package com.test.logthirdpart;

import java.util.ArrayList;
import java.util.List;

public class DebugLogger {
	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// 测试使用,正式使用的时候可以在一个方法的开始new一个DebugLogger
		DebugLogger logger = new DebugLogger();
		// 模拟需要记录的十次日志信息,一次记录到DebugLogger里面去了
		for (int i = 0; i < 10; i++) {
			// 在需要记录日志的地方改为下面的代码
			logger.put("Log info " + i);
		}

		// 到了方法结束或者出现异常的时候,将刚才记录的日志取出来
		String[] strings = logger.get();
		// 将取出来的日志一次记录到日志文件里去
		for (int i = 0; i < strings.length; i++) {
			System.out.println(strings[i]);
		}

		// 请空DebugLogger里记录的日志内容
		logger.clear();
		{
			String[] strings1 = logger.get();
			for (int i = 0; i < strings1.length; i++) {
				System.out.println(strings1[i]);
			}

		}
	}

	// 内部静态类,继承至ThreadLocal
	private static class ThreadLocalList extends ThreadLocal {
		// 在调用get()方法的时候返回一个ArrayList对象
		public Object initialValue() {
			return new ArrayList();
		}

		// 将保存在ThreadLocal中的List返回
		public List getList() {
			return (List) super.get();
		}
	}

	private ThreadLocalList list = new ThreadLocalList();
	private static String[] stringArray = new String[0];

	// 清空记录的日志
	public void clear() {
		list.getList().clear();
	}

	// 将需要记录的日志内容保存下来
	public void put(String text) {
		list.getList().add(text);
	}

	// 返回需要记录的日志
	public String[] get() {
		return (String[]) list.getList().toArray(stringArray);
	}
}