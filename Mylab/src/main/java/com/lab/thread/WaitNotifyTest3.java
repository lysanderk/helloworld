package com.lab.thread;

import java.util.LinkedList;
import java.util.Queue;

public class WaitNotifyTest3 {  
  public Queue queue=new LinkedList();
  public int MAX_SIZE=100;
  
  
  public WaitNotifyTest3(){
	  for(int i=0;i<100;i++){
		  queue.add(i);
	  }
  }
  
  public synchronized  void put(Object  o) throws InterruptedException{
	  while(queue.size()==MAX_SIZE){
		  wait();
	  }
	  queue.add(o);
	  notifyAll();
  }
  
  public synchronized Object get() throws InterruptedException{
	  while(0==queue.size()){
		  System.out.println("waiting.."+Thread.currentThread().getName());
		  wait();
	  }
	  notify();
	  Object obj=queue.remove();
	  return obj;
  }
  
  public static void main(String a[]){
	 final  WaitNotifyTest3 queue=new WaitNotifyTest3();
	  for(int i=0;i<100;i++){
		  new Thread("Thread:"+i){
			  public void run(){
				try {
					Object obj=  queue.get();
					System.out.println(obj);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			  
		  }.start();
	  }
  }
}