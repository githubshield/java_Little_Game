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
 * ��Ҫ���ࣺ����ࡢ����� frame ��������ƹ����
 * 
 * ƹ���򣺳�ʼ���ٶ� ��ֻ��  Y �����ϵ��ٶȡ� x ��0. 
 * ���µ��ٶ��𽥵�����y ����һ����������Ļ�ĵײ���10�� �����Ϊ���ϵ��𣬲����ʶȵ��ٶȵ���ġ�
 * �����ٶȱ�Ϊ0��ֹͣ����Ļ�ĵײ���
 * 
 * ����Ƕ��ball��ʹ�������������ʵ��ļ��������Ļ�е��������λ�á�
 * 
 * @author yhl
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
	
	//�����������ٶ�
	private int count = 0;
	private void addBall(){
		count ++;
		if(count == 20){
			count = 0;
			int x = MyUtil.getRandomNumber(0, Constant.GAME_WIDTH);
			int y = MyUtil.getRandomNumber(30, 60);
			Ball ball = new Ball(x, y, 10, Color.BLUE);
			//����������
			balls.add(ball);
			//�����ɼ������Ƴ�
			for(int i=0;i<balls.size();i++){
				if(!balls.get(i).isVisible())
					balls.remove(i);
			}
		}
	}
	/**
	 * ��ʼ������
	 */
	private void initFrame(){
		setTitle("�ҵ�С��Ϸ");
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
		//�õ�ͼƬ����Ļ���
		if(imgG == null){
			imgG = bufImg.getGraphics();
		}
		
		drawBk(imgG);
		//���еĻ��ƵĹ�������ͼƬ��������ɡ�
		paintBall(imgG);
		
		//ʹ��ϵͳ���ʽ�ͼƬ���Ƶ�frame ��
		g.drawImage(bufImg, 0, 0, null);
	}
	
	private void drawBk(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
	}
	
	
	/**
	 * ������
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
