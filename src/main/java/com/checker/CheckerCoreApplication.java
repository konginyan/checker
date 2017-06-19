package com.checker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class CheckerCoreApplication{

	public static void main(String[] args) {
		SpringApplication.run(CheckerCoreApplication.class, args);
	}
}
