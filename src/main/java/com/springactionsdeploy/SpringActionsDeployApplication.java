package com.springactionsdeploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringActionsDeployApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringActionsDeployApplication.class, args);
	}

}
