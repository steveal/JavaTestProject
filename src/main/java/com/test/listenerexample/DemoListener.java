package com.test.listenerexample;

import java.util.EventListener;

/**
 * Title: 监听器接口 Description: Copyright: Copyright (c) 2005 Company: cuijiang
 * 
 * @author not attributable
 * @version 1.0
 */
public interface DemoListener extends EventListener {
	public void demoEvent(DemoEvent dm);
}