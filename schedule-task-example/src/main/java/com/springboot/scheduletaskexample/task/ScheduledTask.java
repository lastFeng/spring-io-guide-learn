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
package com.springboot.scheduletaskexample.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/6/25 9:39
 */
@Component
public class ScheduledTask {
	/**记录当前类的日志，使用默认日志格式*/
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
	/**定义日期格式*/
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 每隔5秒向INFO日志中打印当前时间
	 * 注意：在Application文件中要增加：@EnableScheduling
	 * */
	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime(){
		logger.info("Current time is: {}", SIMPLE_DATE_FORMAT.format(new Date()));
	}
}