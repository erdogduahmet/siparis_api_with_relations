package com.ahmeterdogdu.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.ahmeterdogdu"})
@ComponentScan(basePackages = {"com.ahmeterdogdu"})
@EnableJpaRepositories(basePackages = {"com.ahmeterdogdu"})
@PropertySource(value = "classpath:app.properties")
@SpringBootApplication
public class SiparisYonetimiApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiparisYonetimiApiApplication.class, args);
	}

}
