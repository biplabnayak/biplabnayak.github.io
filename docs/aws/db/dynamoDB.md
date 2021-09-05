
# Dynamo DB
* No-SQL DB(key Value Store) - managed by AWS
* Schema less
* writes are eventually consistent. reads can be strongly consistent or eventually consistent
* Configuration options :
    * Table Name
    * Primary Key (Partition Key)
    * Sort key - allows to sort within a partition
    * Secondary indexes (Optional)
    * IOPs
    * Encryption

### Secondary index
* each query can only use one index. If you want to query and match on two different columns, you need to create an index that can do that properly.
* when you write your queries, you need to specify exactly which index should be used for each query. It's not like a relational database that has a query analyzer, which can decide which indexes to use for our query.
* DynamoDB has two different kinds of secondary indexes
#### global indexes
* let you query across the entire table to find any record that matches a particular value
* have to configure provisioned throughput for each Global secondary index
* has separate storage then main table. has its own partition key.
* it by default include all the attributes of main table which uses more storage and throughput. but if you want few attributes of the main table, you can project the attributes to the index
* max 20 GSI can be added per table
#### local secondary indexes
* can only help find data within a single partition key
* must be created when the table is created
* max 5 LSI can be added per table
* shares the throughput of the main table

### IOPs
* Provisioned Mode :
    * allows you to provision set read and writes allowed against your database per second by your application and is measured in capacity units, RCUs for reads and WCUs for writes.
    * Can be used when you can predict the reads and writes per second
* On-Demand Mode :
    * does not provision any RCUs or WCUs, instead they are scaled on demand
    * not as cost effective as provisioned
    * used if you do not know how much workload
#### Read and Write Capacity units (RCU / WCU)
* 1 RCU = 1 item upto 4KB with String consistency
* Large records cost 1 RCU per 4KB
* Eventual consistent reads costs half

* 1 WCU = 1 item upto 1KB each seconds
  Image ref: DynamoDBIOPSAutoscaling.PNG

### Queries and Scans
#### Query
* Search the table with single partition key
* can filter using sort keys (=, <, >, begins with)
* can be filtered on any attribute
* consumes less read capacity units as fetch by partition kay and sort / filter by sort key or other attribute
#### Scans
* searches across all partition keys
* filtered by any attribute
* can not be ordered
* eventual consistent
* can run in parallel from multiple thread or server  for example : Amazon elastic map reduce can do a parallel scan on dynamoDB
* consumes more read capacity units(RCU) as it scans all the records of the table
### Partitioning
* As table grows , table gets splits into multiple partitions. Partitions are not visible on console or API.
* Number of partition depends on size of the table, provisioned IOPs
* IOPs get evenly distributed between each partitions. so we need to dicede on a good partition key that can get the data distributed evenly across partitions
### Advantages :
* fully managed
* schema less
* Highly available - Replicated across 3 AZs automatically and routing to new AZ (in case of disaster) is managed by AWS
* Fast - no matter what the size of the table is

### disadvantages
* Eventual consistency with the replicas - may have to tweak some config to achive strong cosistency
* Less flexible queries compared to SQL
* Workflow Limitation - Max record size : 400 KB | max indexes per table : 20 global, 5 Secondary
* Provisioned throughput - if exceed will get ProvisionedThroughputExceededException
* no date datatype

### HA Configurations
* designed to autometically partition data and incoming traffic across multiple partitions
* partitions are stored in numerous backend servers distributed across three AZ in same region
* **Synchronous** AZ replication
* Dialing up and down the provisioned throughput of a DynamoDB database is possible, and ensures that your DynamoDb database can meet the needs of your application as it grows. Changing the provisioned throughput results in additional partitions and replication which again happens automatically in the background.
* Support cross region replication - called GLOBAL TABLES

#### Global tables
* replicate in one or more region
* increases the availability and data locality
* Users can be served directly from closest located table replica
* replication takes place within one AWS account
* can implement multi-master read/write with eventual consistency
* oth read and writes can be performed against any one of the configured global tables. All writes will then be replicated in near sub second to time to all other globally configured tables of the same table name.
* Existing dynamoDB table can be converted into global table

#### On-Demand backup
* Manually requested (can be requested from console or cli)
* back stays in the account till it is manually deleted by admin

#### Point-in-time recovery
* can be restored to any particular time
* feature is disabled by default - needs to enable
* additional charges will apply
* you can only restore it to a new table
* when point-in-time recovery is enabled, you can see "earliest restore date" and "latest=restore date" in console
* "latest=restore date" is always lags the current time by few minutes

#### DynamoDB Accelerator(DAX)
* DAX is an in-memory cache delivering a significant performance enhancement, up to 10 times as fast as the default DynamoDB settings, allowing response times to decrease from milliseconds to microseconds. It is a fully managed feature offered by AWS and as a result, is also highly available.
* fully compliant with all DynamoDB API calls, and so it seamlessly fits into your existing architecture without any redesign and effort from your developer teams.
* DAX deployment can containing a minimum of 3 nodes, a maximum of 10 nodes, with 1 primary and 9 read replicas
* it can also enable you to reduce your provisioned read capacity within DynamoDB (since data is cached in dax)
* DAX also supports encryption at rest, this ensures that any cached data is encrypted integration of the Key Management Service (KMS)
* DAX is a separate entity to DynamoDB. architecturally it sits outside of DynamoDB
* During the creation of your DAX Cluster, you will be asked to select a subnet group, this is simply a grouping of subnets across different availability zones. This is to allow DAX to deploy a node in each of the subnets of the subnet group, with one of those nodes being the primary and the remaining nodes will act as read replicas.
* To allow your EC2 instances to interact with DAX you will need to install a DAX Client on those EC2 instances. This client then intercepts with and directs all DynamoDB API calls made from your client to your new DAX cluster endpoint, where the incoming request is then load balanced and distributed across all of the nodes in the cluster.
* To allow your EC2 instances to communicate with your DAX Cluster you must ensure that the security group associated with your DAX Cluster is open to TCP port 8111 on the inbound rule set.
* For read request : -> DAX -> DynamoDb(if cache miss)
* For write requested made by the client, the data is  first written to DynamoDB before it is written to the cache of the DAX cluster.
* process related to table operations(like create table, delete, other DDLs) are passed through directly to DynamoDB
