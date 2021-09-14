# QLDB

* fully managed serverless database
* designed as a ledger database
* For Eg. recording financial data over a period of time. 
* QLDB would allow to maintain a complete history of accounting and transactional data between multiple parties in an immutable, transparent and cryptographic way through the use of the cryptographic algorithm, SHA-256, making it highly secure.
* a database journal, which is configured as append-only.
* the immutable transaction log that records all entries in a sequenced manner over time

### How it works?
* Data for your QLDB database is placed into tables of Amazon ion documents. Ion Document is a self describing data serialization format. it's a superset of JSON. which means any JSON is  a valid ion document
* QLDB table compromise of a group of Amazon ion documents and their revisions. 
* allow you to query document history across all document iteration
* Each time a change is committed to the journal, a sequence number is added to identify its place in the change history. In addition to this, an SHA-256 bit hash is used for verification purposes, which creates a cryptographic digest file of the journal. Now this identifies an encrypted signature of the changes made to your document and the history of your entire document at that point in time. This can then be used to verify the integrity of the changes made in relation to the digest file created. This helps to ensure that the data within your document has not been altered or changed in any way since it was first written to in QLDB.

## Storage
* Journal Storage - journal storage is the storage that is used to hold the history of changes made within the ledger database.
* Index storage - storage that is used to provision the tables and indexes within the ledger database and it's optimized for querying.

* With QLDB being a fully managed serverless service, this storage is managed for you and there are no specifications to select or make during the creation of your ledger database

## Integration with Kinesis
Using QLDB streams, you are able to capture all changes that are made to the journal and feed this information into an Amazon Kinesis data stream in near real-time.
This allows you to architect solutions whereby other AWS services could process this data from Kinesis to provide additional benefit.