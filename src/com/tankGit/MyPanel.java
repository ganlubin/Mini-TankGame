package com.tankGit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;

class MyPanel extends Panel implements KeyListener,Runnable
{
	private static final long serialVersionUID = 1L;
	//�����ҵ�̹�ˣ����ҵ�����ϣ�
	Hero hero=null;
	//String flag="newgame";
	//�ж��Ǽ����Ͼֻ�������Ϸ
	Vector<Node> nodes=new Vector<Node>();
	Vector<Enemy> ems=new Vector<Enemy>();
	Vector<Bomb> bb=new Vector<Bomb>();
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	int number=5;
	
	//���캯��
	public MyPanel(String flag)
	{
		
		//�ָ���¼
		//Recorder.getRecording();
		
		
		hero=new Hero(120,150);
		if(flag.equals("newgame"))
		{
			for(int i=0;i<number;i++)
			{
				Enemy em=new Enemy((i+1)*50,20);
				em.setType(1);
				em.setDirect(2);
				//em.setets(ems);
				Thread t=new Thread(em);
				t.start();
				Shot s=new Shot(em.x,em.y+20,2);
				em.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				ems.add(em);
			}
		}else
		{
			nodes=new Recorder().getNodesAndEnNums();
			for(int i=0;i<nodes.size();i++)
			{
				Node node=nodes.get(i);
				Enemy em=new Enemy(node.x,node.y);
				em.setType(1);
				em.setDirect(2);
				//em.setets(ems);
				Thread t=new Thread(em);
				t.start();
				Shot s=new Shot(em.x,em.y+20,2);
				em.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				ems.add(em);
			}
		}
		
		try 
		{
			image1=ImageIO.read(new File("1.png"));
			image2=ImageIO.read(new File("2.png"));
			image3=ImageIO.read(new File("3.png"));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}
	
	public void ShouInfor(Graphics g)
	{
		this.drawTank(70, 430, g, 8, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNum()+"", 100, 435);
		this.drawTank(150, 430, g, 8, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.getMyLife()+"",180, 435);
		
		g.setColor(Color.BLACK);
		Font f=new Font("����", Font.BOLD, 20);
		g.setFont(f);
		g.drawString("�����ܳɼ�", 500, 30);
		
		this.drawTank(540,60,g,0,0);
		
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getAllEnNum()+"", 560, 60);
	
	}
	
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 500, 400);
		
		this.ShouInfor(g);
		
		
			
		if(hero.life==true) {
			this.drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
			
			
		}
		
		for(int i=0;i<bb.size();i++)
		{
			Bomb b=bb.get(i);
			
			if(b.life>6)
			{
				g.drawImage(image1, b.x, b.y, 30, 30,this);
			}
			else if(b.life>3)
			{
				g.drawImage(image2, b.x, b.y, 30, 30,this);
			}
			else
			{
				g.drawImage(image3, b.x, b.y, 30, 30,this);
			}
			
			b.lifeDown();
			
			if(b.life==0)
			{
				bb.remove(b);
			}
		}
	
		for(int i=0;i<ems.size();i++)
		{
			Enemy et=ems.get(i);
			if(et.life)
			{
				this.drawTank(et.getX(), et.getY(),g ,et.getDirect(),0);
				for(int j=0;j<et.ss.size();j++)
				{
					Shot EnemyShot=et.ss.get(j);
					if(EnemyShot.life)
					{
						g.draw3DRect(EnemyShot.x, EnemyShot.y, 1, 1, false);
					}else
					{
						et.ss.remove(EnemyShot);
					}
				}
			}
			
		}
		
		
		for(int i=0;i<this.hero.ss.size();i++)
		{
			Shot MyShot=hero.ss.get(i);
			
			if(MyShot!=null&&MyShot.life==true)
			{
				g.draw3DRect(MyShot.x, MyShot.y, 1, 1, false);
			}
			
			if(MyShot.life==false)
			{
				hero.ss.remove(MyShot);
			}
		}
		
		
	}
	
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			this.hero.setDirect(2);
			this.hero.moveDown();
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			this.hero.setDirect(8);
			this.hero.moveUp();
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			this.hero.setDirect(6);
			this.hero.moveRight();
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			this.hero.setDirect(4);
			this.hero.moveLeft();
		}
		
		if(e.getKeyChar()==KeyEvent.VK_SPACE)
		{
			if(hero.ss.size()<5)
				this.hero.ShotEnemy();
		}
		this.repaint();
		
		
	}
	
	
	
	public void HitEnemy()
	{
		for(int i=0;i<hero.ss.size();i++)
		{
			//ȡ���ӵ�
			Shot s=hero.ss.get(i);
			//�ж��ӵ��Ƿ���Ч
			if(s.life)
			{
				for(int j=0;j<ems.size();j++)
				{
					Enemy et=ems.get(j);
					
					if(et.life)
					{
						if(this.HitTank(s, et))
						{

							Recorder.reduceEnemy();
							Recorder.addEnNumRec();
						}
					}
				}
			}
		}
	}
	
	public void HitMe()
	{
		
		for(int i=0;i<ems.size();i++)
		{
			Enemy e=ems.get(i);
			
			for(int j=0;j<e.ss.size();j++)
			{
				Shot enemyshot=e.ss.get(j);
				
				if(this.HitTank(enemyshot, hero))
				{
					Recorder.reduceMe();
				}
				
				
			}
		}
	}
	
	//�ж��ӵ��Ƿ���е���̹��()
	public boolean HitTank(Shot s,Tank e)
	{
		boolean bbb=false;
		//�ж�̹�˵ķ���
		switch(e.direct)
		{
		case 2:
			if(s.x>=e.x&&s.x<=e.x+20&&s.y>=e.y&&s.y<=e.y+30)
			{
				//������(�ӵ�����������̹������)
				s.life=false;
				e.life=false;
				bbb=true;
				Bomb b=new Bomb(e.x+10,e.y+15);
				bb.add(b);
			}
			break;
		case 4:
			if(s.x>=e.x&&s.x<=e.x+30&&s.y>=e.y&&s.y<=e.y+20)
			{
				s.life=false;
				e.life=false;
				bbb=true;
				Bomb b=new Bomb(e.x+15,e.y+10);
				bb.add(b);
			}
			break;
		case 6:
			if(s.x>=e.x&&s.x<=e.x+30&&s.y>=e.y&&s.y<=e.y+20)
			{
				s.life=false;
				e.life=false;
				bbb=true;
				Bomb b=new Bomb(e.x+15,e.y+10);
				bb.add(b);
			}
			break;
		case 8:
			if(s.x>=e.x&&s.x<=e.x+20&&s.y>=e.y&&s.y<=e.y+30)
			{
				//������(�ӵ�����������̹������)
				s.life=false;
				e.life=false;
				bbb=true;
				Bomb b=new Bomb(e.x+10,e.y+15);
				bb.add(b);
			}
			break;
		}
		return bbb;
	}
	
	
	//��̹�˵ĺ���
	public void drawTank(int x,int y,Graphics g,int direct,int type)
	{
		switch(type)//�ж�����
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.RED);
			break;
		}
		
		//����
		switch(direct)
		{
		case 8://����
			g.fill3DRect(x-4, y-10, 5, 30,false);
			g.fill3DRect(x+11, y-10, 5, 30,false);
			g.fill3DRect(x+1, y-5, 10, 20,false);
			g.fillOval(x, y, 10, 10);
			g.drawLine(x+5, y+5, x+5, y-15);
			break;
		case 2://����
			g.fill3DRect(x-4, y-10, 5, 30,false);
			g.fill3DRect(x+11, y-10, 5, 30,false);
			g.fill3DRect(x+1, y-5, 10, 20,false);
			g.fillOval(x, y, 10, 10);
			g.drawLine(x+5, y+5, x+5, y+25);
			break;
		case 4:
			g.fill3DRect(x-10, y-5, 30, 5,false);
			g.fill3DRect(x-10, y+10, 30, 5,false);
			g.fill3DRect(x-5, y, 20, 10,false);
			g.fillOval(x, y, 10, 10);
			g.drawLine(x, y+5, x-15, y+5);
			break;
		case 6:
			g.fill3DRect(x-10, y-5, 30, 5,false);
			g.fill3DRect(x-10, y+10, 30, 5,false);
			g.fill3DRect(x-5, y, 20, 10,false);
			g.fillOval(x, y, 10, 10);
			g.drawLine(x, y+5, x+25, y+5);
			break;
		}
		
		
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void run() {
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.HitEnemy();
			
			this.HitMe();
			
			this.repaint();
		}
		
	}

	
	
	
}
