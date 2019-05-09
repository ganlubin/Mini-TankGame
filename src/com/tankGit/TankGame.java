/**
 * 待完善功能：
 * 1.坦克被击中有爆炸的效果（有代码未能实现）
 * 2.坦克不会重叠（Panel画图）
 * 3.游戏能够暂停和继续（子弹的速度和坦克速度设成0，方向不变）
 * 4.进游戏声音
 * 5.优化代码
 * 作者：g
 */
package com.tankGit;
//周一下午 周四一天 信息楼520
//周二晚上313机房

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class TankGame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	MyPanel m1=null;
	MyPanelStart mps=null;
	
	
	JMenuBar jmb=null;
	JMenu jm1=null;
	JMenuItem jmi1=null;
	JMenuItem jmi2=null;
	JMenuItem jmi3=null;
	JMenuItem jmi4=null;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		TankGame tg1=new TankGame();
	}
	
	public TankGame()
	{
		//m1=new MyPanel();
		//this.add(m1);
		//Thread t2=new Thread(m1);
		//t2.start();
		//this.addKeyListener(m1);
		mps=new MyPanelStart();
		Thread t=new Thread(mps);
		t.start();
		this.add(mps);
		
		jmb=new JMenuBar();
		jm1=new JMenu("游戏（G）");
		jm1.setMnemonic('G');
		jmi1=new JMenuItem("开始新游戏(S)");
		jmi1.addActionListener(this);
		jmi1.setActionCommand("newgame");
		jmi1.setMnemonic('S');
		jmi2=new JMenuItem("退出游戏（E）");
		jmi2.setMnemonic('E');
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		jmi3=new JMenuItem("存盘退出（V）");
		jmi3.setMnemonic('V');
		jmi3.setActionCommand("save");
		jmi3.addActionListener(this);
		jmi4=new JMenuItem("读取存盘(R)");
		jmi4.setMnemonic('R');
		jmi4.setActionCommand("read");
		jmi4.addActionListener(this);
		
		
		jmb.add(jm1);
		jm1.add(jmi1);
		jm1.add(jmi4);
		jm1.add(jmi3);
		jm1.add(jmi2);
		
		this.setJMenuBar(jmb);
		this.setIconImage((new ImageIcon("source/123.jpg")).getImage());
		this.setTitle("坦克大战");
		this.setSize(650, 600);
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("newgame"))
		{
			m1=new MyPanel("newgame");
			this.add(m1);
			Thread t2=new Thread(m1);
			t2.start();
			this.remove(mps);
			this.addKeyListener(m1);
			this.setVisible(true);
			
		}
		
		if(e.getActionCommand().equals("exit"))
		{
			//用户要退出，保存
			//Recorder.KeepRecording();
			
			System.exit(0);
		}
		
		
		if(e.getActionCommand().equals("save"))
		{
			//保存坐标数量
 			Recorder rd=new Recorder();
			rd.setEts(m1.ems);
			rd.KeepRecorderAndEnemy();
			
			System.exit(0);
		}
		
		if(e.getActionCommand().equals("read"))
		{
			m1=new MyPanel(" ");
			//m1.flag=" ";
			
			this.add(m1);
			Thread t2=new Thread(m1);
			t2.start();
			this.remove(mps);
			this.addKeyListener(m1);
			this.setVisible(true);
		}
		
	}
}
