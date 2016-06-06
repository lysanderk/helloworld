package com.lab.pojo;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.lab.thread.ApplicationContext;

public class ClassRoom  extends BasePojo implements Runnable{

	private List<Student> students;
	
	private int Id;
	
	private String name;
	
	private AtomicBoolean flag=new AtomicBoolean(false);
	 
	private   AtomicInteger counter=new AtomicInteger();
	
	private    int allintCounter;
	
	public  void run() {
		startRun();
	}
	public void startRun(){
		
		Thread prev=Thread.currentThread();
		for(int i=0;i<students.size();i++){
			Student st=students.get(i);
			Thread thread =new Thread(st);
			st.setPrevThread(prev);
			thread.setName("Thread :"+name+":"+st.getName());
			thread.start();
			prev=thread;
			try {
			 	//thread.join();
				//System.out.println(st.getName()+" is joined");
			} catch ( Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public  int getAtomCounter() {
		return counter.get();
	}
	
	
	public  int addAtomCounter(){
		int i=counter.incrementAndGet();
		if(i==ApplicationContext.STUDENT_SIZE/ApplicationContext.CLASS_ROOM__SIZE)
			flag.set(true);
		return	i;
	}
	
	public  synchronized int addAllIntCounter(){
		return ++allintCounter;
	}
	
	public int  addCASAllIntCounter(){
		while(true){
			int i=allintCounter;
			int s = i + 1;
			if(i==allintCounter){
				allintCounter=s;
				return s;
			}
			
		}
	}
	
	
	public boolean isFlag() {
		return flag.get();
	}

	public ClassRoom(int id, String name){
		this.Id=id;
		this.name=name;
	}
	
	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	
	
}
