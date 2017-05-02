package com.test.jdk7;
public class TryStudy implements AutoCloseable{
	
    private String name;
    public TryStudy(String name_) {
    	this.name = name_;
    }
    @Override  
    public void close() throws Exception {  
    	System.out.println(name + " study closed");
    }  
}  