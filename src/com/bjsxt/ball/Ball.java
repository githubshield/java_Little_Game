package com.bjsxt.ball;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.prism.paint.Gradient;

/**
 * 自由落体的球
 * @author gc
 *
 */
public class Ball {
	private int x,y;
	//帧速度
	private int speed;
	private int width;
	private Color color;
	private int dir;
	private boolean visible = true;
	//加速度
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
	 * 球绘制自身的方法
	 * @param g
	 */
	public void draw(Graphics g){
		//设置画笔的颜色
		g.setColor(color);
		g.fillOval(x, y, width, width);
		
		move();
	}
	
	/**
	 * 处理球的逻辑
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
				//速度触地损耗
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
