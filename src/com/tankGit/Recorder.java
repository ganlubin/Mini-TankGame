package com.tankGit;
import java.io.*;
import java.util.Vector;
class Recorder {
	
	private static int enNum=20;
	private static int myLife=300;
	private static int allEnNum=0;
	
	private  FileWriter fw=null;
	private  BufferedWriter bw=null;
	
	private FileReader fr=null;
	private BufferedReader br=null;
	private Vector<Enemy> ets=new Vector<Enemy>();
	
	static Vector<Node> nodes=new Vector<Node>();
	public Vector<Node> getNodesAndEnNums()
	{
		try {
			fr=new FileReader("e:/myRecording.txt");
			br=new BufferedReader(fr);
			String n=br.readLine();
			n=br.readLine();
			while((n=br.readLine())!=null)
			{
				String []xyz=n.split(" ");
				for(int i=0;i<xyz.length;i++)
				{
					Node node=new Node(Integer.parseInt(xyz[i]),Integer.parseInt(xyz[i]) , Integer.parseInt(xyz[i]));
					nodes.add(node);
				}
			}
			
			
			allEnNum=Integer.parseInt(n);
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return nodes;
	}
	
	
	public Vector<Enemy> getEts() {
		return ets;
	}

	public void setEts(Vector<Enemy> ets) {
		this.ets = ets;
	}

	//存盘(字符流)
	public void KeepRecording()
	{
		try {
			fw=new FileWriter("e:/myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void KeepRecorderAndEnemy()
	{
		try {
			fw=new FileWriter("e:/myRecording.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
			
			//保存活着的敌人坦克坐标方向
			for(int i=0;i<ets.size();i++)
			{
				Enemy et=ets.get(i);
				if(et.life)
				{
					String recode=et.x+" "+et.y+""+et.direct;
					//写入
					bw.write(recode+"\r\n");
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void getRecording()
	{
		try {
			fr=new FileReader("e:/myRecording.txt");
			br=new BufferedReader(fr);
			
			String n=br.readLine();
			allEnNum=Integer.parseInt(n);
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	public static int getAllEnNum() {
		return allEnNum;
	}
	public static void setAllEnNum(int allEnNum) {
		Recorder.allEnNum = allEnNum;
	}
	public static int getEnNum() {
		return enNum;
	}
	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}
	public static int getMyLife() {
		return myLife;
	}
	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}
	
	public static void reduceEnemy()
	{
		enNum--;
	}
	
	public static void reduceMe()
	{
		myLife--;
	}
	
	public static void addEnNumRec()
	{
		allEnNum++;
	}
	
}
