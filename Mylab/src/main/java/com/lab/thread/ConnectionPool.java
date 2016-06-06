package com.lab.thread;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {
	

	private LinkedList<Connection> pool=new  LinkedList<Connection>();
	
	
	public ConnectionPool(int initialSize){
		for(int i=0;i<initialSize;i++){
			pool.add(ConnectionDriver.createConnection());
		}
	}
	
	public Connection fetchConnection(long mills){
		synchronized(pool){
			if(mills<=0){
				while(pool.isEmpty()){
					try {
						//System.out.println("pool waiting");
						pool.wait();
					
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return pool.removeFirst();
			}
			else{
				long future=System.currentTimeMillis()+mills;
				long remaing=mills;
				while(pool.isEmpty()&&remaing>0){
					try {
						pool.wait(remaing);
						remaing=future-System.currentTimeMillis();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(!pool.isEmpty())
					return pool.removeFirst();
				else 
					return null;
			}
		}
	}
	
	public void releaseConnection(Connection conn){
		synchronized(pool){
			pool.addLast(conn);
			pool.notifyAll();
		}
	}
}
