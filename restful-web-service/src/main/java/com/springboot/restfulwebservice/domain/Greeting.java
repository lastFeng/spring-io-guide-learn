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
package com.springboot.restfulwebservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/6/25 9:24
 *
 * 返回给页面的对象，在实际生产中，该类属于与数据库对应的对象，不该以此来展示，将数据库表信息完全暴露
 * 可以使用DTO或者VO的对象来封装，展示需要展示的内容
 * 注解则是使用lombok来简化编程，即：无需再添加setter和getter对象，同时使用注解来定义全参和无参构造函数，并实现ToString方法
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Greeting {
	/**
	 * id
	 * */
	private Long id;
	/**
	 * content
	 */
	private String content;
}