package kf.examples;
import kafka.admin.TopicCommand;
import kafka.examples.KafkaProperties;


public class SimpleCMD {

	
public static void listTopic(){
	String[] options = new String[]{  
		    "--list",  
		    "--zookeeper",  
		    KafkaProperties.KAFKA_SERVER_URL+":2181"  
		};  
		TopicCommand.main(options); 
}
public static void deleteTopic(String host,String port,String topics){
    String[] options = new String[]{  
		    "--delete",  
		    "--zookeeper",  
		    host+":"+port ,
		    "--topic",  
	        topics  
		};  
    TopicCommand.main(options);  

}
public static void createTopic(){
	 String[] options = new String[]{ 
			  "--create",  
			    "--replication-factor", 
			    "1",
			    "--zookeeper",  
			    KafkaProperties.KAFKA_SERVER_URL+":2181" ,
			    "--partitions",  
		        "1",
		        "--topic","testoo"
			};  
	    TopicCommand.main(options);  
	    
}

public static void main(String argsp[]){
	//deleteTopic();
	//listTopic();
	createTopic();
}

}
