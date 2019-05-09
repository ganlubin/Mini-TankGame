package com.tankGit;

import java.util.Vector;

class Enemy extends Tank implements Runnable
{
	Vector<Shot> ss=new Vector<Shot>();
	
	//Vector<Enemy> ets=new Vector<Enemy>();
	
	int times=0;
	public Enemy(int x, int y) {
		super(x, y);
	}
	
	/*public void setets(Vector<Enemy> vv)
	{
		this.ets=vv;
	}*/
	
	
	/*//判断是否碰到了其他敌人的坦克
	public boolean isTouchOtherEnemy()
	{
		boolean b=false;
		//---
		switch(this.direct)
		{
		case 2:
		case 8:
			for(int i=0;i<ets.size();i++)
			{
				Enemy et=ets.get(i);
				if(et!=this)
				{
					if(et.direct==2||et.direct==8)
					{
						if(Math.abs(this.x-et.x)<=10&&Math.abs(this.y-et.y)<=30)
						{
							return false;
						}else
						{
							return true;
						}
					}
				}
			}
			break;
		case 4:
		case 6:
			for(int i=0;i<ets.size();i++)
			{
				Enemy et=ets.get(i);
				if(et!=this)
				{
					if(et.direct==4||et.direct==6)
					{
						if(Math.abs(this.x-et.x)<=30&&Math.abs(this.y-et.y)<=10)
						{
							return false;
						}else
						{
							return true;
						}
					}
				}
			}
			break;
		}
		return b;
	}
	*/
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		while(true) {
			
			switch(this.direct)
			{
			case 8:
				for(int i=0;i<20;i++)
				{
					if(y>0/*&&!this.isTouchOtherEnemy()*/)
						y-=speed;
					try
					{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				break;
			case 2:
				for(int i=0;i<20;i++)
				{
					if(y<285/*&&!this.isTouchOtherEnemy()*/)
						y+=speed;
					try
					{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				break;
			case 4:
				for(int i=0;i<20;i++)
				{
					if(x>0/*&&!this.isTouchOtherEnemy()*/)
						x-=speed;
					try
					{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				break;
			case 6:
				for(int i=0;i<20;i++)
				{
					if(x<385/*&&!this.isTouchOtherEnemy()*/)
						x+=speed;
					try
					{
						Thread.sleep(50);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				break;
			}
			this.direct=(int )(Math.random()*9);
			this.times++;
			if(times%2==0)
			{
				if(ss.size()<5)
				{
					Shot s=null;
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
			}
			if(this.life==false)
			{
				break;
			}
			
			
		}
	}
	
	
	
}
