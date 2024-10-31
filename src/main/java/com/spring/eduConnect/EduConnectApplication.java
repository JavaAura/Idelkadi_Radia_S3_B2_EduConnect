package com.spring.eduConnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.spring.eduConnect")
@PropertySource("classpath:application-dev.properties")
public class EduConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduConnectApplication.class, args);
	}

}
