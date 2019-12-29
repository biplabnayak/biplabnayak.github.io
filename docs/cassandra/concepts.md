### Key Cassandra Concepts

#### Apache Cassandra Datacenter
In Apache Cassandra (and respectively DataStax Enterprise products) a datacenter and rack do not directly correlate to a physical rack or datacenter.

An Apache Cassandra Datacenter is a group of nodes, related and configured within a cluster for replication purposes. Setting up a specific set of related nodes into a datacenter helps to reduce latency, prevent transactions from impact by other workloads, and related effects. The replication factor can also be setup to write to multiple datacenter, providing additional flexibility in architectural design and organization.

#### Apache Cassandra Racks

An Apache Cassandra Rack is a grouped set of servers. The architecture of Cassandra uses racks so that no replica is stored redundantly inside a singular rack, ensuring that replicas are spread around through different racks in case one rack goes down. Within a datacenter there could be multiple racks with multiple servers, as the hierarchy shown above would dictate.

To determine where data goes within a rack or sets of racks Apache Cassandra uses what is referred to as a snitch. A snitch determines which racks and datacenter a particular node belongs to, and by respect of that, determines where the replicas of data will end up.

#### Nodes and Virtual nodes

#### Snitch
Component of cassandra uses gossip protocol to learn about the the network, racks, data-center, IP, health etc.
* SimpleSnitch
* GossipingPropertyFileSnitch
* PropertyFileSnitch
* Ec2Snitch

### Terminology
![Cassandra Terminology](/images/cassandra/cassandra_terminology.png?raw=true)

#### Keyspace
Analogous to a tablespace in Oracle.

#### Table

#### Partition

#### Rows