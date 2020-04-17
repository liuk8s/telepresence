package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
	@Value("${app.greeting.name:backend_Java_code}")
	private String name;
	@RequestMapping("/")
	public String index() {
		System.out.println("Received request with arg " + name);
		return "Greetings from " +name+ "!";
	}

}
