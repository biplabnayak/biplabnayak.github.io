## Introduction :

A java client for redis master-slave replication cluster in sentinel mode. This POS has been tested with Redis 3.2 on windows.


## How to run :

### Assumption
Cluster of two redis nodes (One master One slave)
[ Replace below IP in configuration files with your actual IP]


     172.17.192.194 (master)
     172.17.192.191 (slave)
     
### Steps :

* Copy `config/master` directory to master node of redis.
* Copy `config/slave` directory to master node of redis.
* Run below commands in master :


     redis-server master/redis.conf
     redis-server master/sentinel.conf --sentinel
     

* Run below commands in slave :


     redis-server slave/redis.conf
     redis-server slave/sentinel.conf --sentinel


* Run `RedisClusterApplicationTests` from JUnit/Maven (replace the IPs from Config.java with actual IPs).


     mvn test


## References :

* http://redis.io/topics/sentinel
* https://seanmcgary.com/posts/how-to-build-a-fault-tolerant-redis-cluster-with-sentinel
