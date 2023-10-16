package com.example.demo.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * This filter redirects all requests to index.html because routing should be made in front end.
 * Exceptions are routes starting with /api and requests to a static file that already exist.
 */
@Component
@RequiredArgsConstructor
public class RedirectToFrontendFilter extends OncePerRequestFilter {

	private final ResourceLoader resourceLoader;
	private static final String API_PREFIX = "/api";

	@Override
	protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
			throws ServletException, IOException {
		// Check if the request URI starts with "/api"
		if (request.getRequestURI().startsWith(API_PREFIX)) {
			// If it starts with "/api", let it pass through
			filterChain.doFilter(request, response);
			return;
		}

		// check if resource exist, if yes, pass it trough (e.g. /styles.css)
		var resource = resourceLoader.getResource("classpath:/static" + request.getRequestURI());
		if (resource.exists()) {
			filterChain.doFilter(request, response);
			return;
		}

		// write index.html to response, routes are handeled in front end in this case
		var angularIndexPage = resourceLoader.getResource("classpath:/static/index.html");
		var content = angularIndexPage.getContentAsByteArray();
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.TEXT_HTML_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		response.getOutputStream().write(content);
	}
}
