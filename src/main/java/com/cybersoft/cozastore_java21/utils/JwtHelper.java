package com.cybersoft.cozastore_java21.utils;


import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {
	//Lấy key được lưu trữ trong application.properties:
	@Value("${jwt.secret.key}")
	private String secretKey;
	
	//Sinh token dựa trên key được lưu trữ trên apllication.properties
	public String generateToken(String data) {
		Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
		String token = Jwts.builder().setSubject(data)
				.signWith(key)
				.compact();
		return token;
	}
	
	//Giải mã Token
	public Claims decodeToken(String token) {
		Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
		Claims claims = Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
		return claims;
	}
}
