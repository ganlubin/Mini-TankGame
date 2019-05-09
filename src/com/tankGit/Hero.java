package com.tankGit;

import java.util.Vector;

class Hero extends Tank
{
	
	
	Shot s=null;
	Vector<Shot> ss=new Vector<Shot>();
	public void ShotEnemy()
	{
		
		
		switch(this.direct)
		{
		case 2:
			s=new Shot(x+4,y+26,2);
			ss.add(s);
			break;
		case 4:
			s=new Shot(x-15,y+4,4);
			ss.add(s);
			break;
		case 6:
			s=new Shot(x+25,y+4,6);
			ss.add(s);
			break;
		case 8:
			s=new Shot(x+4,y-18,8);
			ss.add(s);
			break;
		}
		Thread t=new Thread(s);
		t.start();
	}
	
	public Hero(int x, int y) {
		super(x, y);
	}

	public void moveUp()
	{
		y-=speed;
	}
	public void moveRight()
	{
		x+=speed;
	}
	public void moveLeft()
	{
		x-=speed;
	}
	public void moveDown()
	{
		y+=speed;
	}
	
}