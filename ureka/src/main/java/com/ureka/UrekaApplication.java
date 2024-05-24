package com.ureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class UrekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrekaApplication.class, args);
		
		System.out.println("Eureka Server");
	}

}
