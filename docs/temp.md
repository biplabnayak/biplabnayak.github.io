## Kubernetes
Kubernetes is an opensource container orchastration tool which help manage and deploy containerized applications. Advantages of using a orchastration tool like kubernetes is :
1. Easy deployment of your application
2. Easy to scale the application
3. 


## Basic Kubernetes Objects
- Pod
- Service
- Volume
- Namespace

Also includes higher leves abstraction called Controllers, such as :
- ReplicaSet
- Deployment
- StatefulSet
- DaemonSet
- Job



## Apply yml defination to kubernetes

    kubectl apply -f <file_name.yml> -f <namespace>

## Delete all kubernetes objects defined in an yaml file

    kubectl delete -f <file_name.yml> -f <namespace>

## List all kubernetes objects in the namespace
    
    kubectl get all -n <namespace>

## List all pods
   
    kubectl get pods -n <namespace>

## List Network policy

    kubectl get netpol -n <namespace>

## Delete any kubernetes object
  
    kubectl delete -n <namespace> <object_type>/<object_name> ...

    for example :

    kubectl delete -n <namespace> pod/nginx-wdswe2-343dcd service/nginx
