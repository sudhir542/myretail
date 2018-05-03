package com.myretail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
* This is the main Spring Boot application class. 
* It configures Spring Boot, JPA, Swagger
*/

@SpringBootApplication
@EnableFeignClients
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class MyRetailApplication{
	
	private static final Class<MyRetailApplication> applicationClass = MyRetailApplication.class;
	private static final Logger log = LoggerFactory.getLogger(applicationClass);

    public static void main(String[] args) {
        SpringApplication.run(MyRetailApplication.class, args);
        log.info("Running products application.");
    }
}
