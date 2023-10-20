package com.example.demo.security;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.entity.UserRole;
import com.example.demo.model.UserPrincipal;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
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
				.authorities(userRolesToAuthority(user.getRoles()))
				.password(user.getPassword())
				.build();
	}

	@NonNull
	private List<SimpleGrantedAuthority> userRolesToAuthority(@NonNull Set<UserRole> userRoles) {
		return userRoles.stream().map(r -> new SimpleGrantedAuthority(r.getRole().toString())).collect(Collectors.toList());
	}
}
