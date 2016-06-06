package com.lab.thread;

import java.util.ArrayList;
import java.util.List;

import com.lab.pojo.ClassRoom;
import com.lab.pojo.Student;

public class ApplicationContext {

	 public static int CLASS_ROOM__SIZE=5;
	 public static int  STUDENT_SIZE=250;
	 
	 private   List<ClassRoom> classrooms=new ArrayList();
	 private   List<Student> students=new ArrayList();
	 
	 public   void initClassRoom(){
		 
		 for(int i=1;i<=CLASS_ROOM__SIZE;i++ ){
			 classrooms.add(new ClassRoom(i,"Math Class:"+i));
		 }
	 }
	 
	 public   void initStudents(){
		 for(int i=1;i<=STUDENT_SIZE;i++ ){
			 students.add(new Student(i,"Student :"+i));
		 }
	 }
	 
	 public   void assignStudents(){
		 for(int i=0;i<CLASS_ROOM__SIZE;i++ ){
				ClassRoom cr=classrooms.get(i);
				List<Student> tmp=new ArrayList();
			 for(int j=i*STUDENT_SIZE/CLASS_ROOM__SIZE;j<STUDENT_SIZE;j++){
				Student st=students.get(j);
				st.setClassroom(cr);
				 tmp.add(st);
					if(tmp.size()==STUDENT_SIZE/CLASS_ROOM__SIZE)
						break;
			 }
			 cr.setStudents(tmp);
			// System.out.println("done:"+cr.getId()+","+cr.getStudents().size());
		 }
	 }
	 public void runAction(){
		 for(int i=0;i<classrooms.size();i++){
			 Thread thread=new Thread(classrooms.get(i));
			 thread.start();
			try {
			//	Thread.currentThread().sleep(5);
				thread.join();
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
			System.out.println("all students of the class are done:"+classrooms.get(i).getName());
		 }
	 }
	
	 public ApplicationContext(){
		 initClassRoom();
		 initStudents();
		 assignStudents();
	}
	  
	 public static void main(String agsp[]){
		 ApplicationContext ac=new ApplicationContext();
		 ac.runAction();
	 }
}
