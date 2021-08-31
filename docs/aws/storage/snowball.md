## Snowball
* Essentially this service is used to securely transfer large amounts of data and I'm talking out of petabyte scale here, in and out of AWS. Either from your on-premise data center to Amazon S3 or from Amazon S3 back to your data center using a physical appliance, known as a snowball.
* appliance comes as either a 50 terabyte or 80 terabyte storage device. appliance is dust, water, and tamper resistant
* high-speed data transfer -  I/O 10 gigabit interfaces are available, RJ45 using CAT6, SFP Copper, and SFP Optical
* data is encrypted using 256-bit encryption keys generated from KMS, 
*  end to end tracking using an E-Ink shipping label - can also be tracked using the AWS Simple Notification Service with text messages or via the AWS Management Console
*  AWS Snowball is also HIPAA compliant allowing you to transfer protected health information in and out of S3
* once data reached customer's data center, responsibility of AWS to ensure that data held in the snowball appliance is deleted and removed. Ensures to follow  National Institute of Standard and Technology(NIST) guidelines to ensure all traces of data are removed from the media

#### Process
* you need to create an export job from within the AWS Management Console
* Within this job you can dictate shipping details, the S3 bucket, and the data to exported security mechanisms such as the KMS key for data encryption and also notifications.
* You will then receive delivery of your snowball appliance
* You will then receive delivery of your snowball appliance
* use the ports to connect the appliance to your network whilst it's powered off. Next power on the device and the E Ink display will let you know that it's ready
* configure the network settings of the device, such as the IP address to enable communication
* Now, to transfer data :
  * first gain specific access credential via a manifest file through the management console
  * then install the snowball Client software
  * you can now begin transferring data using the client software once authenticated with the manifest file
    
* When the data transfer is complete, you can disconnect the snowball appliance
* appliance must then be returned to AWS using specified shipping carriers. E Ink label will display the return address.


### Pricing

* you are charged for the normal Amazon S3 data charges,
* For each data transfer job, there is a charge in additional to shipping costs associated to the job
* For the 50 terabyte snowball, there is a $200 charge and for the 80 terabyte, it's $250
* Any delays requiring additional days incur further charges between $15 to $20, depending on the region
* 