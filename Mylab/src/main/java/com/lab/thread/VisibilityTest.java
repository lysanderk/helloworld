package com.lab.thread;

public class VisibilityTest extends Thread {

	public volatile static Boolean flag=true;
	
	public void run(){
		int i=0;
		while(flag){
			i++;
			/* comment out #1:  sync the copy memory to the main main memory*/
			/* for(int k=0;k<1000000;k++){
			    new Object();
			}*/
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// synchronized (flag) {
		 	//	i++;		 };
			//System.out.print("1"); 
		//	 }
		}
		System.out.println(i);
	}
	
	public static void thread2(){
		new Thread(){public void run(){{
			flag=false;
			System.out.println("the flag is switched");
			}}
		}.start();
	}
	
	public static void main(String ars[]){
		VisibilityTest thread=new VisibilityTest();
		thread.start();
		thread2();
	}
	
}
