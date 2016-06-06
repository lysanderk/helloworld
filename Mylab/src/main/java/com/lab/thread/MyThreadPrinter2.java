package com.lab.thread;

public class MyThreadPrinter2 implements Runnable {   
	  
    private String name;   
    private Object prev;   
    private Object self;   
  
    private MyThreadPrinter2(String name, Object prev, Object self) {   
        this.name = name;   
        this.prev = prev;   
        this.self = self;   
    }   
  
    public void run() {   
        int count = 10;   
        while (count > 0) {   
            synchronized (prev) {   
                synchronized (self) {   
            
                    count--;  
                    
                    self.notify();   
                    System.out.println(name+" is notified");   
                }   
                try {   
                	//Thread.sleep(200);
                    prev.wait();   
                    System.out.println(prev+" waited");   
                } catch (InterruptedException e) {   
                    e.printStackTrace();   
                }   
            }   
  
        }   
    }   
  
    public static void main(String[] args) throws Exception {   
        Object a = "a";   
        Object b = "b";   
        Object c = "c";   
        MyThreadPrinter2 pa = new MyThreadPrinter2("A", c, a);   
        MyThreadPrinter2 pb = new MyThreadPrinter2("B", a, b);   
        MyThreadPrinter2 pc = new MyThreadPrinter2("C", b, c);   
           
           
        new Thread(pa).start();
        new Thread(pb).start();
        new Thread(pc).start();    }   
}  
