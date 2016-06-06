package kf.common;

import java.util.Properties;

import kafka.examples.KafkaProperties;
import kf.examples.SimpleCMD;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

public class InstanceFactory {

	private final static String HOST="JKQSH-L0675.paicdom.local";
	
	public static void reset(){
		SimpleCMD.deleteTopic(HOST,"2181","P1,P2,P3,P4,P5");
	}
	
	
	public static KafkaProducer initSimpleProducer(String clientId){
		  Properties props = new Properties();
	        props.put("bootstrap.servers",HOST+":"+"9092");
	        props.put("client.id", clientId);
	        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	      return  new KafkaProducer<>(props);
	}
	
	public static KafkaConsumer initSimplerConsumer(String groupID){
	       Properties props = new Properties();
	  	   props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST+":"+"9092");
	       props.put(ConsumerConfig.GROUP_ID_CONFIG, "SimpleConsumer");
	       props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
	       props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
	       props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
	       props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
	       props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
	       return new KafkaConsumer<>(props);
	}
	
	
}

