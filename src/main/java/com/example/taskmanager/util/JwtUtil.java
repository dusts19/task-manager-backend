package com.example.taskmanager.util;

import java.util.Date;
import java.security.Key;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class JwtUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	
	@Value("${jwt.secret}")
	private String secret;
//	private Key key = Keys.hmacShaKeyFor(secret.getBytes());
	private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//	private Key key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());

	public String generateToken(String username) {
		String token = Jwts.builder()
				.claim("username",username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
				.signWith(key)
				.compact();
		logger.info("Generated Token: {}", token);
		return token;
	}
//	public String generateToken(String username) {
//		return Jwts.builder()
//				.setSubject(username)
//				.setIssuedAt(new Date())
//				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
//				.signWith(key, SignatureAlgorithm.HS256)
//				.compact();
//	}
	
	public Claims extractClaims(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		logger.info("Extracted Claims: {}", claims);
		return claims;
	}
	
	public boolean validateToken(String token, String username) {
		boolean isValid = username.equals(extractClaims(token).get("username", String.class)) && !isTokenExpired(token);
		logger.info("Token Valid: {}", isValid);
		return isValid;
	}
	
	private boolean isTokenExpired(String token) {
		boolean isExpired = extractClaims(token).getExpiration().before(new Date());
		logger.info("Token Expired: {}", isExpired);
		return isExpired;
	}
}

