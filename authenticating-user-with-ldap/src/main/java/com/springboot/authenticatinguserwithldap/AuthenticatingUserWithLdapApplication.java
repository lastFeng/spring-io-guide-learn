package com.springboot.authenticatinguserwithldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class AuthenticatingUserWithLdapApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticatingUserWithLdapApplication.class, args);
	}

}
