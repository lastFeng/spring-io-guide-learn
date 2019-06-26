package com.springboot.messagingwithjms;

import com.springboot.messagingwithjms.domain.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
public class MessagingWithJmsApplication {
	private static final Logger logger = LoggerFactory.getLogger(MessagingWithJmsApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MessagingWithJmsApplication.class, args);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// 发送消息
		// 发送一条POJO的消息，template会重复使用converter
		logger.info("Sending an email message.");
		jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello JMS"));
	}
}
