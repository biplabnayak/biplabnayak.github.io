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
 
 ### How to create auto-scaling group
 