package com.ex.handleXML;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.ex.handleXML")
@EnableScheduling
public class HandleXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandleXmlApplication.class, args);
	}
}