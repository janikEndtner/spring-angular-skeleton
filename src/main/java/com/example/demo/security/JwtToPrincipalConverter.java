package com.example.demo.security;

import java.util.List;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.model.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtToPrincipalConverter {
	public UserPrincipal convert(DecodedJWT jwt) {
		return UserPrincipal.builder()
				.userId(Long.parseLong(jwt.getSubject()))
				.email(jwt.getClaim("email").asString())
				.authorities(extractAuthoritiesFromClaim(jwt))
				.build();
	}

	private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt) {
		var claim = jwt.getClaim("auth");
		if (claim.isNull() || claim.isMissing()) {
			return List.of();
		}
		return claim.asList(SimpleGrantedAuthority.class);
	}
}
