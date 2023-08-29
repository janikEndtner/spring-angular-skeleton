package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GreetingController {
	@GetMapping("/")
	public String greeting() {
		return "Hello World";
	}

	@GetMapping("/api/secured")
	public String secured() {
		return "logged in";
	}
}
