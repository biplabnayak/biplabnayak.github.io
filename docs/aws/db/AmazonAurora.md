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
