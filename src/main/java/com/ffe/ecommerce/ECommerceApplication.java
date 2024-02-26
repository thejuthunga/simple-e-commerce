package com.ffe.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
	ConfigurableApplicationContext run 	= SpringApplication.run(ECommerceApplication.class, args);
//	SpringApplication.exit(run, ()->0);
	}

}
