package com.springboot.accessrelationaldatausingjdbcwithspring;

import com.springboot.accessrelationaldatausingjdbcwithspring.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class AccessRelationalDataUsingJdbcWithSpringApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AccessRelationalDataUsingJdbcWithSpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessRelationalDataUsingJdbcWithSpringApplication.class, args);
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 使用JdbcTemplate对数据库进行操作
	 * */
	@Override
	public void run(String... args) throws Exception {
		logger.info("Create table");

		jdbcTemplate.execute("DROP TABLE customers IF EXISTS ");
		jdbcTemplate.execute("CREATE TABLE customers(" +
				"id SERIAL, " +
				"first_name VARCHAR(255), " +
				"last_name VARCHAR(255)" +
			")");

		// 将名字以“ ”划分为firstName和lastName
		List<Object[]> splitUpName = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
			.map(name -> name.split(" ")).collect(Collectors.toList());

		// 使用Java8的Stream来打印
		splitUpName.forEach(name -> logger.info(String.format("Insert customer record for %s %s", name[0], name[1])));

		// 批量导入数据
		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES(?, ?)", splitUpName);

		logger.info("Querying for customer records where first_name = 'Josh':");

		// 查询first_name为Josh的消费者
		jdbcTemplate.query("SELECT id, first_name, last_name from customers where first_name= ?", new Object[]{"Josh"},
			(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"),
				rs.getString("last_name")))
			.forEach(customer -> logger.info(customer.toString()));
	}
}
