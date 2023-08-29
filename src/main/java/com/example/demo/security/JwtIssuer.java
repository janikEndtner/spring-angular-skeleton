package com.example.demo.security;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtIssuer {
	private final JwtProperties properties;
	public String issue(long userId, String email, List<Role> roles) {
		return JWT.create()
				.withSubject(String.valueOf(userId))
				.withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
				.withClaim("email", email)
				.withClaim("roles", roles.toString())
				.sign(Algorithm.HMAC256(properties.getSecretKey()));
	}
}
