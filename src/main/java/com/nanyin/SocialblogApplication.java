package com.nanyin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SocialblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialblogApplication.class, args);
	}
}
