部署步骤
首先创建一个单独的namespace
$ kubectl create ns logs
$ git clone https://github.com/pythonTaotao/kubernetes.git
$ cd kubernetes/k8s-ELK
1、部署es
$ helm install --name-template efk-els -n logs elasticsearch/

NAME: efk-els
LAST DEPLOYED: Fri May 22 17:00:27 2020
NAMESPACE: logs
STATUS: deployed
REVISION: 1
NOTES:
The elasticsearch cluster has been installed.

Elasticsearch can be accessed:

  * Within your cluster, at the following DNS name at port 9200:

    efk-els-elasticsearch-client.logs.svc

  * From outside the cluster, run these commands in the same shell:

    export POD_NAME=$(kubectl get pods --namespace logs -l "app=elasticsearch,component=client,release=efk-els" -o jsonpath="{.items[0].metadata.name}")
    echo "Visit http://127.0.0.1:9200 to use Elasticsearch"
    kubectl port-forward --namespace logs $POD_NAME 9200:9200

检查安装是否OK
a、查看pod是否running
[root@master k8s-efk]# kubectl get po -n logs
NAME                                            READY   STATUS    RESTARTS   AGE
efk-els-elasticsearch-client-547c6bc6dd-46z29   1/1     Running   0          2m11s
efk-els-elasticsearch-client-547c6bc6dd-tcpfk   1/1     Running   0          2m11s
efk-els-elasticsearch-data-0                    1/1     Running   0          2m11s
efk-els-elasticsearch-data-1                    1/1     Running   0          74s
efk-els-elasticsearch-master-0                  1/1     Running   0          2m11s
efk-els-elasticsearch-master-1                  1/1     Running   0          66s
efk-els-elasticsearch-master-2                  1/1     Running   0          29s

b、运行一个测试pod检查es端口启动是否在正常
$ kubectl run cirros-$RANDOM --rm -it --image=cirros -- sh
/ # curl http://efk-els-elasticsearch-client.logs.svc:9200
{
  "name" : "efk-els-elasticsearch-client-547c6bc6dd-tcpfk",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "_tkC188NRxq96rlS02Xktg",
  "version" : {
    "number" : "6.8.6",
    "build_flavor" : "oss",
    "build_type" : "docker",
    "build_hash" : "3d9f765",
    "build_date" : "2019-12-13T17:11:52.013738Z",
    "build_snapshot" : false,
    "lucene_version" : "7.7.2",
    "minimum_wire_compatibility_version" : "5.6.0",
    "minimum_index_compatibility_version" : "5.0.0"
  },
  "tagline" : "You Know, for Search"
}

如何出现以上提示，说明我们的es安装OK

2、部署fluentd
helm install --name-template efk-flu -n logs fluentd-elasticsearch/

WARNING: This chart is deprecated
NAME: efk-flu
LAST DEPLOYED: Fri May 22 17:06:21 2020
NAMESPACE: logs
STATUS: deployed
REVISION: 1
TEST SUITE: None
NOTES:
1. To verify that Fluentd has started, run:

  kubectl --namespace=logs get pods -l "app.kubernetes.io/name=fluentd-elasticsearch,app.kubernetes.io/instance=efk-flu"

THIS APPLICATION CAPTURES ALL CONSOLE OUTPUT AND FORWARDS IT TO elasticsearch . Anything that might be identifying,
including things like IP addresses, container images, and object names will NOT be anonymized.


检查安装是否ok
/ # curl http://efk-els-elasticsearch-client.logs.svc:9200/_cat/indices
green open logstash-2020.05.22 TktJEzBASwOrxcJjmm_XnQ 5 1 1131 0 3.4mb 1.7mb

如果能够使用测试pod获取到索引吗，说明fluentd部署OK

3、部署kibana
$ helm install --name-template efk-kib -n logs kibana/

[root@master k8s-efk]# kubectl get svc -n logs | grep kibana
efk-kib-kibana                    NodePort    10.103.63.52    <none>        443:30426/TCP   22s

浏览器访问：http://<master_ip>:30426

如果能够正常访问我们的kibana，说明我们的efk部署完成