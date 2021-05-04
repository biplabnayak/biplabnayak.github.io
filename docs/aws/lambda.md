# AWS Lambda
* Only pay for compute
* charge per 100 ms. only when the code is running.
* STEP 1 - Upload code to Lambda - node.js, Java, C#, Python, Go, Power Shell, Ruby
* STEP 2 - Specific trigger from Event source (eg. S3)
* STEP 3 - Lambda runs the code based on trigger
* Following Elements :
  * Lambda Function - Code
  * Event Sources - 
  * Downstream Resources
  * Log stream -> Cloud Watch
  
# Concepts
###  Function
### Trigger
### Event
### Execution environment
* An execution environment provides a secure and isolated runtime environment for your Lambda function.
* manages the processes and resources that are required to run the function.

### Deployment package
* You deploy your Lambda function code using a deployment package. Lambda supports two types of deployment packages:
  * A .zip file archive that contains your function code and its dependencies.
  *  container image that is compatible with the Open Container Initiative (OCI) specification

### Runtime
The runtime provides a language-specific environment that runs in an execution environment. The runtime relays invocation events, context information, and responses between Lambda and the function. You can use runtimes that Lambda provides, or build your own. If you package your code as a .zip file archive, you must configure your function to use a runtime that matches your programming language. For a container image, you include the runtime when you build the image.

### Layer
* A Lambda layer is a .zip file archive that can contain additional code or other content. A layer can contain libraries, a custom runtime, data, or configuration files.
* Layers provide a convenient way to package libraries and other dependencies that you can use with your Lambda functions
* You can include up to five layers per function. Layers count towards the standard Lambda deployment size quotas.
* By default, the layers that you create are private to your AWS account. You can choose to share a layer with other accounts or to make the layer public. If your functions consume a layer that a different account published, your functions can continue to use the layer version after it has been deleted, or after your permission to access the layer is revoked. However, you cannot create a new function or update functions using a deleted layer version.

### Extension
* Lambda extensions enable you to augment your functions. For example, you can use extensions to integrate your functions with your preferred monitoring, observability, security, and governance tools.

### Concurrency
* Concurrency is the number of requests that your function is serving at any given time. When your function is invoked, Lambda provisions an instance of it to process the event. When the function code finishes running, it can handle another request. If the function is invoked again while a request is still being processed, another instance is provisioned, increasing the function's concurrency.
* Reserved concurrency – Reserved concurrency creates a pool of requests that can only be used by its function, and also prevents its function from using unreserved concurrency.
* Provisioned concurrency – Provisioned concurrency initializes a requested number of execution environments so that they are prepared to respond to your function's invocations.

# Important Links
* Quota : https://docs.aws.amazon.com/lambda/latest/dg/gettingstarted-limits.html
* Concurrency : https://docs.aws.amazon.com/lambda/latest/dg/configuration-concurrency.html
* Error handling : https://docs.aws.amazon.com/lambda/latest/dg/invocation-retries.html
* Versions : https://docs.aws.amazon.com/lambda/latest/dg/configuration-versions.html
### Benefits
* Zero administration
* Easy scale
* High Available and fault tolerance - Multi AZ by default
* Ideal for Microservices
* Low cost - **pay for number of request and duration**

## Invocation Types
* DryRun
* Event
* RequestResponse

https://docs.aws.amazon.com/lambda/latest/dg/API_Invoke.html

### Sync and Async Invocation
* You can control sync / async invocation in a custom application
* Depend on the event source
  ![Sync Vs ASync](/images/aws/lambda/pollVsPush.PNG?raw=true)


### Event sources
* Poll based services - Amazon Kinesis, SQS, Dynamo DB
* push based services - S3, code commit, and all other

https://docs.aws.amazon.com/lambda/latest/dg/API_CreateEventSourceMapping.html

# Monitoring
Following cloudwatch matrices are published by default by lambda
![Cloudwatch matrices](/images/aws/lambda/cloudwatch_matrics.PNG?raw=true)

![Poll Vs Push](/images/aws/lambda/pollVsPush.PNG?raw=true)

### Creating a Lambda Function
* Name
* Runtime(Java, NodeJs etc.)
* Role
* Trigger (S3, Kinesis) - based on trigger, **Function Policies** and **Execution Policies** are assigned
  * Execution Policies - What Resource the function role has access to.
  * Function Policies - Which AWS Resources are allowed to invoke the function
* Function Code - Edit inline, Upload Code, from s3 Bucket
  * Handler - Allow to invoke the handler, entry point to execution
* Environment Variables - (Encryption functionality available)
* Execution role 
* Basic Setting
  * Memory 128 MB - 3008 MB
  * Time Out - how log function should run before it terminates. between 1 Sec to 15 min. Function can not run more than 15 min. So it's not idle for log batch processing 
* Network
  * By default, lambda only allowed accessing resources that are accessible over the internet
  * Allow accessing resources via VPC
  * when VPC is configured, Lambda assigned ENIs(Elastic Network Interface) to your resources using a private IP address. The default ability of accessing publicly available resources over internet is removed.
  * To have internet access, you must attach the function to a private subnet which has access to a NAT instance or a NAT Gateway. **Do not attach it to a public subnet**
  * execution role of Lambda will need to have specific permissions that allow it to operate within a VPC.permissions that are required to configure the required ENIs, such as ec2:CreateNetworkInterface, ec2:DescribeNetworkInterfaces, and also ec2:DeleteNetworkInterfac.
* debugging and error handling
  * The DLQ resource can be either an SNS topic or an SQSQ and are used to receive payloads that were not processed due to a failed execution
  * invocations can either be synchronous or asynchronous, and this is determined by the event source itself.
    * **asynchronous** and a failure occurred, then Lambda would automatically retry the event a further two more times.  If you didn't have a DLQ configured, then the payload and event would just simply be deleted.
    * **synchronous** invocations do not automatically retry failed attempts like asynchronous ones do.
  * enable active tracing - it integrates AWS X-Ray to trace the event sources that invoked your Lambda function.
* Concurrency is based upon scaling. It effectively measures how many functions can be running at the same time. And by default there was an unreserved account concurrency set to 1000. This means you can have 1000 occurrences of Lambda functions running at once.
  * Unreserved concurrency - Account level - limit 1000
  * Reserved Concurrency - reserved for a lambda function from unreserved limit

## Other Options
#### Throttle
throttles to concurrency to 0.
#### Qualifier
* Version
* Alias


# Lambda execution environment lifecycle
