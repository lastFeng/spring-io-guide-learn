package com.springboot.messagingwithredis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class MessagingWithRedisApplication {

	private static final Logger logger = LoggerFactory.getLogger(MessagingWithRedisApplication.class);

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = SpringApplication.run(MessagingWithRedisApplication.class, args);
		StringRedisTemplate redisTemplate = context.getBean(StringRedisTemplate.class);
		CountDownLatch countDownLatch = context.getBean(CountDownLatch.class);

		logger.info("Sending message...");
		redisTemplate.convertAndSend("chat", "Hello redis!");
		countDownLatch.await();
		System.exit(0);
	}

}
