# Amazon Elastic File Storage (EFS)

* File level storage
* Optimized for low latency access
* Can be attached to multiple EC2 instances ate the same time - very good storage option for applications that scale across multiple instances allowing for parallel access of data
* like the hierarchy structure all are familiar with
* It uses standard operating system APIs, so any application that is designed to work with standard operating system APIs will work with EFS. It supports both NFS versions 4.1 and 4.0
* **replicates data to multiple AZs. makes it more reliable and available.**
* Its a regional service - any application deployments that span across multiple availability zones can all access the same file systems
* Not all regions have this service. the available regions can be found here : https://docs.aws.amazon.com/general/latest/gr/rande.html#elasticfilesystem-region


## Storage Classes

* Standard
* Infrequent Access (IA)

| Parameter | Standard | IA   |
|-----------|----------|------|
|   Access  | Anytime  | Infrequent|
|Performance| Standard Latency | higher Latency|
| Cost Management   | Charges on amount of storage used each month | Cheaper then standard, **but also charges for read and writes** |
| Availability | Yes | Yes |
| Durability | Yes | Yes |

### EFS Life cycle Management
* When enabled, EFS will automatically move data between the Standard storage class and the IA storage class. This process occurs when a file has not been read or written to for a set period of days, which is configurable, and your options for this period range include 14, 30, 60, or 90 days.
* Depending on your selection, EFS will move the data to the IA storage class to save on cost once that period has been met. However, as soon as that same file is accessed again, the timer is reset, and it is moved back to the Standard storage class. Again, if it has not been accessed for a further period, it will then be moved back to IA. Every time a file is accessed, its lifecycle management timer is reset. The only exceptions to data not being moved to the IA storage class is for any files that are below 128K in size and any metadata of your files, which will all remain in the Standard storage class. 
* After 13th Feb 2019, lifecycle feature can be turned off or on 
    
## Efs Performace modes

* General Purpose
* Max IO

| Parameter | General Purpose | Max IO   |
|-----------|----------|------|
|   Throughput  | Standard  | Unlimited|
|   IOPS  | less than 7000/s  | More then 7000/s|
|   Latency  | Low Latency  | High Latency|
When using the General Purpose mode of operations, EFS provides a CloudWatch metric percent I/O limit, which will allow you to view operations per second as a percentage of the top 7,000 limit. This allows you to make the decision to migrate and move to the Max I/O file system, should your operations be reaching that limit. 