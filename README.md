# CORS Test

用于测试服务器通信链路中CORS跨域配置是否正确。

## 使用方法：

修改server的配置文件config.properties。

将server使用gradle命令打包。

将server部署至目标web容器中。

将client中cors-test.html和js文件夹部署至服务主机下。

修改cors-test.html中serverUrl为server服务地址。

从客户端打开浏览器访问服务主机中的cors-test.html，并点击各个测试按钮进行测试。
