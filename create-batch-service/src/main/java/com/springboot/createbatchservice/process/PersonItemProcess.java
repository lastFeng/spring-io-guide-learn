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
package com.springboot.createbatchservice.process;

import com.springboot.createbatchservice.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/6/26 17:51
 */
public class PersonItemProcess implements ItemProcessor<Person, Person> {

	private static final Logger logger = LoggerFactory.getLogger(PersonItemProcess.class);

	@Override
	public Person process(final Person item) throws Exception {
		final String firstName = item.getFirstName().toUpperCase();
		final String lastName = item.getLastName().toUpperCase();

		final Person transformPerson = new Person(firstName, lastName);

		logger.info("Converting (" + item + ") into (" + transformPerson + ")");

		return transformPerson;
	}
}