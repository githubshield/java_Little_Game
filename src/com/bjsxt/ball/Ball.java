package com.bjsxt.ball;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.prism.paint.Gradient;

/**
 * �����������
 * @author yhl
 *
 */
public class Ball {
	private int x,y;
	//֡�ٶ�
	private int speed;
	private int width;
	private Color color;
	private int dir;
	private boolean visible = true;
	//���ٶ�
	public static final int ACC_SPEED = 1 ;
	
	public static final int DIR_UP = 1;
	public static final int DIR_DOWN = 0;
	
	Ball(int x, int y, int width, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.speed = 0;
		this.width = width;
		this.color = color;
		this.dir = DIR_DOWN;
	}
	
	/**
	 * ���������ķ���
	 * @param g
	 */
	public void draw(Graphics g){
		//���û��ʵ���ɫ
		g.setColor(color);
		g.fillOval(x, y, width, width);
		
		move();
	}
	
	/**
	 * ��������߼�
	 */
	private void move(){
		if(dir == DIR_UP){
			y -= speed;
			speed -= ACC_SPEED;
			speed = speed < 0 ? 0 : speed;
			if(speed == 0){
				dir = DIR_DOWN;
			}
		}else{
			y += speed;
			speed += ACC_SPEED;
			if(y >= Constant.GAME_HEIGHT-width){
				dir = DIR_UP;
				//�ٶȴ������
				speed -= 4;
				speed = speed < 0 ? 0 : speed;
				if(speed == 0){
					visible = false;
				}
			}
		}
	}
	
	public  boolean isVisible(){
		return visible;
	}
	
	
}
