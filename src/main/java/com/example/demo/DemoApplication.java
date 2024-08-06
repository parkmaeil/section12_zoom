package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // web.xml -> DispatcherServlet(xml) / ContextLoaderListener(xml) : SC
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args); // Tomcat Server 구동 : 8080 -> 8081
	}

}
