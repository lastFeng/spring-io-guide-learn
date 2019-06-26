## Messaging with Redis
### 使用Redis作为消息队列的示例

总体思路：

### 消息接收类
   - 编写消息接收类及其消息接收方法，以供消息监听适配器调用（MessageListenerAdapter）

### 消息队列配置文件
   - 编写消息监听适配器（MessageListenerAdapter）
   - 编写消息监听容器（RedisMessageListenerContainer）：
   设置RedisConnectFactory和MessageListenerAdapter。并定义消息channel
   - 编写StringRedisTemplate，将RedisConnectFactory注入

### 消息发送
   - 在main方法中，通过StringRedisTemplate通过channel将消息发送给消息接收类