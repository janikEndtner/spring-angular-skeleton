package com.example.demo.security;

import com.example.demo.filter.JwtAuthenticationFilter;
import com.example.demo.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final CustomUserDetailService customUserDetailService;
	@Bean
	public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		// we can disable csrf, since we are using JWT authentication
		http.csrf(AbstractHttpConfigurer::disable);

		http
				.formLogin(AbstractHttpConfigurer::disable)
				.securityMatcher("/**")
				.authorizeHttpRequests(registry -> registry
						.requestMatchers("/api/auth/login").permitAll()
						.requestMatchers("/api/admin/**").hasRole(Role.ADMIN.toString())
						.requestMatchers("/api/**").authenticated()
						.requestMatchers("/**").permitAll());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(customUserDetailService)
				.passwordEncoder(passwordEncoder())
				.and().build();
	}
}

