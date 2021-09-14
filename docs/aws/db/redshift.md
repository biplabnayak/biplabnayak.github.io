# Redshift
* Data warehouse 
* capable of storing and processing PetaBytes of data
* based on postgres SQL(but many differences) : https://docs.aws.amazon.com/redshift/latest/dg/c_redshift-and-postgres-sql.html
* consolidate data from multiple sources to allow you to run business intelligent tools, across your data
* ETL Jobs - (Extract, Transform, Load)

## Redshift Cluster
* Each cluster have one redshift engine (with at least one Database)
* Cluster have oen or mode compute node. If it has more than one compute node, one leader node would be elected
* each compute node will have its own CPU, RAM, Storage. compute nodes can have different capacities
* Leader node :
  * manges the compute nodes
  * serves as a gateway to redshift cluster from application
  * when receive a query request, it creates execution plan send it to the compute nodes aggregates the data and return
* Node Slices:
  * partition of compute node where the nodes memory and disk spaces split
  * node slice then processes operations given by the leader node where parallel operations can then be performed across all slices and all nodes at once for the same query
  * compute node's capacity determines how many slices it can be split into : https://docs.aws.amazon.com/redshift/latest/dg/c_best-practices-best-dist-key.html
  * it is possible to distribute rows of that table across different nodes slices based upon how the distribution case is defined for the table.
    
## Connecting to Redshift
* use industry standard open database connectivity, ODBC. 
* Java database conductivity, JDBC drivers for PostgreSQL

## Features
* massively parallel processing. associating rows from tables across different nodes slices and nodes, leader node distributes the execution plan which run parallally on each node/ slice and masternode aggregates the result
* Columnar data storage -  reducing the number of times the database has to perform disk I/O
* Result caching helps to reduce the time it takes to carry out queries by caching some results of the queries in the memory of the leader node in a cluster.

## monitoring
* Integrated with cloudwatch. Can view CPU utilization and throughput
* Redshift also generates Query and load performance data. which can be viewed from redshift console

During the creation of your Redshift cluster, you can select up to 10 different IAM roles to associate with your cluster. This allows you to grant the Amazon Redshift principle access to other services on your behalf. For Example, Amazon S3 where you might have a data lake