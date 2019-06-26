## Messaging with JMS
### 通过JMS（Java Message Service）来获取消息
   - 在Receiver类中，对queue进行监听，当有消息发送时，会到该队列中获取
   同时需要对ContainerFactory: JmsListenerContainerFactory进行配置
   这样才能了解监听的是哪个容器
   - 配置JmsListenerContainerFactory和自定义消息转化MessageConverter，
   来定义获取对象的格式
   - 同时，使用JMS时不能单独使用，需要与ActiveMq或RabbitMq进行使用，Active如下依赖：

  ```pom
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-activemq</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.activemq</groupId>
        <artifactId>activemq-broker</artifactId>
    </dependency>
  ```