package kf.common;

public class Main {

	public static void main(String ag[]){
		//InstanceFactory.reset();
		LogServiceImpl log=new LogServiceImpl();
		log.send(BizFactory.initMemberPojo(10));
		
		//MonitorServiceImpl monitor=new MonitorServiceImpl();
		//monitor.consumer();
	}
}
