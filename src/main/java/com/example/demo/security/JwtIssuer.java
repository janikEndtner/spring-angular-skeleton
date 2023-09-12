package com.example.demo.security;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtIssuer {
	private final JwtProperties properties;
	public String issue(long userId, String email, List<Role> roles, Instant expiresAt) {
		return JWT.create()
				.withSubject(String.valueOf(userId))
				.withExpiresAt(expiresAt)
				.withClaim("email", email)
				.withClaim("roles", roles.stream().map(Role::toUserPrincipalRole).collect(Collectors.toList()))
				.sign(Algorithm.HMAC256(properties.getSecretKey()));
	}
}
