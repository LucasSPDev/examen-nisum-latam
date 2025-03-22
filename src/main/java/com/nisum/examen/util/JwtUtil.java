package com.nisum.examen.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

import com.nisum.examen.base.Constants;

public class JwtUtil {

	public static final String SECRET_KEY = "3F9vZzR5TjJZMEV6WWdGcU5EWlVhMDF4Wm1oWmEyRjFiV2wz";  
	
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.signWith(SignatureAlgorithm.HS256,SECRET_KEY)
				.compact();
	}

	public String extractEmail(String token) {
		return getClaims(token).getSubject();
	}


	public boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}


	public boolean validateToken(String token, String email) {
		return email.equals(extractEmail(token)) && !isTokenExpired(token);
	}


	private Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
	}

}
