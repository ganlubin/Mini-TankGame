package com.tankGit;

class Tank
{
	int x=100,y=100;
	
	int direct=8;
	
	int speed=2;
	boolean life=true;
	int type=0;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
}
