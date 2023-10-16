package com.example.demo.filter;

import java.io.IOException;
import java.util.Optional;

import com.example.demo.security.JwtDecoder;
import com.example.demo.security.JwtToPrincipalConverter;
import com.example.demo.security.UserPricipalAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtDecoder jwtDecoder;
	private final JwtToPrincipalConverter jwtToPrincipalConverter;
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		extractTokenFromRequest(request)
				.map(jwtDecoder::decode)
				.map(jwtToPrincipalConverter::convert)
				.map(UserPricipalAuthenticationToken::new)
				.ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));

		filterChain.doFilter(request, response);
	}

	private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
		var token = request.getHeader("Authorization");
		if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			return Optional.of(token.substring(7));
		}
		return Optional.empty();
	}
}
