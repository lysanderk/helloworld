package kf.common;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import kafka.examples.KafkaProperties;

import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class LogServiceImpl {
	
	KafkaProducer   producerA=	InstanceFactory.initSimpleProducer("clientA");
 	KafkaProducer   producerB=	InstanceFactory.initSimpleProducer("clientB");
	
	public void send(List<Member> list){
	      	for(Member member:list){
	      			for(String action:member.getActions()){
	      				if(StringUtils.isEmpty(action))
	      					continue;
	      				if(member.getId().hashCode()%2==0){
	      					send_A(member.getId(),member.getTier(),action);
	      				}
	      				else{
	      					send_B(member.getId(),member.getTier(),action);
	      				}
	      			}
	      	}
	}

	private void send_A(String id,String tier,String action){
		  try {
			producerA.send(new ProducerRecord<>(action,id,action+","+id+","+tier+","+new Date())).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	private void send_B(String id,String tier,String action){
		try {
			producerB.send(new ProducerRecord<>(action,id,action+","+id+","+tier+","+new Date())).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public void concurrentSend(List<Member> list){
		
	}

}
