# AWS Solutions Overview
## AWS Storage solutions
* Amazon Simple Storage Service(S3)
  
  * Object storage
  * Suitable for situations where files are written once and read many times
  * Not Optimal for heavy read and writes at same time

* Amazon Elastic Block Storage (EBS)

  * File stored in small small blocks(not as a single object). so the portion of file that changed will be updated 
  * For low latency access
  * Can be attached to single EC2 instance
  
* Amazon Elastic File Storage (EFS)
