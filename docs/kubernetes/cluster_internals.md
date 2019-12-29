# Kubernetes Cluster Internals

kube-system is the namespace for objects created by the Kubernetes system. this would contain pods like kube-dns, kube-proxy, kubernetes-dashboard and stuff like fluentd, heapster, ingresses and so on.

```jshelllanguage
C:\>k get all -n kube-system
NAME                                            READY   STATUS    RESTARTS   AGE
pod/coredns-5644d7b6d9-9p8fv                    1/1     Running   0          5m53s
pod/coredns-5644d7b6d9-hr7b6                    1/1     Running   0          5m53s
pod/etcd-minikube                               1/1     Running   0          4m52s
pod/kube-addon-manager-minikube                 1/1     Running   0          6m
pod/kube-apiserver-minikube                     1/1     Running   0          4m56s
pod/kube-controller-manager-minikube            1/1     Running   0          5m8s
pod/kube-proxy-29jqp                            1/1     Running   0          5m53s
pod/kube-scheduler-minikube                     1/1     Running   0          4m46s
pod/nginx-ingress-controller-6fc5bcc8c9-xx76z   1/1     Running   0          5m50s
pod/registry-fgd4f                              1/1     Running   0          5m51s
pod/registry-proxy-mlpc9                        1/1     Running   0          5m51s
pod/storage-provisioner                         1/1     Running   0          5m50s

NAME                             DESIRED   CURRENT   READY   AGE
replicationcontroller/registry   1         1         1       5m51s

NAME               TYPE        CLUSTER-IP    EXTERNAL-IP   PORT(S)                  AGE
service/kube-dns   ClusterIP   10.96.0.10    <none>        53/UDP,53/TCP,9153/TCP   6m1s
service/registry   ClusterIP   10.98.47.52   <none>        80/TCP                   5m51s

NAME                            DESIRED   CURRENT   READY   UP-TO-DATE   AVAILABLE   NODE SELECTOR                 AGE
daemonset.apps/kube-proxy       1         1         1       1            1           beta.kubernetes.io/os=linux   6m1s
daemonset.apps/registry-proxy   1         1         1       1            1           <none>                        5m51s

NAME                                       READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/coredns                    2/2     2            2           6m1s
deployment.apps/nginx-ingress-controller   1/1     1            1           5m52s

NAME                                                  DESIRED   CURRENT   READY   AGE
replicaset.apps/coredns-5644d7b6d9                    2         2         2       5m53s
replicaset.apps/nginx-ingress-controller-6fc5bcc8c9   1         1         1       5m52s

``` 