### mqtt-starter 创建思路
1. 依赖`org.eclipse.paho.mqttv5.client` 的客户端
2. 添加依赖时 `optional` 参数可以减少多次依赖：project1依赖a.jar(optional=true),project2依赖project1,则project2不依赖a.jar
3. 配置各种Bean的注入，其中：`ConfigurationProperties` 注解可以在yml提示参数
4. 将配置的Bean通过 `resources/META-INF/spring.factories` 