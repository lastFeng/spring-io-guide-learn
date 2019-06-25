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
package com.springboot.consumerrestfulwebservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * @create: 2019/6/25 14:51
 *
 * 为了直接将数据绑定到自定义类型，需要指定与从API返回的JSON文档中的键完全相同的变量名。
 * 如果JSON文档中的变量名和键不匹配，则需要使用@jsonproperty annotation来指定JSON文档的确切键。
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {
	private Long id;
	private String quote;
}