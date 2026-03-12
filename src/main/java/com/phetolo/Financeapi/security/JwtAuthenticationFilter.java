package com.phetolo.Financeapi.security;

import java.util.List;
import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	
	




	public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}






	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain)
	        throws ServletException, IOException {
		System.out.println("Perfoming filter...");
	    if (request.getServletPath().contains("/auth")) {
	    	System.out.println("Perfoming filter... in the if ");
	        filterChain.doFilter(request, response);
	        return;
	    }
	    System.out.println("Perfoming filter...: after the if");
	    String authHeader = request.getHeader("Authorization");

	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        filterChain.doFilter(request, response);
	        return;
	    }

	    String token = authHeader.substring(7);
	    System.out.println(token);
	    String username = jwtService.extractUsername(token);

	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	    	System.out.println("loading.");
	        if (jwtService.isTokenValid(token)) {
	        	UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	        	System.out.println("loading..");
	        	UsernamePasswordAuthenticationToken authentication =
	                    new UsernamePasswordAuthenticationToken(
	                    		userDetails,
	                            null,
	                            userDetails.getAuthorities()
	                    );
	        	System.out.println("loading...");
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        }
	    }

	    filterChain.doFilter(request, response);
	}

}
