package com.lab.thread;

public class WaitNotifyTest1 extends Thread {

	private Object current;
	
	
	private Object previous;


	public void setPrevious(String name,Object previous,Object current) {
		this.previous = previous;
		this.current=current;
	}


	public void run(){
		int i=0;
		synchronized(previous){
			synchronized(current){
			
				while(i<10){
					System.out.println(""+Thread.currentThread().getName()+":"+i++);
					
					current.notify();
					
					}
				try {
					previous.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
			
		}
	}
	
	
  public static void main(String s[]){
	  
	  
	  Object a1=new Object();
	  Object b1=new Object();
	  Object c1=new Object();
	  
	  
	  WaitNotifyTest1 a=new WaitNotifyTest1();
	  WaitNotifyTest1 b=new WaitNotifyTest1();
	  WaitNotifyTest1 c=new WaitNotifyTest1();
	  
	  a.setPrevious("a",c1,a1);
	  b.setPrevious("b",a1,b1);
	  c.setPrevious("c",b1,c1);
	  
	  
	  
	  
	  
	  a.start();
	  b.start();
	  c.start();
  }
}
