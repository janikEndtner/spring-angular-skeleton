package com.example.demo.service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.example.demo.model.Role;
import com.example.demo.model.SessionInformation;
import com.example.demo.model.UserPrincipal;
import com.example.demo.security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtIssuer jwtIssuer;
	private final AuthenticationManager authenticationManager;

	public SessionInformation attemptLogin(String email, String password) {
		var authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, password)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		var principal = (UserPrincipal) authentication.getPrincipal();
		var roles = principal.getAuthorities().stream()
				.map(grantedAuthority -> Role.valueOf(grantedAuthority.getAuthority()))
				.toList();
		var expiresAt = Instant.now().plus(Duration.of(1, ChronoUnit.DAYS));
		var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles, expiresAt);
		return SessionInformation.builder()
				.idToken(token)
				.expiresAt(String.valueOf(expiresAt))
				.build();
	}
}
