# Kubernetes cheat sheet

### Start minikube cluster

```jshelllanguage
 minikube start --vm-driver=virtualbox
 ```

```jshelllanguage
minikube dashboard
```
 
### Cluster Information
 
 ```jshelllanguage
kubectl cluster-info
```
 
 
### Apply yml defination to kubernetes

```jshelllanguage
kubectl apply -f <file_name.yml> -f <namespace>
```
    

### Delete all kubernetes objects defined in an yaml file
```jshelllanguage
    kubectl delete -f <file_name.yml> -f <namespace>
```

### List all kubernetes objects in the namespace
```jshelllanguage
kubectl get all -n <namespace>
```
    

### List all pods
```jshelllanguage
kubectl get pods -n <namespace>
```

### List Network policy
```jshelllanguage
    I:\k8s-deploy>kubectl get netpol -n <namespace>

    NAME                                 POD-SELECTOR     AGE
    allow-ingress-to-helloworld-policy   app=helloworld   22h
```

### Delete any kubernetes object
```jshelllanguage
kubectl delete -n <namespace> <object_type>/<object_name> ...

for example :

    kubectl delete -n <namespace> pod/nginx-wdswe2-343dcd service/nginx
```
 