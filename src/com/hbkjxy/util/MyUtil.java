package com.hbkjxy.util;

import java.awt.Color;
/**
 * 工具类，提供各种工具方法
 * @author yhl
 *
 */
public class MyUtil {
	private MyUtil() {
	}
	
	/**
	 * 得到指定区间的随机数
	 * @param min 区间的最小值  包含
	 * @param max 区间的最大值  不包含
	 * @return  返回随机数
	 */
	public static final int getRandomNumber(int min, int max){
		return (int)(Math.random()*(max-min)+min);
	}
	
	/**
	 * 得到随机的颜色
	 * @return
	 */
	public static final Color getRandomColor(){
		return new Color(getRandomNumber(0, 256),getRandomNumber(0, 256),getRandomNumber(0, 256));
	}
	
	/**
	 * 判断指定概率的时间，本次是否发生的
	 * @param percent  指定事件的概率
	 * @return  如果发生了，返回 true ，否则  false。
	 */
	public static boolean isHappened(double percent){//0.3
		//
//		Math.random()  得到一个随机的小数[0.0~1.0)  Math.random() 得到的结果小于0.3的概率就是30%
		return Math.random() < percent;
	}
	
	
	
	
	
}
