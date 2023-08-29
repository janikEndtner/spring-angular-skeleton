package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.LoginRequest;
import com.example.demo.model.LoginResponse;
import com.example.demo.model.Role;
import com.example.demo.security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final JwtIssuer jwtIssuer;
	@PostMapping("/api/auth/login")
	public LoginResponse login(@RequestBody @Validated LoginRequest request) {
		var token = jwtIssuer.issue(1L, request.getEmail(), List.of(Role.USER));
		return LoginResponse.builder()
				.accessToken(token)
				.build();
	}
}
