package com.phetolo.helloworld;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phetolo.helloworld.service.HelloService;

@RestController
public class HelloController {
	private HelloService helloService;
	
	public HelloController(HelloService helloService) {
		this.helloService = helloService;
	}
	
	@GetMapping("/hello")
	public Map<String,String> sayHello() {
		return helloService.helloCommand();
	}
	@GetMapping("/date")
	public LocalDate getDate() {
		return helloService.getDate();
	}
}
