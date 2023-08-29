package com.example.demo.security;

import java.util.Collection;

import com.example.demo.model.UserPricipal;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserPricipalAuthenticationToken extends AbstractAuthenticationToken {

	private final UserPricipal principal;
	public UserPricipalAuthenticationToken(UserPricipal principal) {
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
