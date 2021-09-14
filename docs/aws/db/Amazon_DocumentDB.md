# Amazon DocumentDB
* a non-relational fully managed servic
* ability to quickly and easily store any JSON-like document data 
* compute and storage can be scaled separately from each other
* As database grows in size, it will automatically increase the storage by 10G each time, up to a maximum of 64TB
* compatible with MongoDB
* Amazon Database Migration Service helps migrate data from self-managed MongoDB to Amazon DocumentDB


### Architecture
![DocumentDB Architecture](/images/aws/db/DocumentDB_Architecture.PNG?raw=true)
* A cluster have one primary instance, and  upto 16 replicas span across different AZ within a region
* A cluster can have single or multiple DB instance
* use shared cluster storage volume. All instances use the same cluster storage volume
* Instances provide the processing power to read and write request
* Primary for read and write
* replica for read only

## Connectivity
* Endpoints (url with port):
  * Cluster endpoint - point to current primary DB instance, if primary instance fail , it will point to new primary instance
  * reader endpoint - connects to read replicas
  * Instance endpoint - unique instance endpoint for each instance
    
### Backup
* automatic scheduled backup
* point in time recovery during the retention period
* backup stored in S3