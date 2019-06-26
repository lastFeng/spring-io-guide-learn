package com.springboot.accessdatawithneoj4;

import com.springboot.accessdatawithneoj4.entity.Person;
import com.springboot.accessdatawithneoj4.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableNeo4jRepositories
public class AccessDataWithNeoj4Application {

	private static final Logger logger = LoggerFactory.getLogger(AccessDataWithNeoj4Application.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessDataWithNeoj4Application.class, args);
	}

	/**
	 * 对Neo4j进行操作，并设置关联关系
	 * */
	@Bean
	public CommandLineRunner run(PersonRepository personRepository) {
		return args -> {
			personRepository.deleteAll();

			Person greg = new Person("greg");
			Person roy = new Person("roy");
			Person craig = new Person("craig");

			List<Person> team = Arrays.asList(greg, roy, craig);

			logger.info("Before linking up with Neo4j...");

			team.stream().forEach(person -> logger.info("\t " + person.toString()));

			personRepository.save(greg);
			personRepository.save(roy);
			personRepository.save(craig);

			greg = personRepository.findByName(greg.getName());
			greg.workWith(roy);
			greg.workWith(craig);
			personRepository.save(greg);

			roy = personRepository.findByName(roy.getName());
			// 已经设置了与greg一起工作，所以无需重复设置
			roy.workWith(craig);
			personRepository.save(roy);

			logger.info("look up each person by name...");
			team.stream().forEach(person -> logger.info("\t" + personRepository.findByName(person.getName()).toString()));
		};
	}

}
