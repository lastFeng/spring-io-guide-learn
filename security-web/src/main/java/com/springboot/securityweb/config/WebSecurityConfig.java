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
package com.springboot.securityweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/6/27 8:59
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// 设置http链接的访问权限
			.authorizeRequests()
				// 对于”/“和"/home"访问不需要权限认证，任何人都可以访问（主页不需要认证）
				.antMatchers("/", "/home").permitAll()
				// 剩余任何网页均需要认证后才能访问
				.anyRequest().authenticated()
			.and()
			// 使用默认的用户登录界面
			.formLogin()
				// 设置登录页的访问链接
				.loginPage("/login")
				// 任何人均可访问
				.permitAll()
			.and()
			// 设置登出页，任何人都可以
			.logout()
				.permitAll();
	}

	/**
	 * 在内存中创建账号
	 * 使用内置的UserDetails和UserDetailService类进行创建
	 * 也可以集成UserDetails和UserDetailsService类来自定义自己的User账号
	 * */
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails user =
			User
				.withUsername("user")
				// 由于withDefaultEncoder过时，需要使用此过程进行密码加密
				.password(encoder.encode("password"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}