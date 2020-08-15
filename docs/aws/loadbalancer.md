## EC2 Load Balancer (ELB)

### Why Loadbancer
* spread traffic acress multiple downstream
* Expose single point of access (DNS) to your application
* handle downstream failure
* Health check
* SSL termination
* Enforce stickiness with c0okies
* High Availability across Zones

### why ELB
* Managed by AWS
* AWS guarantees it will be working
* Upgrade, Maintenance and Availability taken care by AWS
* Provides a few configuration knobs
* Const would be lesser compared to you own loadbalancer

There are 3 types of loadbalancers
* Classic Loadbalancer (v1 - old gen -2009)
* Application Loadbalancer(v2 - new Gen - 2016) ***Recomended**
* Network Loadbalancer(v2 - new gen - 2017)  ***Recomended**

### ELB Components
* Listeners - The listener defines how your inbound connections are routed to your target groups based on ports and protocols set as conditions.
* Target Groups - A target group is simply a group of resources that you want your ELB to route requests to, for example a fleet of EC2 instances. You can configure your ELB with a number of different target groups, each associated with a different listener configuration and associated rules. This enables you to route traffic to different resources based upon the type of request.
* Rules - The listener defines how your inbound connections are routed to your target groups based on ports and protocols set as conditions.
* Health Checks -
* Internal / Internet facing
* ELB Node - During the creation process of your ELBs, you're required to define which Availability Zone you'd like your ELB to operate within. For each Available Zone selected, an ELB node will be placed within that Availability Zone. As a result, you need to ensure that you have an ELB node associated to any Availability Zones for which you want to route traffic to. Without the Availability Zone associated, the ELB will not be able to route traffic to any targets within that Availability Zone even if they are defined within the target group. This is because the nodes are used by the ELB to distribute traffic to your target group
* Cross-Zone Load balancing -

### SSL Certificate

When you select HTTPS as your listener, you will be asked to select a certificate using one of four different options available. 
* Either choose a certificate from ACM, 
* upload a certificate to ACM, 
* choose a certificate from IAM
* upload a certificate to IAM. 

The first two options relate to ACM. An ACM is the AWS Certificate Manager and this service allows you to create and provision SSL/TLS server certificates to be used within your AWS environment across different services. This integration with ACM simplifies the configuration process of implementing a new certificate for your elastic load balancer and as a result, it's the preferred option. 
https://docs.aws.amazon.com/acm/latest/userguide/acm-overview.html

### Application Load balancer - V2 (ALB)
* Act on layer 7
* loadbalancing multiple HTTP applications across machines (target group)
* Has port mapping feature to redirect to dynamic port
* In comparision, we need to create one Classic loadbancer per application before, which was expensive and ineficient. So, Classic loadbalancers are deprecated.
* Stickiness can be enabled at target group level.
* ALB supports HTTP, HTTPS, websocket protocols
* Application server don't see the ip of the client directly, True Ip is inserted in the header **X-Forwarded-For**
 * can route based on host name and path
 * Great fit for ECS (container service)
* 4xx error codes - Client introduced errors
* 5xx error codes - Application Introduced error
* 503 - Nocapacity or No registered Target
 ![Application Loadbalancer](/images/aws/loadbalancer.PNG?raw=true)
 
### Network Loadbalancer
* Acts on Layer 4
* Forward TCP Traffic
* high throughput (millions requests per second)
* support for static IP or Elastic IP
* less latency(~100ms)
* Should be used for extream performance and should **not** be a default load balancer
* directly see the Client IP
* You must enable Cross-Zone Load Balancing to achieve the highest level of availability. Without enabling this feature, clients could cache the DNS address of the load balancer node in one availability zone and that node would only distribute requests to instances within the availability zone. Cross-Zone Load Balancing allows every load balancer node to distribute requests across all availability zones, although for the Network Load Balancer there are data transfer charges when this feature is enabled. (There are no data charges for other types of load balancers)
---------------------- 
* All loadbalancer have a static hostname. ** Donot resolve the hostname and use underlying IP).
* All load balancers can scale, but not instantaneously. Contact AWS for Warm-Up
* If loadbalancer can no connect to your application, check your security group
 
 // TODO - Add Images
 
 # Auto Scaling Group
 * Scale out(Spin up EC2) to match increased load
 * Scale in(Remove EC2) to match decreased load
 * Ensures Min and Max instance running
 * Automatically register new instance to load balancer
 
 // TODO: Ad more notes
 
 ### Launch configurations
 
 Your group uses a launch configuration as a template for its EC2 instances. When you create a launch configuration, you can specify information such as the AMI ID, instance type, key pair, security groups, and block device mapping for your instances. 
 
 ### Launch template
 
 A launch template is similar to a launch configuration, in that it specifies instance configuration information. ... However, defining a launch template instead of a launch configuration allows you to have multiple versions of a template. With versioning, you can create a subset of the full set of parameters and then reuse it to create other templates or template versions.
 
 
 ### How to create auto-scaling group
 
 
 
 
 # Focus:
 * ELB Connection Draining
 *  suspended the Auto Scaling terminate process
 * Availability Zone Rebalancing process (AZRebalance)
 * Which of the following does the dualstack prefixed DNS return with respect to ELB? - Both IPV4 and IPV6 records
 * A user is configuring a load balancer using HTTPS/SSL. Which of the following should be created to set up authentication for back-end instances? - Public key policy
 * Classic Load balancer supports ssl termination ? Yes
 * Slow start mode for ALB
 * Cross Zone Load balancing
