package com.example.demo.configurationn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EntityScan(basePackages = "com.example.demo.book.model")
@EnableJpaRepositories(basePackages = "com.example.demo.book.persistence")
@SpringBootApplication(scanBasePackages = {
		"com.example.demo"

})

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
