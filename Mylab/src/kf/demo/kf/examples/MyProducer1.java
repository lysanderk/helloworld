package kf.examples;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import sun.instrument.InstrumentationImpl;
import kf.common.InstanceFactory;

public class MyProducer1 {

	
	public static void main(String asg[]){
		  Properties props = new Properties();
	        props.put("bootstrap.servers","localhost:9092");
	        props.put("client.id", "producer-MyProducer1");
	        props.put("acks","all");
	        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	KafkaProducer<String, String> producer=	new KafkaProducer<String, String>(props);
	
	for(int i=0;i<100;i++){
		producer.send(new ProducerRecord<String,String>("tp"+i%2,Integer.toString(i)), new Callback(){
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				System.out.println(metadata.topic()+","+metadata.partition()+","+metadata.offset());
			}
		});
	}
	producer.close();
	}
}
