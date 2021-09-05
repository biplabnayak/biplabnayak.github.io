# Amazon ElastiCache
Options:
* Redis
* MemcacheD

## Redis

### Cluster Mode
* When disabled , each cluster will have 1 shard
* when enabled, each cluster can have upto 90 shards

### Components

* Elasticache Nodes : Fixed size chunk of network attached RAM (builingblock of elasticache)
* Redis shards : Group og 9 Elasticcache nodes
* Redis Cluster : can contain 1-90 redis shards. data is partitioned across all the shards in the cluster
* Memchached Cluster : collection of one or more elasticache nodes. Amazon ElastiCache automatically detects and replaces failed nodes,