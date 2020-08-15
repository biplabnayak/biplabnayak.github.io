# Optimize AWS Storage Cost
## S3 and S3 Glacier

 ![S3 Storage class Comparision](/images/aws/s3/s3_features.png?raw=true)

Things to consider :

How critical is the data? 
How reproducible is the data, so can it easily be created again if need be? 
What is the access pattern of the data likely to be? 
Will latency a factor when accessing the object?

some of these storage classes have a tiered pricing structure.
**Standard and S3 Intelligent** storage class your costs vary depending on how much data is stored within a single month. As you can see from the table below (based on the London region) for the Standard storage, your price is reduced as you add more and more data within the same month.

![S3 Standard Pricing](/images/aws/s3/s3_standard_pricing.png?raw=true)

![S3 Intelligent Pricing](/images/aws/s3/s3_intelligent_tiring_pricing.png?raw=true)

For All other storage classes, pricing is FLAT.

![S3 All Other Pricing](/images/aws/s3/s3_all_other pricing.png?raw=true)


Using the right storage class is one way to optimize your costs. But, You should also be familiar with :
 * Request Cost 
 * Data retrieval costs
 * Data transfer costs
 * Management and replication costs
 
 ### Request Cost
 Below is the typical request cost (based on London Region) just for an idea.
 DELETE and CANCEL requests are free.
 
 ![S3 All Other Pricing](/images/aws/s3/S3_request_cost.PNG?raw=true)
 
 ### Retrieval Costs