package com.tankGit;
class Shot implements Runnable
{
	int x,y;
	int direct;
	int speed=3;
	boolean life=true;
	public Shot(int x,int y,int direct)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	@Override
	public void run() {
		
		while(true)
		{
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			switch(this.direct)
			{
			case 2:
				y+=speed;
				break;
			case 4:
				x-=speed;
				break;
			case 6:
				x+=speed;
				break;
			case 8:
				y-=speed;
				break;
			}
			//System.out.println("×ø±êx="+x+"y="+y);
			
			if(x<0||x>500||y<0||y>400)
			{
				life=false;
				break;
			}
			
		}
	}
}
