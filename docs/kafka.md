# Running kafka on windows
* down load Kafka binaries from https://kafka.apache.org/downloads
* unzip the tar file.
* edit config/zookeeper.properties
```jshelllanguage
dataDir=E:/softwares/kafka_2.12-2.3.1/data/zookeeper
``` 
* edit config/server.properties
```jshelllanguage
log.dirs=E:/softwares/kafka_2.12-2.3.1/data/kafka
```
* create a batch file `run.bat`
```
start E:\softwares\kafka_2.12-2.3.1\bin\windows\zookeeper-server-start.bat E:\softwares\kafka_2.12-2.3.1\config\zookeeper.properties

ping 127.0.0.1 -n 10 > nul

start E:\softwares\kafka_2.12-2.3.1\bin\windows\kafka-server-start.bat E:\softwares\kafka_2.12-2.3.1\config\server.properties 
```
* `ping 127.0.0.1 -n 10 > nul` in the script is for waiting 10 seconds for zookeeper to start before starting kafka.


# other useful commands

* Test the Zookeeper and Kafka broker registration to the Zookeeper server.
```jshelllanguage

C:\Users\Admin>E:\softwares\kafka_2.12-2.3.1\bin\windows\zookeeper-shell.bat localhost:2181 ls /brokers/ids
Connecting to localhost:2181

WATCHER::

WatchedEvent state:SyncConnected type:None path:null
[0] 
```

* create a new Kafka topic
```jshelllanguage
C:\Users\Admin>E:\softwares\kafka_2.12-2.3.1\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
```

* List the topic
```jshelllanguage
E:\softwares\kafka_2.12-2.3.1\bin\windows\kafka-topics.bat --list --zookeeper localhost:2181
```