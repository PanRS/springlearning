Spring Boot2整合Apollo配置中心的使用。本demo包含了Apollo的多namespace的配置。多环境的配置。
custom-xxx.properties中的配置内容是放在apollo中新增的namespace下的内容。

# 动态日志测试
```
localhost:8888/log
```
# 测试属性获取
```
localhost:8889/
```
# k8s中apollo环境设置
创建configmap
```
kubectl create configmap apollo-env --from-literal=ENV="dev"
kubectl create configmap apollo-meta --from-literal=APOLLO_META="http://192.168.237.131:8080"
```
```
kubectl get configmap apollo-env -o yaml
kubectl get configmap apollo-meta -o yaml
```
官方推荐采用application.properties来设置，尤其是dubbo的应用，该配置并不能配置多环境env，因此在k8s上部署时，如果部署环境变了
建议直接APOLLO_META

