package com.lab.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountLatchTest {

	
	private static CountDownLatch match=new CountDownLatch(1);
	private static CountDownLatch individuals=new CountDownLatch(10);
	
	
public static void main(String asd[]) throws InterruptedException{
	match.countDown();
	System.out.println("Match Begin");
	
	for(int i=0;i<10;i++){
		 Thread runner=new Thread("Name:"+i){
				public void run(){
					try {
						match.await();
						TimeUnit.SECONDS.sleep((long) (Math.random()*1));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("This:"+this.getName()+" :is arrived");
					individuals.countDown();
					try {
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} ;
			runner.start();
	}
	individuals.await();
	System.out.println("Match End");

}
}
