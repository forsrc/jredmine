package com.forsrc.jredmine.server.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forsrc.jredmine.server.dao.UserDetailsDao;
import com.forsrc.jredmine.server.model.UserDetails;
import com.forsrc.jredmine.server.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Configuration
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	 
		// System.out.println("Authentication: " + authentication);
		
		
		// Get authorization header and validate
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}

		// Get jwt token and validate
		final String token = header.split(" ")[1].trim();

		try {
			JwtTokenUtil.validate(token);
		} catch (Exception e) {
			Map<String, String> message = new HashMap<>(3);
			message.put("status", HttpStatus.FORBIDDEN.toString());
			message.put("message", e.getMessage());
			message.put("exception", e.getClass().getName());
			response.getWriter().write(objectMapper.writeValueAsString(message));
			// chain.doFilter(request, response);
			return;
		}

		// authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {

			// Get user identity and set it on the spring security context
			UserDetails userDetails = userDetailsDao.findById(JwtTokenUtil.getUsername(token)).orElse(null);

			if (token.equals(userDetails.getJwtToken())) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				chain.doFilter(request, response);
				return;
			}
			response.setStatus(HttpStatus.FORBIDDEN.value());
			Map<String, String> message = new HashMap<>(2);
			message.put("status", HttpStatus.FORBIDDEN.toString());
			message.put("message", "Jwt token is not match.");
			response.getWriter().write(objectMapper.writeValueAsString(message));
			//chain.doFilter(request, response);

			return;
		}
		String username = JwtTokenUtil.getUsername(token);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		if (!userDetails.getUsername().equals(username)) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			Map<String, String> message = new HashMap<>(2);
			message.put("status", HttpStatus.FORBIDDEN.toString());
			message.put("message", "User not login");
			response.getWriter().write(objectMapper.writeValueAsString(message));
			//chain.doFilter(request, response);
			return;
		}

		chain.doFilter(request, response);
	}

}