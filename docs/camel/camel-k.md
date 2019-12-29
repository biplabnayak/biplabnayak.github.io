## Camel K (aka Kamel)

#### Camel K installation on kubernetes (minikube)

* Start minikube
```jshelllanguage
 minikube start --vm-driver=virtualbox
 ```

* enable minikube `registry` addon
```jshelllanguage
minikube addons enable registry
```
* Check the kubernetes `registry` pod is up
```jshelllanguage
E:\sourcecode\github\n-reboot\product-service>k get pods -n kube-system
NAME                                       READY   STATUS                 RESTARTS   AGE
coredns-86c58d9df4-7dxx8                   1/1     Running                3          158d
coredns-86c58d9df4-zfwdk                   1/1     Running                3          158d
default-http-backend-5ff9d456ff-pm489      1/1     Running                0          25m
etcd-minikube                              1/1     Running                3          158d
kube-addon-manager-minikube                1/1     Running                3          158d
kube-apiserver-minikube                    1/1     Running                3          158d
kube-controller-manager-minikube           0/1     CreateContainerError   1          158d
kube-proxy-dvlxj                           1/1     Running                0          3h25m
kube-scheduler-minikube                    0/1     CreateContainerError   1          158d
kubernetes-dashboard-ccc79bfc9-d2qg6       1/1     Running                8          158d
nginx-ingress-controller-7c66d668b-7zzgs   1/1     Running                0          25m
registry-dt5q2                             1/1     Running                0          25m  ---
storage-provisioner                        1/1     Running                9          158d
```

* download camel-k binaries from https://github.com/apache/camel-k/releases (according to your OS)

* add kamel binary (kamel.exe) to path

* Install kamel
```jshelllanguage
E:\sourcecode\github\n-reboot\product-service>kamel install
Camel K installed in namespace default
```


