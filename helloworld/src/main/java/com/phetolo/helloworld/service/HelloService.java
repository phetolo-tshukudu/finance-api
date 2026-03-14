package com.phetolo.helloworld.service;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
	
	public Map<String,String> helloCommand(){
		return Map.of("name", "Phetolo",
				"message" , "hello from springboot",
				"status", "Backend running");
	}
	
	public LocalDate getDate() {
		return LocalDate.now();
	}
}
