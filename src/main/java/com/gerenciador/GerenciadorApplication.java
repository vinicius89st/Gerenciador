package com.gerenciador;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorApplication {

	public static void main(String[] args) {
		PropertyConfigurator.configure("conf/log4j.properties");
		SpringApplication.run(GerenciadorApplication.class, args);
	}

}
