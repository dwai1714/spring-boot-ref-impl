package com.das.liquor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.audit.client"})


public class App 
{
    public static void main( String[] args )
    {
		System.out.println("Inside Main Class");
		SpringApplication.run(App.class, args);
    }
}

