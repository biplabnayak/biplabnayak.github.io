# AWS CloudTrail
* records and track all API requests in your account
* API requests can be from :
  * SDKs
  * ASW CLI
  * AWS Management console
  * Another AWS Service
  
* API requests captured are recorded as an event
* recorded in cloudTrail logs and stored in S3
* Logs :
  * Caller Identity
  * timestamp
  * Source IP
* New log files every 5 min - stored in S3
* log files can be stored as long as required to review the history of the API
* Can be delivered to CloudWatch Logs for metric monitoring and alerting vai SNS
* **Its a Global Services**
* Supports over 60 AWS services
### Use cases:
* Security Analysis
  * Monitor restricted API calls
  * notification on Threshold breach
* resolve operational issues
  * Filtering mechanism to isolate data
  * Quick RCA
* Can be used as evidence for various compience and governance controls like - ISO, PCI DSS, FedRamp
* Help Achive complience by logging API calls anc changes to resources

https://d1.awsstatic.com/whitepapers/compliance/AWS_Security_at_Scale_Logging_in_AWS_Whitepaper.pdf

### Components
* Trails
* S3
* Logs
* KMS (optional)
* SNS (optional)
* Cloudwatch logs(optional)
* Event selectors
* Tags
* Events
* API Activity Filters

### Process Flow

![Cloud Trail Process Flow](/images/aws/cloudtrail_processflow.png?raw=true)

# AWS Config
Answers the following :
* what resources do we have? 
* What devices are out there within our infrastructure performing function? 
* Do we have resources that are no longer needed? And therefore, can we be saving money by switching them off? 
* What is the status of their current configuration?
* Are there any security vulnerabilities we need to worry about?
* How are our resources linked within the environment?
* What relationships are there and are there any dependencies?If we make a change to one resource, will this affect another?
* What changes have occurred on the resources and by whom? Do we have a history of changes for this resource that shows us how the resource has changed over time?
* Is the infrastructure compliant with specific governance controls?
* how can we check to ensure that this configuration is meeting specific internal and external requirements?
* do we have accurate auditing information that can be passed to external auditors for compliance checks?

AWS Config can :
* Capture resource changes
* Resource Inventory
* Store Config history
* Provide snapshot of config
* notification about changes - SNS
* ASW Cloud Trail integration - who made the change, when and with which APi
* Rules to check compliance
* Security analysis 
* Identity relationships - eg. which EBS volume is associated with a ec2 instance - GUI
* Supported resources : https://docs.aws.amazon.com/config/latest/developerguide/resource-config-reference.html
* **AWS Config is region specific**

### Components of AWS Config
* AWS resources - CREATE, UPDATE, DELETE
* configuration items (CI) - JSON doc having configuration information, relationship info and other metadata. AWS config records CI information every time a resource changes. Used for :
  * Configuration history
  * Config stream
  * Config Snapshot - CIs are used to create a point in time snapshot of all supported resources
* configuration streams - When the CI us created, it is sent to a Configuration Stream (in the form of SNS Topic). Also Stream is sent in following scenarios :
  * configuration history for a resource was delivered to your account
  * when a configuration snapshot was started and delivered to your account
  * when the state of your resource compliance changes against any config rules that have been configured
  * when evaluation begins for rules against resources
  * when AWS Config found to deliver notifications to your account
* configuration history
  * collate and produce a history of changes to a particular resource
  * allows you to see the complete set of changes made to resource over a set period of time
  * Get the History using CLI - **aws configservice get-resource-config-history --resource-type <resource type> --resource-id <>**
  * AWS Config also sends a configuration history file for each resource type to an S3 bucket that is selected during the setup of AWS Config. This configuration file is typically delivered every six hours
* configuration snapshots
  * Uses CI
  * configuration snapshot will take a point in time snapshot on all supported resources configured for that Region. Generates CI for reach resource.
  * can be sent to an S3 bucket. Alternatively, this information can be viewed by the AWS Management Console.
* configuration recorder
  * this component of AWS Config can be seen as the engine of the service
  * responsible for recording all of the changes to the supported resources within your account and generate CI
  * automatically enabled and started when you first configure AWS Config. can stop and then will start again at a later point. When you stop it, AWS Config will no longer track and record changes to your supported of resources.
* Config rules - (more details below)
* resource relationships
* SNS topics
* S3 buckets - The S3 bucket that was selected at the time of configuration, is used to store all that configuration history files that are generated for each resource type, which happens every six hours. Also, any configuration snapshots that are taken are also stored within the same S3 bucket. 
* AWS Config permissions - When setting up AWS Config, you're required to select an IAM role. This role is required to allow AWS Config to obtain encrypt permissions to carry out and perform a number of functions. Eg.
  * AWS Config will need read only access to all the supported resources within your account so it can retrieve data for the configuration items
  * AWS Config uses SNS and S3, both the streams and storage of the configuration history files and snapshots. So AWS Config requires the relevant permission to allow it to send data to these services.

### Config Rules
* AWS Config rules are a great way to help you enforce specific compliance checks and controls across your resources and allows you to adopt an ideal deployment specification for each of your resource types. Each rule is essentially a Lambda function, that when called upon, evaluates the resource and carries out some simple logic to determine the compliance result with the rule.
* AWS Config simply alerts you that there's a violation and it's up to you to take the appropriate action.
* allows you to adopt best practices that you may have internally within your own enterprise
* using config rules for maintaining security checks and configurations
* AWS have a number of predefined rules that fall under the security umbrella that are ready to use. Eg.
  * Rds-storage-encrypted - checks whether storage encryption is activated by your RDS database instances
  * Encrypted-volumes - checks to see if any EBS volumes that are in attached state
  * Root-account-mfa-enabled - checks whether your root account of your AWS account requires multi-factor authentication for console sign-in
  * IAM-user-no-policy-check - checks that none of your IAM users have policies attached. Best practice dictates that permission should be provided by roles or groups.
![Config Rules Process Flow](/images/aws/config_rules.png?raw=true)