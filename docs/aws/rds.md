# RDS (Relational Database Service)
* Database managed By AWS
* Databases :
  * PostgreSQL
  * Oracle
  * MySQL
  * MariaDB
  * Microsoft SQL Server
  * Amazon Aurora
  
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

* In SYNC replication
* will get one DNS name to connect to DB
* Automatic fail over to slave if master is down (no manual intervention)
* Increase availability

### RDS Backups

* Automatically Enabled
* Daily full snapshot of DB
* Ability to restore to any point in time
* 7 Days retention (can be increased upto 35 days)
* DB Snapshots
  * Manually triggered by the users
  * Retention can be as long as you want

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
