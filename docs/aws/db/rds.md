# RDS (Relational Database Service)
* Database managed By AWS
* Databases :
  * PostgreSQL
  * Oracle
  * MySQL
  * MariaDB
  * Microsoft SQL Server
  * Amazon Aurora

### Instance Size
![RDS Instance size](/images/aws/db/rds_instanceType.PNG?raw=true)
![RDS Instance size](/images/aws/db/rds_instanceSize.PNG?raw=true)
https://aws.amazon.com/ec2/instance-types/

### Advantage

* Managed by AWS
* OS Patching
* Continuous Backup and Restore to a specific timestamp
* Monitoring Dashboards
* Read Replicas
* Multi AZ Setup for DR
* Mantainance window for upgrade
* Scaling Capabilities (horizontal and vertical)
* *Can't SSH into DB instance*

#### Read replicas
* Can create up to 5 replicas
* Can be with in same AZ, cross AZ, Cross Region
* Replication is ASYNC
* Replicas can be promoted to its own DB
* Will get different connection String for each replicas and Master

  
#### Multi AZ

* Run RDS instance in one AZ (as primary) and second instance(as secondary) into another AZ in same region.
* Synchronous replication occurs between primary and secondary.
* will get one DNS name to connect to DB
* Automatic fail over to slave if master is down (no manual intervention)
* Increase availability
* The Failover process takes place automatically - AWS will update the DNS records to point to the secondary instance. (usually takes 60-120 seconds)
* Failover process triggers in following scenarios :
  * patching/maintenance activity on primary instance
  * Host Failure of Primary Instance
  * AZ failure of primary DB
  * Primary Instance is rebooted with failover
  * DB instance class is modified in primary (eg. standard to memory optimized)

### RDS Backups

* Automatically Enabled
* Daily full snapshot of DB
* Ability to restore to any point in time
* can configure retention(in days). 7 Days retention (can be increased upto 35 days)
* Can configure encryption with KMS
* can provide a backup window
* DB Snapshots
  * Manually triggered by the users
  * Retention can be as long as you want (no retention period)
* Aurora DB supports Backtrack - allows you to go back in time on the database to recover from an error or incident without having to perform a restore or create another database cluster. allows you to enter a number of hours of how far you would like to backtrack to with a maximum of 72 hours.

### Autoscaling
#### Storage Autoscaling
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

#### Compute Scaling
* Vertical Scaling
  * eg. from m4.large to m4.xtralarge
  * can be done immediately or can be scheduled
* Horizontal scaling:
  * Read replicas :
    * gets created from the DB snapshot (if using multi AZ, snapshot gets created from secondary DB ensuring no performance impact on primary DB)
    * Once created, there is a Async link with the primary DB
    * ReadOnly queries can be redirected to read replicas


### RDS Encryption
* Encryption at rest capability with AWS KMS - AES-256 encryption
* SSL certificate to encrypt data in flight
* To Enforce SSL
  * For PostgreSQL : rds.force_ssl=1 .. in AWS RDS Console(Parameter Group)
  * For MySql : within DB : *GRANT USAGE ON \*.\* TO 'mysqluser'@'%' REQUIRE SSL*
* To connect using SSL :
  * Provide SSL Trust certificate (Can be downloaded from AWS)
  * Provide SSL option when connecting to DB
  
### RDS Security
* Usually deployed within private subnet(not in public one)
* It works by leveraging Security Group (like in EC2)
* IAM policy to manage who can manage AWS RDS
* Traditional username / passowrd to login into DB

