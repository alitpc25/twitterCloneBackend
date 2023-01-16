package com.alitpc25.twitterclone.configs;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	//Secret key is a random 256-bit encryption key
	private static final String SECRET_KEY = "76397924423F4528482B4D6251655468576D5A7134743777217A25432A462D4A";

	public String extractUsername(String jwtToken) {
		return extractClaim(jwtToken, Claims::getSubject); // Subject is username of the user, we chosed it so.
	}
	
	public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(jwtToken);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String jwtToken) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(jwtToken)
				.getBody();
	}
	
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		// HMAC is a specific type of message authentication code (MAC) involving a cryptographic hash function and a secret cryptographic key.
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	// To generate token without extra claims.
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<String, Object>(), userDetails);
	}

	// To generate token with extra claims.
	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername()) // Our subject for token is username.
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
		final String username = extractUsername(jwtToken);
		// We should look at UserDetails to see if the username subject equals to the username in UserDetails class provided by Spring security
		return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
	}
	
	private boolean isTokenExpired(String jwtToken) {
		return extractExpirationDate(jwtToken).before(new Date());
	}
	
	private Date  extractExpirationDate(String jwtToken) {
		return extractClaim(jwtToken, Claims::getExpiration);
	}
	
}
