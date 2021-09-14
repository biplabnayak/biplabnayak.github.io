### Symmetric cryptography
* Single key is used for both encryption and decryption.
* Disadvantage : if the key is compromised, encrypted data could be easily decrypted
* Commonly used Algorithms :
   * AED
   * DES
   * Triple DES
   * Blowfish
* Symmetric is faster
### ASymmetric Cryptography
* 2 Separate keys for encryption and decryption. created at the same time and linked with mathematical algorithm
* private key should never be shared
* public key can be shared with anyone.
* Both keys are required to decrypted data
* Asymmetric is slower
* commonly used Algorithm :
   * RSA - (Rivest-Shamir-Adleman)
   * Diffie-Hellman
   * Digital signature algorithm
    
## KMS
* KMS contain key to decrypt private data
* Admins do not have access to the keys in KMS
* All administrative action require dual authentication by 2 admin
* only for data encryption at rest
* **KMS is a regional service. But multi keys are now supported for clien-side encryption in AWS Encryption SDK, AWS S3 Encryption Client, AWS DynamoDB Encryption Client**
### Server side encryption
* Encryption done by server
* performing encryption and managing key is handled by server

### Client side encryption
* encryption at client side
* overhead of encryption process is on client

#### Compliance
* KMS works with CloudTrail to audit and track the encryption keys are being used by whom
* other metadata like source IP, API Calls like "encrypt", "decrypt" etc


## Components of KMS service

### Customer Master Key(CMK)
* main key type within KMS
* can encrypt data upto 4KB
* used in relation to your DEKs. CMK encrypt and decrypt the DEK, then used outside the KMS service
* any CMK created are protected by FIPS 140-2 validated cryptographic modules
* 2 types of CMKs
  * AWS managed CMKs
    * used by other AWS services
    * can be used by the service that created it
    * they are created on the first time you implement encryption using that service
  * Customer managed CMKs
    * more flexible
    * can perform rotation, governing access, key policy configuration
    * can enable/disable the keys as required


### Data Encryption Keys(DEK)
* key is used to encrypt any size of data
* CMK generates DEK in plain text and encrypted format. both of the keys are then used to encrypt data
* the process of encrypion of one key by another key is called envelop encryption
* only way to decrypt the object is if you have the relevant decrypt permission for that CMK that the data keys are associated to. 

#### Example - Encryption process with S3 object (SSE-KMS)
##### Encryption
* when object is posted to S3, S3 will request for the key to KMS
* KMS will return a plain text data key and a encrypted version of the same data key
* plain text data key will be used for encrypting the object and then destroyed
* encrypted data key is stored along side the s3 object, which will be used for decryption

##### Decryption
* when s3 receive a get request, it sends the encrypted data key (associated with the object during encryption) to KMS
* KMS will decrypt the encrypted data key using the same CMK and send the plain text data key ot S3.
* S3 uses the plain text data key to decrypt the object and return to user. nad destroy the plain text data key

## Permissions & Key policies
* access control for most AWS services controlled by IAM alone. but to manage access to CMK, you must use a Key Policy.
* CMK can not be used without associating it with a CMK
* 3 ways to control access to CMK
  * Key Policy
  * Key Policy with IAM
  * Key Policy with Grants
  
### Key Policy
* json based document (like IAM policy)
* Contains Resource, Action, effect, principal, condition(optional)
* KMS creates a default key policy to allow principal to use CMK.
* KMS allow root  user of aws account with full access to CMK
* if full access user is deleted from IAM, contact AWS to regain control

* when you carete a CMK from management console, you can configure administrators of the CMK and users which are allowed access to the CMK
* the administrators can be users or roles in aim
* the principal can only administer the CMK, not perform encryption. but they have access to modify key policy. so they can grant permission to themselves to perform encryption
* few admin actions are : "kms:Create", "kms:Describe", "kms:Enable", "kms:List", "kms:Put", "kms:Update", "kms:Delete" etc.. 

### Key Policies with IAM Policy
* This is possible only when you have root full KMS access to CMK
* THis enables you to centralise the permissions through IAM
* you can now provide access like "kms:Encrypt", "kms:Decrypt" permission to user group or role

### Key Policy with Grant
* allow to delegate permission to another AWS principal within AWS account
* another resource based method to access control on CMKs
* They need to be created using KMS APIs
* within key policy, you allow the user with 3 Grant permission - "kms:CreateGrant","kms:ListGrants", "kms:RevokeGrants"
* With Grant, the grantee issued a grant token and grantId. so, the grantee will have to  use the grant oken to perform action on CMK


## Key Management
### Rotation of CMK
* need to rotate CMK for security best practices
  
#### Automatic key rotation
* supports automatic key rotation (ever 365 days). with automatic rotation key alias, ARN will be same. just the backing KEY will change
* it retains the old keys for decrypting the older data
* Limitation of automatic key rotation: 
  * Automatic key rotation is not possible with imported key material
  * key rotation happens every 365 days. duration can not be altered

* you can anytime perform manual key rotation
*  If your CMK is the state of disabled or pending deletion, then KMS will not perform a key rotation. If the key is re-enabled, or if the deletion process is canceled, then KMS will assess the age of the backing key, and if that key is older than 365 days, the automatic key rotation process will rotate the key immediately.
* Not possible to manage key rotation for any **AWS Managed Keys**. they are rotated every 2095 days (3 years).

#### Manual Key rotation 
* with manual rotation, a new CMK ID is generated along with backing key
* CMK ID will need to be updated in application (if referenced)
* You could use alias name for your key and simply update your alias target to point t new CMK ID
* eg. aws kms update-alias -- alias-name <name> target-key-id <new-key>
* on manual key rotation, old backing key should not be disabled or deleted (old keys must be active to decrypt data encrypted with old keys)

### Import key material from existing KMS outside AWS
* key material is the backing key
* key material is automatically created for a cmk. but its possible to create a CMK without key material by selecting "External" key material origin.
### Deletion of CMKs