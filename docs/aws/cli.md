[ec2-user@ip-10-0-0-32 ~]$ sudo pip install --upgrade awscli
[ec2-user@ip-10-0-0-32 ~]$ aws help
[ec2-user@ip-10-0-0-32 ~]$ aws ec2 help
[ec2-user@ip-10-0-0-32 ~]$ aws ec2 describe-regions help
[ec2-user@ip-10-0-0-32 ~]$ aws ec2 describe-regions --output json

aws s3 mb s3://cloudacademy-biplab
aws s3 ls
aws s3 ls s3://cloudacademy-biplab
aws s3 cp . s3://cloudacademy-biplab --recursive
aws s3 website s3://cloudacademy-biplab --index-document index.html --error-document error/index.html
aws s3 cp . s3://cloudacademy-biplab  --recursive --acl public-read
aws s3 sync . s3://cloudacademy-biplab