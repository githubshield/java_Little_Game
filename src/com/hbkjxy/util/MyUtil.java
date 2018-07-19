package com.hbkjxy.util;

import java.awt.Color;
/**
 * �����࣬�ṩ���ֹ��߷���
 * @author yhl
 *
 */
public class MyUtil {
	private MyUtil() {
	}
	
	/**
	 * �õ�ָ������������
	 * @param min �������Сֵ  ����
	 * @param max ��������ֵ  ������
	 * @return  ���������
	 */
	public static final int getRandomNumber(int min, int max){
		return (int)(Math.random()*(max-min)+min);
	}
	
	/**
	 * �õ��������ɫ
	 * @return
	 */
	public static final Color getRandomColor(){
		return new Color(getRandomNumber(0, 256),getRandomNumber(0, 256),getRandomNumber(0, 256));
	}
	
	/**
	 * �ж�ָ�����ʵ�ʱ�䣬�����Ƿ�����
	 * @param percent  ָ���¼��ĸ���
	 * @return  ��������ˣ����� true ������  false��
	 */
	public static boolean isHappened(double percent){//0.3
		//
//		Math.random()  �õ�һ�������С��[0.0~1.0)  Math.random() �õ��Ľ��С��0.3�ĸ��ʾ���30%
		return Math.random() < percent;
	}
	
	
	
	
	
}
