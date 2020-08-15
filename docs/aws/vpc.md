# Virtual Private Cloud



# Subnets


# Network Access Control List (NACL)
*A network access control list (ACL) is an optional layer of security that acts as a firewall for controlling traffic in and out of a subnet. The network access control list is a numbered list of rules, evaluated in order, to determine whether traffic is allowed in or out of any associated subnet. NACLs are stateless. NACLs cannot tell if a message is a response to a request that started inside the VPC, or if it is a request that started from the internet. Hence, a NACL is better suited for a private subnet than a public one. (For public subnets, using security groups is recommended without NACLs.)
*The VPC comes with a modifiable default network ACL and each subnet must be associated with a network ACL. If you do not explicitly associate a subnet with a network ACL, the subnet is automatically associated with the default network ACL that allows all inbound and outbound traffic:  
* A Subnet can be associated with only one NACL
* A network ACL is a numbered list of rules that are evaluated in order, starting with the lowest numbered rule, to determine whether traffic is allowed in or out of any subnet associated with the network ACL. As a best practice the Lab will start by creating rules with rule numbers that are multiples of 100. This can help with organization if you need to insert new rules later on, as there is room within the numbering scheme.
* When you add or remove rules from a network ACL, the changes are automatically applied to the subnets it is associated with. Note that NACLs may take a bit longer to propagate, as opposed to security groups, which take effect almost immediately (1-2 seconds)
* If troubleshooting efforts are required, sometimes adding an Inbound and Outbound Rule allowing ICMP from anywhere can help while issues are resolved. **(The ping utility requires ICMP)** 


# Security Group


# NAT Gateway

Instances that you launch into a private subnet in a virtual private cloud (VPC) can't communicate with the Internet. You can optionally use a network address translation (NAT) instance in a public subnet in your VPC, to enable instances in the private subnet to initiate outbound traffic to the Internet, but prevent the instances from receiving inbound traffic initiated by someone on the Internet. In this Lab Step you will use an Amazon NAT AMI, which is configured out of the box for IPv4 forwarding, iptables IP masquerading, and with ICMP redirects disabled. 

The following is a very common scenario for using NAT: You want maximum security on the production database servers, so you put them in a private subnet (no route to the internet). Yet you need the ability to perform operating system updates on the database servers.  They cannot connect to the internet, so they cannot receive needed updates.  By adding a default route to a NAT, the database servers can connect to the internet and receive updates. However, they are still secure because no connection originating from the internet can reach them.

Recall that earlier when creating the PrivateRouteTable, you had not yet launched a NAT instance, so you temporarily put the internet gateway as a target in the route table?  Once the NAT instance is up and running, you can put the NAT instance in where it belongs. (Note: The reason launching the NAT instance was delayed until now was to highlight it's importance when private instances attempt to reach the internet. For example, when performing operating system updates.)

# Bastion Host


# Elastic IP Address
When launching an EC2 instance, you can select the subnet that it will reside in, and if you want EC2 to auto-assign a Public IP address. If you select Enable, then your instance will be launched with one of AWS' public IP addresses from their pool of available public addresses.

If this auto-assign option is selected, then that public IP address will remain with that instance until it is stopped or terminated, at which point it will be removed from your instance. However, there will be times when you need a persistent IPv4 public IP address that you need to have associated with your instance, which is exactly what an Elastic IP Address provides.

When you create a persistent elastic IP address, the IP address is associated with your account rather than an instance. This means you can attach an EIP address to an instance or an Elastic Network Interface, an ENI, and even if you stop the instance its associated with, the same EIP will remain in place. You can also detach the EIP from an instance and re-attach it to another instance. However, do bear in mind that when you detach an EIP and it's not associated with a running instance, then you will incur a cost for it. If you no longer need the EIP, you must detach it from the associated instance and release it back to AWS.

If you associate an EIP to an instance that already has a pooled public IP address, then that pooled public address will be released and put back into the pool, and your instance will take on a new EIP address. 
you can't convert an existing pooled public IP address to an EIP.

# Elastic Network Interface(ENI)
* EC2 instance by default comes with a primary network interface - **Labeled as Eth0**. that can not be removed or detached.
* there will be occasions where you will need your instances to have multiple network interfaces. For example, if you wanted to create a management network, and in this instance, you can create and use an ENI to attach to your instance in addition to its primary interface of Eth0. This second interface can then be configured with a private IP address to handle any management traffic from within a different subnet.
* quantity of interfaces is dependent on the EC2 instance type - To check how many interfaces can be attached to your instance -> https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-eni.html#AvailableIpPerENI
* The secondary Network interface can also be attached to a instance running in a different subnet.

# Enhanced Network Adapter
* If you are looking to enable enhanced networking features to reach speeds of up to 100 Gbps for your Linux compute instances, then you can do so using an ENA. However, ENAs are only supported on a limited number of instances
* More details here : https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/enhanced-networking.html#enabling_enhanced_networking
*  it is offered at **no extra cost.**
* Enhanced networking is enabled when the ena module is installed on your instance and that the enaSupport attribute is set. If you wanted to confirm that the ena module is installed on your instance then you can run modinfo ena from the terminal prompt.
 or use AWS CLI 
 aws ec2 describe-instances --instance-ids instance_id --query "Reservations[].Instances[].EnaSupport"
 
 # VPC Endpoints
 VPC Endpoints allow you to privately access AWS services using the AWS internal network instead of connecting to such services via the internet using public DNS endpoints. This means that you can connect to the supported services without configuring an Internet Gateway, NAT Gateway, a Virtual Private Network or a Direct Connect connection.
 
  ## Interface Endpoints 
  **NEED to EXPLORE MORE**
  ## Gateway Endpoints
  **NEED to EXPLORE MORE**
  
# AWS Global Accelerator
* Global service - not tied to a specific region
* 