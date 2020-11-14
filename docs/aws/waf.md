# AWS WAF
* prevent website from attackes
* Identify how Cloudfront and ALB responds to web requests
* filter Both HTTP and HTTPS and distinguish malicious request
* Can be integrated with **AWS Cloud front** and **Application Load Balancer**
* Association between Web ACLs and Cloudfront can take upto 15 min
* Supports custom origin - can manage same level of security to web infrastructure outside of AWS
* 403 error is thrown if a request is blocked by WAF - Error page can be customized

### Components
* Conditions
   - Cross Site scripting
   - Geo-match
   - Ip Address - Single or group of IP address - Allow / Block
   - Size constraints - Header, body, URI, query string etc
   - SQL injection
   - String and regex matching
   
* Rules - add one or more  condition to form a rule
  - Regular Rule
  - Rate based rule
* Web ACLs  - Access Control List
  - Put actions on the rules
  - Actions can be - ALLOW, BLOCK, COUNT
  
 ### Advantages
 - AWS WAF is simple to manage
 - WAF is PCI DSS 3.2 certified (2016) - help achive higher level of security compliance
 - AWS Cloud Watch for Monitoring and AWS Lambda for Automation
 
### Monitoring
- WAF Dashboard - View Statistical information on Wen ACLs
- integration with AWS Cloud watch - set metrics for services in one minute interval by default

### Limitation
- 100 conditions of each type, except Regex, which allow only 10 conditions
- 100 rules and 50 Web ACLs per account
- 5 rate based rules per account
- 10,000 requests per second for WAF with ALB

### Pricing
- 3 chargeable elements
  * number of incoming requests - $0.60 per million web requests
  * No. of Web ACLs - $5 Per Web ACLs per month
  * No. of rule in each ACLs - $1 per rule per Web ACLs per month
  
# AWS Firewall Manager
* Help manage WAF across multiple accounts within AWS Organisation
* Can group and protect specific resources together. eg. all resource with a  particular tag
* it automatically protects certain resources that are added to your account
* Pre-requisite
  * ensuring that your AWS Account is a part of an organization with all features activated
  * define which AWS account will act as the Firewall Manager Admin account
  * AWS Config must be enabled
  
### Elements :
####  WAF Rules:
*which are the same rules used within AWS WAF
#### Rule Groups
* allow you to group together one or more WAF rules that will all have the same action applied when the conditions a rule are met. 
* Rule Groups can either contain a Block or Count action. 
* You can only have 10 rules per group, which is a fixed limitation.
#### Firewall Manager Policies
* This policy contains the rule groups that you want to assign to your AWS resources.
* You can only have two rule groups per policy, One customer created rule group, and one AWS Marketplace rule group. Again, this is a fixed limitation.

AWS WAF rules are defined, which are then added to a Rule Group with either a Block or Count action associated, and this rule group is then added to an AWS Firewall Manager Policy, which is then associated to AWS resources. 
Cost - The cost of each policy is $100 per policy, per region, per month.

# AWS Shield service
* AWS Shield has been designed to help protect your infrastructure against DDoS attacks
* there are a number of different types of DDoS takes, for example, a SYN Flood, a DNS Query Flood, a HTTP flood or cache-busting attacks.
* Comes in 2 variations
  *  **AWS Shield Standard** is free to everyone and offers DDoS protection against some of the more common layer three and layer four DDoS attack
  * **AWS Shield Advanced**
    * greater level of protection for DDoS attacks across a wider scope for an additional cost
    * Protection against EC2, CloudFront, Elastic Load Balancing and also Route 53
    * access to a 24-by-seven specialized DDoS response team at AWS, known as DRT
    * Provides an enhanced monitoring capability. Protection against layer three, four and seven DDoS attacks
    * it costs $3,000 per month
    * AWS Shield Advanced is activated via the Management Console using the WAF and Shield Service
    * It's AWS account specific, so you will need to activate the service on each account required. And you must manually define the resources you want to protect once the service is activated. To protect EC2 instances, you must first associate an Elastic IP first. You must then add Rate-based rules providing you with a primary indicator that a DDoS attack is in progress. 
    * you have the opportunity to pre-authorize the AWS DDoS Response team team to update and modify your Web ACLs and Shield configurations during an attack. But if you do not want AWS to have this access, then you can select the Do not grant the DRT access to my account. Access is provided via an IAM role. And it's recommended you configure CloudWatch alarms with alerting via SNS service. By viewing the Global Threat Environment Dashboard it can provide an overview of the top attacks, and the number of attacks across the AWS landscape. 
    
    
  
  