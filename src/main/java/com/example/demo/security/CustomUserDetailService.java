package com.example.demo.security;

import java.util.List;

import com.example.demo.model.UserPrincipal;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

	private final UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userService.findByEmail(username).orElseThrow();
		return UserPrincipal.builder()
				.userId(user.getId())
				.email(user.getEmail())
				.authorities(List.of(new SimpleGrantedAuthority(user.getRole().toString())))
				.password(user.getPassword())
				.build();
	}
}
