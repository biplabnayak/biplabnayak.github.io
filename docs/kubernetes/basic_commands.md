# Kubernetes cheat sheet

## Minikube Commands

###### Start minikube cluster
```jshelllanguage
 minikube start --vm-driver=virtualbox
 ```

###### Show Dashboard
```jshelllanguage
minikube dashboard
```

###### Stop minikube Cluster
```jshelllanguage
minikube stop
```



## Kubernetes Cluster commands 

###### Cluster Information
 ```jshelllanguage
kubectl cluster-info
```

###### API Versions supported in Kubernetes
```jshelllanguage
kubectl api-versions
```

###### API Resources available in Kubernetes
```jshelllanguage
kubectl api-resources
```


 
## Kubernetes get Object Info

###### List all kubernetes objects in the namespace
```jshelllanguage
kubectl get all -n <namespace>
``` 


###### List all pods
```jshelllanguage
kubectl get pods -n <namespace>
```
 
###### List Network policy
```jshelllanguage
    I:\k8s-deploy>kubectl get netpol -n <namespace>

    NAME                                 POD-SELECTOR     AGE
    allow-ingress-to-helloworld-policy   app=helloworld   22h
```

 
 
 
## kubernetes Deployment Commands

###### Apply yml definition to kubernetes

```jshelllanguage
kubectl apply -f <file_name.yml> -f <namespace>
```

###### Running a Docker image in Interactive mode in kubernetes cluster
```jshelllanguage
kubectl run --generator=run-pod/v1 --image hr4g67xo8d45/product-service -it product-service  -- /bin/bash
```



###### Delete any kubernetes object
```jshelllanguage
kubectl delete -n <namespace> <object_type>/<object_name> ...

for example :

    kubectl delete -n <namespace> pod/nginx-wdswe2-343dcd service/nginx
```
 

###### Delete all kubernetes objects defined in an yaml file
```jshelllanguage
    kubectl delete -f <file_name.yml> -f <namespace>
```
