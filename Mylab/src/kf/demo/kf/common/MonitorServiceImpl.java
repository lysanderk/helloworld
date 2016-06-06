package kf.common;

import java.util.Arrays;
import java.util.Collections;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

public class MonitorServiceImpl  {
	
	final KafkaConsumer   consumerA=	InstanceFactory.initSimplerConsumer("consumerA");
 	final KafkaConsumer   consumerB=	InstanceFactory.initSimplerConsumer("consumerB");
	public void consumer(){
		//  consumerA.subscribe(Arrays.asList(C.MEMBER_ACTION_LIST));
		//consumerA.subscribe(Collections.singletonList("P1"));
		
		System.out.println(consumerA.listTopics().entrySet().toArray()[0]);
		
		System.out.println("topic size"+consumerA.listTopics().entrySet().size());
		//PartitionInfo pf=(PartitionInfo)consumerA.partitionsFor("P1").get(0) ;
		/*new Thread(){
			public void run(){
				while(true){
		
				    ConsumerRecords<Integer, String> records = consumerA.poll(1000);
					   System.out.println("looping start:"+records.count());
				    
				    for (ConsumerRecord<Integer, String> record : records) {
			            System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
			        }
				    System.out.println("looping end");
				    
				}
				
			}
			
		}.start();*/
	}

}
