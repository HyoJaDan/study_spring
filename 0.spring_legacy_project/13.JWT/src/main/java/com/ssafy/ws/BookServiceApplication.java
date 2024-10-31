package com.ssafy.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.ssafy.ws.util.JwtUtil;

@SpringBootApplication
public class BookServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

}
