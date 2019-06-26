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
package com.springboot.accessdatawithneoj4.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/6/26 14:15
 * 节点实体类
 */
@NodeEntity
public class Person {
	/**
	 * 自增id，无需有对应的构造函数
	 * */
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	public Person(){}

	public Person(String name) {
		this.name = name;
	}

	/**
	 * 设置关联类型
	 * */
	@Relationship(type = "TEAMMATE", direction = Relationship.UNDIRECTED)
	public Set<Person> teammates;

	public void workWith(Person person) {
		if (null == teammates) {
			teammates = new HashSet<>();
		}

		teammates.add(person);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return this.name + "'s teammates => " + Optional.ofNullable(this.teammates)
			.orElse(Collections.emptySet()).stream().map(Person::getName).collect(Collectors.toList());
	}

	public void setName(String name) {
		this.name = name;
	}
}