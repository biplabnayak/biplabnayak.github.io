## Elastic Block Storage
* Can be attached to an EC2 instance. Supports very high IO.
* Independent of EC2 Instance
* Multiple EBS volume can be attached to a single instance
* 1 EBS volume can be attached to only one EC2 instance. (It now supports Provisioned IOPS SSD volumes can be attached to 16 Nitro-based instance within same AZ 
* EBS is Specific to one AZ. it can be attached to EC2 instance on the same AZ



1. General purpose SSD - gp2
2. Provisioned IOPs 1 - io1
3. Provisioned IOPS 2 - io2
4. Cold HDD - sc1
5. Throughput Optimized HDD - st1
6. Magnetic Storage


### General purpose SSD
* Min: 1 GiB, Max: 16384 GiB
* Baseline of 3 IOPS per GiB with a minimum of 100 IOPS, burstable to 3000 IOPS
* minimum of 100 IOPS and a maximum of 16000
* Balanced price and performance
* Recommended for most workloads


### Provisioned IOPs 1 (SSD)) - io1
Min: 4 GiB, Max: 16384 GiB
you can provision up to 50 IOPS per GiB for io1,

### Provisioned IOPs 2 (SSD) - io2
Min: 4 GiB, Max: 16384 GiB
up to 500 IOPS per GiB for io2.

### Cold HDD(SC1)
* Min: 500 GiB, Max: 16384 GiB
* Throughput : 12MB/s per TiB
* Lowest cost HDD volume

### Throughput Optimized HDD (ST1)
* Min: 500 GiB, Max: 16384 GiB
* Throughput : 40 MB/s per TiB

#### Resiliency
* Every write to EBS is replicated multiple time within same AZ
* EBS volume is specific to 1 AZ

#### Backups
* Backup snapshots can be taken manually 
* Configure Cloudwatch event to take snapshot
* snapshots stored in S3
* Snapshots are incremental - Each snapshot will copy data that has changed since previous snapshot
* Can copy snapshot from 1 region to another

### Security
* Supports encryption ( just have to enable encryption)
* Encrypted with AES-256 algorithm along with AWS KMS

### Resizing option
* EBS volume size can be modified as required

—————————————————————————————————————

EC2 Placement Group
———————————
* Cluster
* Spread
* Partition
