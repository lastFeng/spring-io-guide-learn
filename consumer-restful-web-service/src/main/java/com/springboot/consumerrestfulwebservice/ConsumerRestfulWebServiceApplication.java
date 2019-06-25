package com.springboot.consumerrestfulwebservice;

import com.springboot.consumerrestfulwebservice.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumerRestfulWebServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerRestfulWebServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumerRestfulWebServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			logger.info(quote.toString());
		};
	}
}
