package com.alitpc25.twitterclone.configs;

import java.io.IOException;
import java.util.Date;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	
	public JwtAuthenticationFilter(JwtService jwtService, @Lazy UserDetailsService userDetailsService) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwtToken;
		final String username;
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response); // In case jwt is not valid, return.
			return;
		}
		
		try {
			//Extract the token from the header
			jwtToken = authHeader.substring(7);
			
			//To make sure that user exist, we should check it in UserDetailsService. So we need the user's data from jwtToken. Therefore
			//first, we should extract the username from jwt token.
			username = jwtService.extractUsername(jwtToken);
			
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				//We write own implementation of loadByUsername since we will fetch the user from our database.
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				if(jwtService.isTokenValid(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		} catch(ExpiredJwtException e) {
			// Token is expired, logout user.
			filterChain.doFilter(request, response);
			return;
		}
		filterChain.doFilter(request, response);
	}
	
}
