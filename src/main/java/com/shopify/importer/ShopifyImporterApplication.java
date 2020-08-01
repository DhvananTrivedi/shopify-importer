package com.shopify.importer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"com.shopify"})
//@EnableJpaRepositories("com.shopify.importer.repository.StudentRepository")
@EnableScheduling
@SpringBootApplication
public class ShopifyImporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopifyImporterApplication.class, args);
	}

}
