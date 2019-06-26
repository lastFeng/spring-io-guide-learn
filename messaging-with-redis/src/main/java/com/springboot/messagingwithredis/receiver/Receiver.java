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
package com.springboot.messagingwithredis.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/6/26 9:30
 * 消息接收
 */
public class Receiver {
	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

	@Autowired
	private CountDownLatch countDownLatch;

	public Receiver(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	/**
	 * 消息接收函数，通过Container调用
	 * @param message 收到的消息
	 * */
	public void receiveMessage(String message) {
		logger.info("Receive << " + message + ">>");
		countDownLatch.countDown();
	}
}