package kf.examples;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import kafka.api.FetchRequestBuilder;
import kafka.consumer.KafkaStream;
import kafka.examples.KafkaProperties;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.javaapi.FetchResponse;
import kafka.message.MessageAndOffset;

public class ConsumerTest   {

	public static void testProducer() throws InterruptedException, ExecutionException{
	    Properties props = new Properties();
        props.put("bootstrap.servers", "JKQSH-L0675.paicdom.local:9092");
        props.put("client.id", "DemoProducer");
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer  producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<>(KafkaProperties.TOPIC,null,"imvalue2")).get();
          System.out.println("producer");
	}
	
	public static void testConsumer(){
		  Properties props = new Properties();
   	   	props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "JKQSH-L0675.paicdom.local:9092");
           props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoConsumer");
           props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
           props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
           props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
           props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
           props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

          KafkaConsumer consumer = new KafkaConsumer<>(props);
          consumer.subscribe(Collections.singletonList(KafkaProperties.TOPIC));
          
          ConsumerRecords<Integer, String> records =consumer.poll(5000);
          System.out.println("records size:"+records.count());
          for (ConsumerRecord<Integer, String> record : records) {
              System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
          }
	}
	public static void testSimpleConsumer() throws UnsupportedEncodingException{
		  SimpleConsumer simpleConsumer = new SimpleConsumer(KafkaProperties.KAFKA_SERVER_URL,
		            KafkaProperties.KAFKA_SERVER_PORT,
		            KafkaProperties.CONNECTION_TIMEOUT,
		            KafkaProperties.KAFKA_PRODUCER_BUFFER_SIZE,
		            KafkaProperties.CLIENT_ID);

		        FetchRequest req = new FetchRequestBuilder()
		            .clientId(KafkaProperties.CLIENT_ID)
		            .addFetch(KafkaProperties.TOPIC, 0, 0L, 10000)
		            .build();
		        FetchResponse fetchResponse = simpleConsumer.fetch(req);
		        for (MessageAndOffset messageAndOffset : fetchResponse.messageSet(KafkaProperties.TOPIC,0)) {
		            ByteBuffer payload = messageAndOffset.message().payload();
		            byte[] bytes = new byte[payload.limit()];
		            payload.get(bytes);
		            System.out.println(new String(bytes, "UTF-8"));
		        }
	}
    
    public static void main(String asg[]) throws InterruptedException, ExecutionException, UnsupportedEncodingException{
    	//testProducer();
    	testSimpleConsumer();
    	 
    }
}