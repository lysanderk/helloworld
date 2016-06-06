package com.lab.pojo;

import java.util.concurrent.TimeUnit;

public class Student extends BasePojo implements Runnable {

	private int id;
	private String name;
	private String gender;
	private boolean flag;
	private  ClassRoom classroom;
	private Thread prevThread;
	
	public void run(){
		
		if(prevThread!=null){
			try {
			 	prevThread.join();
			} catch ( Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(classroom.getId()+","+name);
		int i=(int) (1);
		try {
			
			TimeUnit.SECONDS.sleep(i);
			//atom
			//System.out.println("Class:"+classroom.getId()+",No:"+	classroom.addAtomCounter()+",Status:"+classroom.isFlag()+","+name);
			
			//int
			//System.out.println("Class:"+classroom.getId()+",No:"+	classroom.addAllIntCounter()+",Status:"+classroom.isFlag()+","+name);
		 	//System.out.println(classroom.getId()+","+classroom.addAllIntCounter());
			//System.out.println(classroom.addCASAllIntCounter());
			//System.out.println(classroom.getId()+","+classroom.addAtomCounter());
			//System.out.println(\+name);
			flag=true;
			//Thread.currentThread().join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Thread getPrevThread() {
		return prevThread;
	}


	public void setPrevThread(Thread prevThread) {
		this.prevThread = prevThread;
	}


	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
  
	public Student(int id,String name, Thread prevThread){
		this.prevThread=prevThread;
		this.id=id;
		this.name=name;
	}
	
	public Student(int id, String name){
			this(id, name, null);
	}
	
	public ClassRoom getClassroom() {
		return classroom;
	}

	public void setClassroom(ClassRoom classroom) {
		this.classroom = classroom;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
