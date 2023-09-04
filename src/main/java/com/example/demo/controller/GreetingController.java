package com.example.demo.controller;

import com.example.demo.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	public String secured(@AuthenticationPrincipal UserPrincipal pricipal) {
		return "logged in as " + pricipal.getEmail() + " User ID: "
				+ pricipal.getUserId();
	}

	@GetMapping("/api/admin")
	public String admin(@AuthenticationPrincipal UserPrincipal principal) {
		return "if you see this, you are an admin " + principal.getUserId();
	}
}
