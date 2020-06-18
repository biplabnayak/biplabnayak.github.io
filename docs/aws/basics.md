### AWS Regions and Availability Zones
For eg. ```ap-south-1a``` -> here 'ap-south-1' is Region and "a" is the availability zone.

Details about regions: https://aws.amazon.com/about-aws/global-infrastructure/

### Terminologies
* IAM (Identity and Access Management)
* AZ (Availability Zone)
* EC2 ()
  * EBS (Elastic Block Storage)
  * ELB (Elastic Load Balancer)
  * ASG (Auto Scaling Group)
  * Target Group
  * Launch Configuration
  * Launch Template
  * Security Group
  * Elastic IP
* AMI (Amazon Machine Image)
* RDS (Relational Database Service)

# EC2
### Launch a new EC2 Instance

### SSH Into EC2

To SSH into EC2 Instance you need to have the .pem file that was created during the launch of the ec2 instance.
You can get public Ip address of the instance from aws console. Please Note: the public ip of the 

Use Command :
```shell script
ssh -i first_ec2.pem ec2-user@13.232.34.6
``` 

You may get below error while connecting to ec2

```shell script
E:\online-cources\aws\ec2>ssh -i first_ec2.pem ec2-user@13.232.34.6
The authenticity of host '13.232.34.6 (13.232.34.6)' can't be established.
ECDSA key fingerprint is SHA256:WBHrT8ZM0I/uHCrAcQKWPYlZ018RkYR3/gCl3ax4Syk.
Are you sure you want to continue connecting (yes/no)? yes
Warning: Permanently added '13.232.34.6' (ECDSA) to the list of known hosts.
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@         WARNING: UNPROTECTED PRIVATE KEY FILE!          @
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
Permissions for 'first_ec2.pem' are too open.
It is required that your private key files are NOT accessible by others.
This private key will be ignored.
Load key "first_ec2.pem": bad permissions
ec2-user@13.232.34.6: Permission denied (publickey,gssapi-keyex,gssapi-with-mic)
``` 
To resolve this, you need to tweak the file permission

* On Windows:

Right Click on pem file -> property -> Security -> Advanced

make sure only the current User have the permission on that file. remove  access of any other users.

* On Linux / Mac

```shell script
chmode 0400 first_ec2.pem
```

After changing the permission, try the ssh command again.
```shell script
E:\online-cources\aws\ec2>ssh -i first_ec2.pem ec2-user@13.232.34.6


       __|  __|_  )
       _|  (     /   Amazon Linux 2 AMI
      ___|\___|___|

https://aws.amazon.com/amazon-linux-2/
No packages needed for security; 6 packages available
Run "sudo yum update" to apply all updates.
```

## Security Group

* Security Group allows you to manage inbound and outbound traffic to and from ec2 instance.
* Acts as a firewall
* Regulate access to port
* Authorize IP Range
* _By default all outbound traffic from ec2 is allowed_
* _By default all inbound traffic are blocked_
* One security group can be attached to multiple instances
* Locked down to a region/VPC combination
* You can also reference one security group other security group
 

![security_group](/images/aws/security_group.PNG?raw=true)

## Private IP / Public IP / Elastic IP

* Each EC@ instance assigned a Public IP & a private IP
* Public IP changes on restart of the ec2 instance
* Private IP can be used to reach the EC2 instance within the VPC
* if you need a fixed public IP, you can use Elastic IPs
* Elastic IP is a IPv4 public IP
* you can attach it to one EC2 instance at a time
* Max 5 elastic IPs per account. can ask aws support to increase
* Using Elastic ip is a poor architectural decision. try avoid elastic IP, use DNS(route 53) instead.
* Acquiring Elastic IPs associated a cost.


## EC2 User Data
* Bootstap the instance with user data script. Script run once during the **first start** of the instance.
* can be used for  :
  - installing updates
  - installing software
  - anything else ..
* may impact  boot time

## EC2 Instance Launch Types

* **On demand instances**
  - Pay for what you use
  - Bill per second, After first minute
  - Highest Cost
  - No upfront payment
  - No long term commitment
  - *Recommended for short term, un-interrupted workload*

* **Reserved Instances**
  - 75 % lesser cost compared to on-demand
  - Pay upfront for what you use with long term commitment
  - Reservation Period - 1 or 3 year
  - Reserve a specific instance
  - *Recommended for steady state usage application. For Eg. Databases*

* **Convertible Reserved Instances**
  - 54% less cost compare to on-demand
  - Can change the Instance type

* **Scheduled Reserved Instances**
  - Launch within the time window you reserve
  - *Idle when you require for a fraction of a day/week/month.*

* **Spot Instances**
  - Up to 90% lesser price compared to on-demand
  - Bid a price and get the instance as long as its under the price
  - Price varies based on demand and offer
  - You can lose an instance at any time with 2 minute notification (when spot price goes above the bid)
  - *Can be used for batch jobs, Big data analytics, workload resilient to failure*
  - Not good for critical apps
    
* **Dedicated Hosts**
  - Book Entire Physical Server
  - Full Control EC2 Instance placement
  - Visibility into underlying socket / physical cores of hardware
  - Allocated for your account for 3 years period reservation
  - *More expensive*
  - Useful for
    *  Software with complicated licencing model
    * Companies having strong regulatory or compliance needs
    
* **Dedicated Instances**
  - Bit more restrictive then Dedicate Hosts
  - No Other customer share your hardware - hardware dedicate dto you
  - May share hardware with other instances in same account
  - No control over the hardware
  - No control over instance placement - can move hardware after Stop / Start
Here is the difference between Dedicate Instances Vs Dedicated Hosts.
More details here : https://aws.amazon.com/ec2/dedicated-hosts
![Dedicate Instance Vs Dedicated Hosts](/images/aws/dedicate_instace.PNG?raw=true)

## EC2 Pricing
* EC2 instance price per hour depends on
  * region
  * instance type
  * Launch Type
  * OS (Linux vs Windows vs other)
* Billed by second, with minimum 60 sec
* pay for other factors like storage, Data Transfer, Fixed IP, Public Addresses, Load balancing etc
* **You donot pay for the instance if instance is stopped**

## EC2 AMI(Amazon Machine Image)
* Base Image such as 
  * Ubuntu
  * Fedora
  * RedHat
  * Windows
  * Amazon Linux
* image can be customized at runtime using EC2 User Data
* Custom AMI (Creating your own image)
  * can be based on Linux or Windows
  * Need :
    * Pre-Installed package needed
    * faster boot time (no need for ec2 user data at boot time)
    * Machine comes with monitoring / Enterprise software
    * Security concern - control over the machine in the network
    * Someone else AMI optimized for running DB, APP etc..
  * **AMI are built for a specific AWS Region**
  
## EC2 Instance Overview
5 distinct characteristics
* RAM (Type, Amount, Generation)
* CPU (type, Make, frequency, generation, number of core)
* I/O (Disk performance, EBS Optimization)
* Instance type : More details : https://aws.amazon.com/ec2/instance-types/ , https://www.ec2instances.info/