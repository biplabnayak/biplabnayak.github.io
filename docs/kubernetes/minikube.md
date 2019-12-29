###### Start minikube cluster
```jshelllanguage
 C:\>minikube --memory 4096 --cpus 2 start --vm-driver=virtualbox
 * minikube v1.5.2 on Microsoft Windows 10 Pro 10.0.17763 Build 17763
 * Downloading VM boot image ...
     > minikube-v1.5.1.iso.sha256: 65 B / 65 B [--------------] 100.00% ? p/s 0s
     > minikube-v1.5.1.iso: 143.76 MiB / 143.76 MiB [-] 100.00% 6.30 MiB p/s 23s
 * Creating virtualbox VM (CPUs=2, Memory=4096MB, Disk=20000MB) ...
 * Preparing Kubernetes v1.16.2 on Docker '18.09.9' ...
 * Downloading kubelet v1.16.2
 * Downloading kubeadm v1.16.2
 * Pulling images ...
 * Launching Kubernetes ...
 * Waiting for: apiserver
 * Done! kubectl is now configured to use "minikube"

 ```

###### Show Dashboard
```jshelllanguage
minikube dashboard
```

###### Stop minikube Cluster
```jshelllanguage
minikube stop
```


## Deleting minikube
```
E:\sourcecode\github\biplabnayak\biplabnayak.github.io>minikube delete
x   Deleting "minikube" from virtualbox ...
-   The "minikube" cluster has been deleted.

```