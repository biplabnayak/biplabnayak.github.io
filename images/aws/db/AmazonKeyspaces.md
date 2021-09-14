# Amazon keyspace
* Compatible with Apache Cassandra
* Fully managed Serverless service
* Unlimited throughput
* keyspaces is the collection of table
* Storage layer is abstracted and managed by aws 
* Encryption at rest is automatically enabled
* In-Transit encryption is managed by TLS connection
* Allows point in time recovery
### Throughput Options:
* On-Demand : 
  * default option, 
  * pricing based on number or read/write per sec. 
  * scales instantaneously to any increased throughput
  * for unpredictable workload
* Provisioned
  * for predictable workload
  * specify number of read / writes per second
  * use automatic scaling if experience fluctuation