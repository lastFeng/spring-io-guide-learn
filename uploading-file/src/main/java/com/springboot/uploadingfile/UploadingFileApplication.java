package com.springboot.uploadingfile;

import com.springboot.uploadingfile.property.StorageProperties;
import com.springboot.uploadingfile.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class UploadingFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadingFileApplication.class, args);
	}

	/**
	 * 启动时初始化文件夹
	 * */
	@Bean
	public CommandLineRunner init(StorageService storageService) {
		return args -> {
			storageService.init();
		};
	}
}
