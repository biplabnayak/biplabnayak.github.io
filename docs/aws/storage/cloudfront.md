## Cloudfront
* a CDN
* It does not store data in stread it cache and distribute the dat from edge location
* Source of data can be S3, EC2 etc
* origin can be running within or outside of AWS.
### Web distribution
* static and dynamic content
* HTTP and  HTTPS
* uses an origin where the source data is coming from eg. EC2, S3

### RTMP Distribution
* for distribution of streaming media
* allow start viewing media before completing download from edge location
* source data can only exist in **S3**

### Configuration
* Origin
* Caching behaviour
* Distribution setting (which edge locations)
  