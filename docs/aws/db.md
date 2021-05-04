# RDS
* Managed Service - backup, patching managed by AWs
* Shared responsibility model : https://cloudacademy.com/blog/aws-shared-responsibility-model-security/
* options :
   * MySQL
   * Maria DB
   * Postgres
   * Amazon Aurora(mysql fork )
   * Oracle
   * SQL Server

### Instance Size
![RDS Instance size](/images/aws/db/rds_instanceType.PNG?raw=true)
![RDS Instance size](/images/aws/db/rds_instanceSize.PNG?raw=true)
https://aws.amazon.com/ec2/instance-types/

### Multi-AZ
* Run RDS instance in one AZ (as primary) and second instance(as secondary) into another AZ in same region.
* Synchronous replication occurs between primary and secondary.
* The Failover process takes place automatically - AWS will update the DNS records to point to the secondary instance. (usually takes 60-120 seconds)
* Failover process triggers in following scenarios :
  * patching/maintenance activity on primary instance
  * Host Failure of Primary Instance
  * AZ failure of primary DB
  * Primary Instance is rebooted with failover
  * DB instance class is modified in primary (eg. standard to memory optimized)
  
## Scaling

### Storage AutoScaling
* All DB except Amazon Aurora uses EBS. where as Amazon Aurora uses shared cluster storage architecture
* EBS storage :
  * General purpose SSD
    * low latency
    * Cost effective
    * Primary Data : Min 20GB Max 64TB (Max for SQL Server is 16TB)
    
  * Provisioned IOPS SSD Storage
    * Primary Data : Min 20GB Max 64TB (Max for SQL Server is 16TB)
    * IOPS : Min: 8000 | Max: 80000 (SQL Server : 40000) 
  * Magnetic Storage
    * just for backward compatibility - not recommended
* Storage autoscaling can be selected during the storage selection when creting the RDS instance. Also have to provide a Max storage threshold
* For Aurora :
  * shared cluster architecture
  * No storage configuration during db creation
  * managed and scaled automatically by AWS
  
### Compute Scaling
* Vertical Scaling
  * eg. from m4.large to m4.xtralarge
  * can be done immediately or can be scheduled 
* Horizontal scaling:
  * Read replicas :
    * gets created from the DB snapshot (if using multi AZ, snapshot gets created from secondary DB ensuring no performance impact on primary DB)
    * Once created, there is a Async link with the primary DB
    * ReadOnly queries can be redirected to read replicas

### Back up
* Provides automatic back-up
* can configure retention(in days)
* Can configure encryption with KMS
* can create manual back up (snapshot) - those are not bound by the retention period for auto back up.


# Dynamo DB
* No-SQL DB(key Value Store) - managed by AWS
* Schema less
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
   * global indexes - let you query across the entire table to find any record that matches a particular value
   * local secondary indexes can only help find data within a single partition key.
   
### IOPs
* Provisioned Mode :
  * allows you to provision set read and writes allowed against your database per second by your application and is measured in capacity units, RCUs for reads and WCUs for writes.
  * Can be used when you can predict the reads and writes per second
* On-Demand Mode :
  * does not provision any RCUs or WCUs, instead they are scaled on demand
  * not as cost effective as provisioned
  * used if you do not know how much workload

Image ref: dynamo_db_iops_ondemand.PNG
  
### Advantages :
* fully managed
* schema less
* Highly available - Replicated across 3 AZs automatically and routing to new AZ (in case of disaster) is managed by AWS
* Fast - no matter what the size of the table is

### disadvantages
* Eventual consistency with the replicas - may have to tweak some config to achive strong cosistency
* Less flexible queries compared to SQL
* Workflow Limitation - Max record size : 400 KB | max indexes per table : 20 global, 5 Secondary
* Provisioned throughput

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
* For write requested made by the client, the data is first written to DynamoDB before it is written to the cache of the DAX cluster.
* process related to table operations(like create table, delete, other DDLs) are passed through directly to DynamoDB

# Amazon ElastiCache
Options:
* Redis
* MemcacheD

# Amazon Neptune
* Its Graph DB
* UseCase :
   * Social networking Platform
   * Fraud Detection
   * recommendation engines
* Query Languages :
  1. Apache Tinkerpop Gremlin
  2. W3C SPARQL
  
### Cluster
* Single or multiple neptune DB instances across multiple AZs
* Each instance is attached to a cluster volume. Each Cluster volume have multiple SSDs and can scale automatically upto 64 TB
* **Storage Auto-Repair** -  

### Endpoint
1. Cluster Endpoint - Read / Write
2. Reader Endpoint
  * For read Replicas
  * Single reader Endpoint - even if multiple read replica exists
  * connection served in round-robin basis
  * endpoint does not load balance 
3. Instance Endpoint
  * each instance in the cluster have unique instance endpoint
  
# Amazon Aurora
* provides superior mysql and postgreSQL engine complaint service
* Separates compute layer to storage layer. (EC2 for compute behind the scene)
* shared storage volume (single logicall volume shared across all compute instances)
* Aurora stores data in 10 GB blocks, with each block being replicated six times across three AZs - two within each availability zone.
* Aurora uses a quorum and gossip protocol baked within the storage layer to ensure that the data remains consistent.  If a storage node goes offline intermittently - when it comes back online it will receive all data modifications from its peers via the gossip protocol.
* Aurora in general, and regardless of the compute layer setup - always provides 6 way replicated storage across 3 availability zones. Because of Aurora's storage layer design, Aurora is only supported in regions that have 3 or more availability zones.
* Aurora provides both automatic and manual failover of the master either of which takes approximately 30 seconds to complete
* In case of failover, Aurora will either launch a replacement master or promote an existing read replica to the role of master
* Endpoints :
  * Cluster Endpoint:  points to the current master database instance. - for reads and writes
  * Reader Endpoint: load balances connections across the read replica fleet within the cluster
  * Custom Endpoint: load balancer's connections across a set of cluster instances that you choose and register within the custom endpoint(you may have a requirement to generate month end reports - therefore you connect to a custom endpoint that has been specifically set up for this task)
  * nstance Endpoint: An instance endpoint maps directly to a cluster instance. Each and every cluster instance has its own instance endpoint
* Connection endpoint load balancing is implemented internally using Route 53 DNS - therefore be careful in the client layer not to cache the connection endpoint lookups longer than their specified TTLs.

#### Aurora Single Master multi read replica
* can support upto 15 read replica
* replication is async
* shared same underlying storage
* can be deployed different AZ
* can be launched a cross-region replica
* Each read replica can be tagged with a label indicating priority in terms of which one gets promoted to the role of master in the event of the master going down. The master can be rebooted in 60 or less seconds.
* cluster can be stopped entirely. when stopped:
  * all underlying compute instances are stopped
  * cluster can remain shut till 7 days, autometically restarts itself
* Backups:
  * Daily backups are automatically performed with a default retention period of 1 day. can be configured for maximum retention period of upto 35 days
  * can specifying a window of time in which backups are automatically taken
  * n-demand manual snapshots can be performed on the database at any time

#### Aurora Multi Master
![security_group](/images/aws/db/aurora-multi-master.PNG?raw=true)
* multi master setup leverages 2 compute instances configured in active-active read write configuration
* active-active pair of compute instances, each instance being deployed into different availability zones.
* all database writes can be redirected to the remaining active instance - all without the need to perform a failover.
* A maximum of 2 compute instances can be configured currently as masters in a multi master cluste
* can not add read replicas to a multi master cluster
* balancing connection logic must be implemented and performed within the client

#### Aurora Serverless
* elastic solution that autoscales the compute layer based on application demand
*  only bills you when it's in use
* suited for applications which exhibit variable workloads and/or have infrequent data accessing and modification needs
* no need allocate instance sizes. configure lower and upper limits for capacity
* Capacity is measured in ACUs - which stands for Aurora Capacity Units. You have the ability to set upper and lower limits for capacity
* uses same fault-tolerant, self-healing storage layer
* scales up or down based on traffic. shut down compute entirely when no demand(**you pay for the storage only**)
* no option or need to configure read-replica
* Single endpoint - for both read and write
* Web Service Data API feature - available only on Aurora Serverless databases.
  * run queries against the database without the need for a JDBC driver nor connection
* Backup :
  * Aurora Serverless performs a continuous automatic backup of the database with a default retention period of 1 day - which can be manually increased to a maximum retention period of 35 days. This style of backup gives you the capability of restoring to a point in time within the currently configured backup retention period. Restores are performed to a new serverless database cluster.
