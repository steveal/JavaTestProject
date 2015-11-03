package com.test.listenerexample;

import java.util.EventObject;

/**
 * Title: 事件处理类，继承了事件基类 
 * Description: 
 * Copyright: Copyright (c) 2005 Company:
 * cuijiang
 * 
 * @author not attributable
 * @version 1.0
 */
public class DemoEvent extends EventObject {
	private Object obj;
	private String sName;

	public DemoEvent(Object source, String sName) {
		super(source);
		obj = source;
		this.sName = sName;
	}

	public Object getSource() {
		return obj;
	}

	public void say() {
		System.out.println("这个是 say 方法...");
	}

	public String getName() {
		return sName;
	}
}