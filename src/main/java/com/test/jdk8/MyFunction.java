package com.test.jdk8;
/**
 * 方法引用类
 * @author 郭胜凯
 * @emai 719348277@qq.com
 * @time 2016年7月4日 下午1:32:23
 */
public class MyFunction {

 /**
  * 指向某个Function的方法指针
  * @author 郭胜凯
  * @emai 719348277@qq.com
  * @time 2016年7月4日 下午1:31:07
  * @param <F> 传值类型
  * @param <T> 结果类型
  */
 @FunctionalInterface
 interface Fun<F, T> {
     T run(F from);
 }
 
 public static void main(String[] args) {

	  Fun<String, String> fun = MyFunction::myMethod;

	  String result = fun.run("This is arg");

	  System.out.println(result);

	 }



	 public static String myMethod(String arg){
	  return arg;
	 }
 
 
}