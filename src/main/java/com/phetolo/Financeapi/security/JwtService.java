package com.phetolo.Financeapi.security;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private String secrete ="mysecretemysecretemysecrete";
	
	public String  generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+86400000))
				.signWith(Keys.hmacShaKeyFor(secrete.getBytes()))
				.compact();
	}
	
	public String extractUsername(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(secrete.getBytes())
				.build().parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
}
