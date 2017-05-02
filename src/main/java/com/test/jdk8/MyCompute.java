package com.test.jdk8;
/**
 * 我的Compute类
 * @author 郭胜凯
 * @emai 719348277@qq.com
 * @time 2016年7月4日 下午1:07:42
 */
public interface MyCompute {

 /**
  * 定义加法运算并给他默认实现方法
  * @param i1 加数
  * @param i2 加数
  * @return 和
  */
 default int sum(int i1, int i2){
  return i1 + i2;
 }

 /**
  * 定义减法运算接口
  * @param i1 减数
  * @param i2 被减数
  * @return 差
  */
 int subtraction(int i1, int i2);
}