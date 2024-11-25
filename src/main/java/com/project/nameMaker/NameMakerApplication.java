package com.project.nameMaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NameMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NameMakerApplication.class, args);
	}

}
