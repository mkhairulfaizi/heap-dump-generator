1. Testing in https://labs.play-with-k8s.com
Initialize Kubernetes in the terminal:
kubeadm init --pod-network-cidr=192.168.0.0/16

Set up kubectl for your user:
mkdir -p $HOME/.kube
cp /etc/kubernetes/admin.conf $HOME/.kube/config
chown $(id -u):$(id -g) $HOME/.kube/config

Deploy a network plugin (e.g., Calico):
kubectl apply -f https://docs.projectcalico.org/manifests/calico.yaml

Apply Kubernetes Configurations: Upload the YAML files to the Play with Kubernetes session, or create them directly using nano. 
Then apply them:
kubectl apply -f persistent-volume.yaml
kubectl apply -f deployment.yaml
kubectl apply -f ingress.yaml

Expose the Ingress:

Install an ingress controller (e.g., Nginx):
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml

Verify the ingress is working:
kubectl get ingress

Test the Application:
Map the demo.local host to the IP of your Kubernetes instance (displayed at the top of the Play with Kubernetes session) by editing your local hosts file:
<K8s_Instance_IP> demo.local

Access the application in your browser:
http://demo.local/hello

Cleanup
kubectl delete -f deployment.yaml
kubectl delete -f service.yaml
kubectl delete -f ingress.yaml