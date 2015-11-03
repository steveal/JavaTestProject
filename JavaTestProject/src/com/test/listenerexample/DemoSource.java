package com.test.listenerexample;

import java.util.Vector;
import  java.util.Enumeration;

/**
 * Title: 使用事件的类
 *  Description: 该类实现了监听器的添加和监听器方法的执行，并且实现了由于属性的改变而执行事件
 * Description: 在添加、删除、执行监听器的时候都要注意同步问题 Copyright: Copyright (c) 2005 Company:
 * cuijiang
 * 
 * @author not attributable
 * @version 1.0
 */
public class DemoSource {
	private Vector repository = new Vector();
	private DemoListener dl;
	private String sName = "";

	public DemoSource() {
	}

	// 注册监听器，如果这里没有使用Vector而是使用ArrayList那么要注意同步问题
	public void addDemoListener(DemoListener dl) {
		repository.addElement(dl);// 这步要注意同步问题
	}

	// 如果这里没有使用Vector而是使用ArrayList那么要注意同步问题
	public void notifyDemoEvent(DemoEvent event) {
		Enumeration enumeration = repository.elements();//这步要注意同步问题
		while(enumeration.hasMoreElements())
		{
			dl = (DemoListener)enumeration.nextElement();
			dl.demoEvent(event);
		}
   }

	// 删除监听器，如果这里没有使用Vector而是使用ArrayList那么要注意同步问题
	public void removeDemoListener(DemoListener dl) {
		repository.remove(dl);// 这步要注意同步问题
	}

	/**
	 * 设置属性
	 * 
	 * @param str1
	 *            String
	 */
	public void setName(String str1) {
		boolean bool = false;
		if (str1 == null && sName != null)
			bool = true;
		else if (str1 != null && sName == null)
			bool = true;
		else if (!sName.equals(str1))
			bool = true;

		this.sName = str1;

		// 如果改变则执行事件
		if (bool)
			notifyDemoEvent(new DemoEvent(this, sName));
	}

	public String getName() {
		return sName;
	}
}