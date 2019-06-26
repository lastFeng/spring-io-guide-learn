/*
 * Copyright 2001-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springboot.messagingwithredis.config;

import com.springboot.messagingwithredis.receiver.Receiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/6/26 9:35
 *
 * 以Redis为消息中间件的配置操作过程为：
 *  注册消息监听容器，并将消息连接工厂(xxxConnectFactory)和消息监听适配器(MessageListenerAdapter)添加到容器中,
 *      在消息监听适配器中设置Topic
 *  配置消息监听适配器（MessageListenerAdapter）设置消息接收类以及接收方法
 */
@Configuration
public class MessageReceiverConfig {
	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
	                                                                      MessageListenerAdapter messageListenerAdapter){
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(messageListenerAdapter, new PatternTopic("chat"));
		return container;
	}

	@Bean
	public MessageListenerAdapter messageListenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	public Receiver receiver(CountDownLatch countDownLatch) {
		return new Receiver(countDownLatch);
	}

	@Bean
	public CountDownLatch countDownLatch(){
		return new CountDownLatch(1);
	}

	@Bean
	public StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}


}