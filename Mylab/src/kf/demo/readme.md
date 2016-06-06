KF

2 Brocker
5 topics : by Mebmer Action, {"P1","P2","P3","P4","P5"}
3 patitions / topic: hash by the Member id
3 consumer groups: Member Tier {1,2,3}



2 Producers: clientA,clientB


5 Consumer




bin\windows\zookeeper-server-start.bat config\zookeeper.properties
bin\windows\kafka-server-start.bat config\server.properties
bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
bin\windows\kafka-topics.bat --list --zookeeper localhost:2181
bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic test
bin\windows\kafka-console-consumer.bat --zookeeper localhost:2181 --topic test --from-beginning



auto.create.topics.enable   =true
when the 1st, I produce the data to the new topic, the server will report an WARN: Error while fetching metadata with correlation id 0 : {w3=LEADER_NOT_AVAILABLE} (org.apache.kafka.clients.NetworkClient)


Tips
The	high-level	consumer	API	is	used	when	only	data	is	needed	and	the	handling
of message	offsets	is	not	required

Kafka	topics	are	divided	into	a	set	of
ordered	partitions	and	each	partition	is	consumed	by	one	consumer	only

	developers	need	to	put	in	extra	effort	to	gain
low-level	control	within	consumers	by	keeping	track	of	offsets,	figuring	out	the	lead
broker	for	the	topic	and	partition,	handling	lead	broker	changes,	and	so	on.

Questions:
When a new thread for consumer is started, what's the strategy for the re-balance



