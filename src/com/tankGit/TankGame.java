/**
 * �����ƹ��ܣ�
 * 1.̹�˱������б�ը��Ч�����д���δ��ʵ�֣�
 * 2.̹�˲����ص���Panel��ͼ��
 * 3.��Ϸ�ܹ���ͣ�ͼ������ӵ����ٶȺ�̹���ٶ����0�����򲻱䣩
 * 4.����Ϸ����
 * 5.�Ż�����
 * ���ߣ�g
 */
package com.tankGit;
//��һ���� ����һ�� ��Ϣ¥520
//�ܶ�����313����

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
		jm1=new JMenu("��Ϸ��G��");
		jm1.setMnemonic('G');
		jmi1=new JMenuItem("��ʼ����Ϸ(S)");
		jmi1.addActionListener(this);
		jmi1.setActionCommand("newgame");
		jmi1.setMnemonic('S');
		jmi2=new JMenuItem("�˳���Ϸ��E��");
		jmi2.setMnemonic('E');
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		jmi3=new JMenuItem("�����˳���V��");
		jmi3.setMnemonic('V');
		jmi3.setActionCommand("save");
		jmi3.addActionListener(this);
		jmi4=new JMenuItem("��ȡ����(R)");
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
		this.setTitle("̹�˴�ս");
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
			//�û�Ҫ�˳�������
			//Recorder.KeepRecording();
			
			System.exit(0);
		}
		
		
		if(e.getActionCommand().equals("save"))
		{
			//������������
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
