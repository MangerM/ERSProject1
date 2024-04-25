package com.revature.EmployeeReimbursement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EntityScan("com.revature.Models")
@ComponentScan("com.revature")
@EnableJpaRepositories("com.revature.DAOs")
public class EmployeeReimbursementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeReimbursementApplication.class, args);
	}

}
