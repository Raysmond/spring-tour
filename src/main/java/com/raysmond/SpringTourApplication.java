package com.raysmond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringTourApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTourApplication.class, args);
	}
}
