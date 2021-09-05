
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
* **Storage Auto-Repair** - Storage Auto-Repair will automatically find and detect any segment failures that are present in the SSDs that make up the shared volume, and then automatically repair that segment using the data from the other volumes in the cluster.
*
### Replication
* If replicas are used, then each Neptune cluster will contain a primary database instance, which will be responsible for any read and write operations. The Neptune replicas, however, are used to scale your read operations, and so support read-only operations to the same cluster volume that the primary database instance connects to.
* maximum limit of 15 replicas per crust exists which can span multiple availability zones.
* Ensures that should have failure occur in the availability zone hosting the primary database one of the Neptune read replicas in a different AZ will be promoted to the primary database instance, and adopt both read and write operations. This process usually takes about 30 seconds
* Data is synchronized between the primary database instance and each replica synchronously. And in addition to providing a failover to your primary database instance, they offer support to read only queries.
### Endpoint
1. Cluster Endpoint - Read / Write - when failover happens, there would be no change in the cluster endpoint
2. Reader Endpoint
* For read Replicas
* Single reader Endpoint - even if multiple read replica exists
* connection served in round-robin basis
* endpoint does not load balance
3. Instance Endpoint
* each instance in the cluster have unique instance endpoint