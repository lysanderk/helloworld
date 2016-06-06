package com.lab.thread;

import java.util.concurrent.TimeUnit;

public class WaitNotifyTest {
	
	private static boolean flag=true;
	private static Object locker=new Object();
	
	static class Notify implements Runnable{
		public void run(){
			System.out.println("Class Notify is start");
			 synchronized(locker){
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				locker.notify();
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Notified");
				//flag=false;
		 	};
		 	
		/* 	synchronized(locker){
		 		System.out.println("locker again");
		 	}*/
		}
	}
	
	static class  Wait implements Runnable{
		public void run(){
			synchronized(locker){
				System.out.println("Class wait is start and get the lock");
				while(flag){
					try {
						System.out.println("Class wait is going to the waitingQueue");
						locker.wait();
						System.out.println("Class wait is going to the synchronizedQueue");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
			System.out.println("Class wait is finished");
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread( new Wait()).start();
		new Thread( new Notify()).start();
	}

}
