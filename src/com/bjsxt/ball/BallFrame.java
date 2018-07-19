package com.bjsxt.ball;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.hbkjxy.util.MyUtil;

/**
 * 需要的类：入口类、主面板 frame 、跳动的乒乓球。
 * 
 * 乒乓球：初始的速度 ，只有  Y 方向上的速度。 x 是0. 
 * 向下的速度逐渐递增，y 坐标一旦到达了屏幕的底部，10， 方向改为向上弹起，并有适度的速度的损耗。
 * 最终速度变为0，停止在屏幕的底部。
 * 
 * 如果是多个ball，使用容器管理，在适当的间隔增加屏幕中的球。随机的位置。
 * 
 * @author gc
 * 
 */

public class BallFrame extends Frame implements Runnable{
	
	private ArrayList<Ball> balls = new ArrayList<>();
	
	private BufferedImage bufImg = new BufferedImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
	private Graphics imgG;
	
	
	public BallFrame() {
		initFrame();
		initRepaintThread();
	}
	
	//控制添加球的速度
	private int count = 0;
	private void addBall(){
		count ++;
		if(count == 20){
			count = 0;
			int x = MyUtil.getRandomNumber(0, Constant.GAME_WIDTH);
			int y = MyUtil.getRandomNumber(30, 60);
			Ball ball = new Ball(x, y, 10, Color.BLUE);
			//随机的添加球，
			balls.add(ball);
			//将不可见的球移除
			for(int i=0;i<balls.size();i++){
				if(!balls.get(i).isVisible())
					balls.remove(i);
			}
		}
	}
	/**
	 * 初始化窗口
	 */
	private void initFrame(){
		setTitle("我的小游戏");
		setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		setLocation(Constant.SYS_SCREEN_W-Constant.GAME_WIDTH>>1, Constant.SYS_SCREEN_H-Constant.GAME_HEIGHT>>1);
		
		setResizable(false);
		
		addEventListener();
		
		setVisible(true);
	}
	
	private void initRepaintThread(){
		new Thread(this).start();
	}
	
	
	public void update(Graphics g) {
		//得到图片对象的画笔
		if(imgG == null){
			imgG = bufImg.getGraphics();
		}
		
		drawBk(imgG);
		//所有的绘制的工作都让图片画笔来完成。
		paintBall(imgG);
		
		//使用系统画笔将图片绘制到frame 上
		g.drawImage(bufImg, 0, 0, null);
	}
	
	private void drawBk(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
	}
	
	
	/**
	 * 绘制球
	 * @param g
	 */
	private void paintBall(Graphics g){
		for(int i=0;i<balls.size();i++){
			balls.get(i).draw(g);
		}
	}
	
	
	
	private void addEventListener(){
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void run() {
		try {
			while(true){
				repaint();
				addBall();
				Thread.sleep(40);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
