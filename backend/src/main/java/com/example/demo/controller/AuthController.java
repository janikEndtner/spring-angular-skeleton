package com.example.demo.controller;

import com.example.demo.model.LoginRequest;
import com.example.demo.model.SessionInformation;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/")
public class AuthController {

	private final AuthService authService;

	@PostMapping("login")
	public SessionInformation login(@RequestBody @Validated LoginRequest request) {
		return authService.attemptLogin(request.getEmail(), request.getPassword());
	}
}
