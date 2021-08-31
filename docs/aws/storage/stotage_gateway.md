* Provide a gateway between on-prem DC to AWS storage system(s3, Glacier)
* its a software to be installed on on-prem (as a VM)
* 3 configurations available
  * File gateway
  * Volume gateway
  * Tape gateway
    
### File gateway
* Allow to securely store file into S3(with encryption)
* Also maintain a local cache
* When your file gateway is first configured, you must associate it with your S3 Bucket which the gateway will then present as an NFS v3 or v41 file system to your internal applications. This allows you to view the Bucket as a normal NFS file system, making it easier to mount as a drive in Linux or map a drive to it in Microsoft.

### Volume Gateways

#### Stored volume gateways
* Stored volume gateways are often used as a way to backup your local storage volumes to Amazon S3 whilst ensuring your entire data library also remains locally on premise for very low latency data access. Volumes created and configured within the storage gateway are backed by Amazon S3, and are mounted as iSCSI devices that your applications can then communicate with. During the volume creation, these are mapped to your on premise storage devices, which can either hold existent data or be a new disk. As data is written to these iSCSI devices, the data is actually written to your local storage services such as your own NAS, SAN, or DAS storage solution. However, the storage gateway then asynchronously copies this data to Amazon S3 as EBS snapshots.
* Volumes created can be between one gig and 16 terabytes. And for each storage gateway, up to 32 stored volumes can be created, which can give you a maximum total of 512 terabytes of storage per gateway.
#### Cached volume gateways
*  Cached volume gateways are different to stored volume gateways, in that the primary data storage is actually Amazon S3 rather than your own local storage solution. However, cached volume gateways do utilize your local data storage as a buffer and a cache for recently accessed data to help maintain low latency,
* each volume created can be up to 32 terabytes in size. With support for up to 32 volumes, meaning a total storage capacity of 1024 terabytes per cache volume gateway.
* although all of your primary data used by your applications is stored in S3 across volumes, it is still possible to take incremental backups with these volumes as EBS snapshots. 



















