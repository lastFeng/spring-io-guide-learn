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
package com.springboot.createbatchservice.config;

import com.springboot.createbatchservice.domain.Person;
import com.springboot.createbatchservice.process.PersonItemProcess;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/6/26 17:57
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	// tag::readerwriterprocessor[] --Reader
	@Bean
	public FlatFileItemReader<Person> reader() {
		return new FlatFileItemReaderBuilder<Person>()
			// 设置reader名称
			.name("personItemReader")
			// 设置资源位置
			.resource(new ClassPathResource("sample-data.csv"))
			// 设置资源的分割
			.delimited()
			// 分割之后对应的Person属性
			.names(new String[]{"firstName", "lastName"})
			// 设置资源对应的实体类
			.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){{
				setTargetType(Person.class);
			}})
			.build();
	}

	// processor
	@Bean
	public PersonItemProcess process() {
		return new PersonItemProcess();
	}

	// writer
	@Bean
	public JdbcBatchItemWriter<Person> writer(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Person>()
			// 通过JDBC的批量处理工具，将其写入到数据库中
			.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
			.sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
			// 对数据库进行操作
			.dataSource(dataSource)
			.build();
	}
	// end::readerwriterprocessor[]

	// tag: jobstep[]
	/**
	 * 定义了Job的过程
	 * */
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step) {
		return jobBuilderFactory.get("importUserJob")
			// 因为是批量出入，而对于主键需要不断增加
			.incrementer(new RunIdIncrementer())
			// 监听，在操作完成后进行sql查询
			.listener(listener)
			.flow(step)
			.end()
			.build();
	}

	/**
	 * 定义了Job中的一个Step
	 * 每个Step包括：reader、processor、writer
	 * */
	@Bean
	public Step step(JdbcBatchItemWriter<Person> writer){
		return stepBuilderFactory.get("step")
			.<Person, Person> chunk(10)
			.reader(reader())
			.processor(process())
			.writer(writer)
			.build();
	}

	// end:: jobstep[]
}