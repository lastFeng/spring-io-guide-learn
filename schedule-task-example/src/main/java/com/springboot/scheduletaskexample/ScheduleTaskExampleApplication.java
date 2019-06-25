package com.springboot.scheduletaskexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScheduleTaskExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleTaskExampleApplication.class, args);
	}

}
