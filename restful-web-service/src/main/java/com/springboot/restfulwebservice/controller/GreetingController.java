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
package com.springboot.restfulwebservice.controller;

import com.springboot.restfulwebservice.domain.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/6/25 9:32
 * Greeting的Controller类，通过页面的get请求来获取Greeting对象的内容
 * 除了Get方法外，还有POSt、PUT和DELETE方法都可以来操作
 */
@RestController
public class GreetingController {
	private static final String template = "Hello, %s!";
	private final AtomicLong atomicLong = new AtomicLong();

	/**
	 * URI中如果有自定义的name请求内容，则显示该信息，如果没有，则显示默认的world信息
	 * @param name request请求值
	 * @return greeting对象的Json对象
	 * */
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "world")String name) {
		return new Greeting(atomicLong.incrementAndGet(), String.format(template, name));
	}
}