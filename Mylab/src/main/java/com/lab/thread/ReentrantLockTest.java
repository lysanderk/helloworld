package com.lab.thread;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest<T> {

	public ReentrantLock lock=new ReentrantLock();
	public Condition consumer_is_waiting;
	public Condition producer_is_waiting;
	private LinkedList<T> pool=new  LinkedList<T>();
	 Random r=new Random();
	private static volatile boolean consumer_need_speed_up=false;
	private static volatile boolean producer_need_speed_up=false;
	public final int MAX_ZISE=10;
	
	public ReentrantLockTest(){
		consumer_is_waiting=lock.newCondition();
		producer_is_waiting=lock.newCondition();
	}
	
	public void add(T obj){
		lock.lock();
		try{
			while(MAX_ZISE==pool.size()){
				producer_is_waiting.await();
			}
			pool.add(obj);
			int consumer_await_size=lock.getWaitQueueLength(consumer_is_waiting);
			int producerawait_size=lock.getWaitQueueLength(producer_is_waiting);
			System.out.println("add: Consumer:"+consumer_await_size+",Producer"+producerawait_size+",Pool:"+pool.size());
			if(consumer_await_size-producerawait_size>10){
				consumer_need_speed_up=false;
				producer_need_speed_up=true;
			}
			if(producer_need_speed_up){
			}
			else
				TimeUnit.SECONDS.sleep(r.nextInt(2));
			
			consumer_is_waiting.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	 
	public T remove(){
		lock.lock();
		try{
			while(pool.size()==0){
				consumer_is_waiting.await();
			}
			T t= pool.remove();
			int consumer_await_size=lock.getWaitQueueLength(consumer_is_waiting);
			int producerawait_size=lock.getWaitQueueLength(producer_is_waiting);
			System.out.println("add: Consumer:"+consumer_await_size+",Producer"+producerawait_size+",Pool:"+pool.size());
			if(producerawait_size-consumer_await_size>10){
				consumer_need_speed_up=true;
				producer_need_speed_up=false;
			}
			if(consumer_need_speed_up){
				
			}
			else
				TimeUnit.SECONDS.sleep(r.nextInt(2));
			producer_is_waiting.signalAll();
			return t;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}finally{
			lock.unlock();
		}
	}
	
	class  Consumer  implements Runnable{
		private T t;
		private void Set(T t){
			this.t=t;
		}
		public void run(){
			if(consumer_need_speed_up){
				System.out.println("Consumer is speed up");
				t=remove();
				t=remove();
			}
				t=remove();
		}
	}
	
	class Producer implements Runnable{
		private T t;
		private void Set(T t){
			this.t=t;
		}
		public void run(){
			if(producer_need_speed_up){
				System.out.println("Producer is speed up");
				add(t);
				add(t);
			}
				 add(t);
		}
	}
	
	public static void main(String ags[]){
	final	ReentrantLockTest<Integer> obj=new 	ReentrantLockTest<Integer>();
	  
	  new Thread(){
		  public void run(){
			  Executor producerExe=  Executors.newFixedThreadPool(50);
			int counter=0;
			 while(true){
				 if(counter%13==0){
					 try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 }
					ReentrantLockTest<Integer>.Producer producer=obj.new Producer();
					producer.Set(new Integer(counter++));
					producerExe.execute(producer);
			 }
		  }
	  }.start();
	  
	  new Thread(){
		  public void run(){
			  Executor consumerExe= Executors.newFixedThreadPool(50);
				int counter=0;
			  while(true){
				  if(counter%17==0){
						 try {
							TimeUnit.SECONDS.sleep(3);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					 }
					  ReentrantLockTest<Integer>.Consumer consumer= obj.new Consumer();
					  consumer.Set(new Integer(counter++));
					  consumerExe.execute(consumer);
			  }
		  }
	  }.start();
	  
	  
	
	  
	}
}
