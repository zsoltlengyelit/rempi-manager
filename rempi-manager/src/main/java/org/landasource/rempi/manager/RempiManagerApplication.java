package org.landasource.rempi.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
public class RempiManagerApplication {

	public static void main(final String[] args) {

		SpringApplication.run(RempiManagerApplication.class, args);
	}

}
