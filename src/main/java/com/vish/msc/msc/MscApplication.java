package com.vish.msc.msc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.vish.msc" })
public class MscApplication {
	public static void main(String[] args) {
		SpringApplication.run(MscApplication.class, args);
	}
}
