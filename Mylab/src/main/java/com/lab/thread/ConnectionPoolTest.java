package com.lab.thread;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {

	static ConnectionPool pool =new ConnectionPool(10);
	static CountDownLatch start =new CountDownLatch(1);
	static CountDownLatch end;
	
	
	public static void main(String as[]){
		int threadCount=30;
		end=new CountDownLatch(threadCount);
		int count=20;
		AtomicInteger got =new AtomicInteger();
		AtomicInteger notGot=new AtomicInteger();
		
		for(int i=0;i<threadCount;i++){
			Thread thread=new Thread(new ConnectionRunner(count,got,notGot),"Thread");
			thread.start();
		}
		start.countDown();
		try {
			end.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("total:"+threadCount * count+", got:"+got+",notGot:"+notGot);
		
	}
	
	static class ConnectionRunner implements Runnable{
		int count;
		AtomicInteger got;
		AtomicInteger notGot;
		
		public ConnectionRunner(int count,AtomicInteger got,AtomicInteger notGot){
			this.count=count;
			this.got=got;
			this.notGot=notGot;
		}
		
		public void run()  {
			try {
				start.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(count>0){
				try{
				Connection connection= pool.fetchConnection(500);
				if(connection!=null){
					try {
						//connection.createStatement();
						 connection.commit();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally{
						pool.releaseConnection(connection);
						got.incrementAndGet();
					}
				}
				else{
					notGot.incrementAndGet();
				}
			}finally{count--;}
			}
			end.countDown();
			
		}
		
		
	}

}
