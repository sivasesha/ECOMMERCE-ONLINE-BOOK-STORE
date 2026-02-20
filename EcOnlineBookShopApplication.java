package com.nit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication

@OpenAPIDefinition(
        info = @Info(
        title = "Ecommerc Book Store Management",
        version = "1.0",
        description = "Welcome to the Siva I Technologies",
        contact = @Contact(name ="Siva IT Technology",email = "siva@gmail.com")))


@EnableJpaRepositories(basePackages = "com.nit.repo") 
@EnableMongoRepositories(basePackages = "com.nit.mongo")
@EnableCaching
public class EcOnlineBookShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcOnlineBookShopApplication.class, args);
	}

	
}
