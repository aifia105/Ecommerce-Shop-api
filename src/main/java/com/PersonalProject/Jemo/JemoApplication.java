package com.PersonalProject.Jemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableJpaRepositories
@EnableTransactionManagement
@EnableJpaAuditing
@SpringBootApplication
public class JemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JemoApplication.class, args);
	}

}
