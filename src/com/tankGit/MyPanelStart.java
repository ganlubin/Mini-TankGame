package com.tankGit;

import java.awt.*;
import javax.swing.JPanel;

class MyPanelStart extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	int times=0;
	public void paint (Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 500, 400);
		if(times%2==0)
		{
			g.setColor(Color.yellow);
			Font myFont=new Font("»ªÎÄÐÂÎº", Font.BOLD, 30);
			g.setFont(myFont);
			g.drawString("stage:1", 180, 100);
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			times++;
			this.repaint();
		}
	}
	
}
