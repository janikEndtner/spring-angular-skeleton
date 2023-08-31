package com.example.demo.controller;

import com.example.demo.model.LoginRequest;
import com.example.demo.model.LoginResponse;
import com.example.demo.model.Role;
import com.example.demo.model.UserPrincipal;
import com.example.demo.security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final JwtIssuer jwtIssuer;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/api/auth/login")
	public LoginResponse login(@RequestBody @Validated LoginRequest request) {
		var authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		var principal = (UserPrincipal) authentication.getPrincipal();
		var roles = principal.getAuthorities().stream()
				.map(grantedAuthority -> Role.valueOf(grantedAuthority.getAuthority()))
				.toList();
		var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);
		return LoginResponse.builder()
				.accessToken(token)
				.build();
	}
}
