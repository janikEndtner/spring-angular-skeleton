package com.example.demo.security;

import com.example.demo.model.UserPrincipal;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPricipalAuthenticationToken extends AbstractAuthenticationToken {

	private final UserPrincipal principal;
	public UserPricipalAuthenticationToken(UserPrincipal principal) {
		super(principal.getAuthorities());
		this.principal = principal;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}
}
