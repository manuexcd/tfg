package com.uca.tfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TfgApplication {

	public static void main(String[] args) {
		SpringApplication.run(TfgApplication.class, args);
	}
}
