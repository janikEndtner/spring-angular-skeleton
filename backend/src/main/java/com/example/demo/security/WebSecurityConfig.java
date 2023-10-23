package com.example.demo.security;

import com.example.demo.filter.JwtAuthenticationFilter;
import com.example.demo.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

		configureCsrf(http);

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

	private void configureCsrf(HttpSecurity http) throws Exception {
		CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
		tokenRepository.setCookiePath("/"); // because we have /api as base path
		XorCsrfTokenRequestAttributeHandler delegate = new XorCsrfTokenRequestAttributeHandler();

		http.csrf(csrf -> csrf
				.csrfTokenRepository(tokenRepository)
				.csrfTokenRequestHandler(delegate::handle)
		);
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

