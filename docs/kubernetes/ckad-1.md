# Kubernetes CKAD Prep

## Highlight
* Automated Deployment rollout and rollback
* Seamless horizontal scaling
* Secret management
* Service Discovery and load balancing
* Linux and Windows Container support
* Simple log collection
* Stateful application support
* Persistent volume management
* CPU and memory quota
* Batch Job processing
* Role based access control

### Compare to :
* DC/OS (Datacenter operating system)
  * DC/OS or Distributed Cloud Operating System
  * DC/OS pools compute resources into a uniform task pool
  * DC/OS targets many different types of workloads, including, but not limited to containerized applications
  * DC/OS also includes a package manager to easily deploy systems like Kafka and Spark
  * You can even run Kubernetes on DC/OS given its flexibility for different types of workloads. 
* AWS ECS
  * ECS allows you to create pools of compute resources and uses API calls to orchestrate containers across them
  * Compute resources are EC2 instances that you can manage yourself or let AWS manage them for you using AWS Fargate.
* Docker Swarm

### Single node Cluster options
* Docker desktop (mac/windows)
* minikube (mac / windows/ linux)
* kubeadm (linux)

### Multinode cluster advantage
* Production workoad
* Horizontal scaling
* Tolerate node failure

### Fully managed solution
* Amazon EKS
* AKS (Azure)
* GKE (Google cloud)

### Full control
* kubespray
* kops
* kubeadm

### Enterprise support
* Openshift - RedHat
* Pivotal Container Service (PKS)
* Rancher

## Terminology


## Architecture
### Cluster

### Nodes

#### Worker node

#### Master Node
Master node runs Control planes. **Control planes** is the set of APIs Kubernetes users interact with.

### Control plane
 The control plane schedules containers onto nodes. The term scheduling does not refer to time in this context. Think of it from a kernel perspective. The kernel schedules processors onto the CPU according to multiple factors.
 
### Pods
* Smallest building block in kubernetes
* may include one or more container
* All container of a pod run on same node
* more useful abstraction comes on top of pod


## Interacting with Kubernetes
* modify cluster state by sending request to Kubernetes API server
* API server is a master component that acts as a frontend for the control paln

### Client libraries

* Handle authentication
* Manage rest api request
* k8s maintains official client library for java, python, go, .net, js
* Community lib
* **kubectl**

## Pods
* basic building block
* one or more container
* all containers in a pod share the same networ. allow any container to communicate with any other container in same pod
* one ip address per pod

### Pod declaration
* container image
* container port
* container restart policy
* resource limit