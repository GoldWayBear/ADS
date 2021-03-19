/**
 * 
 */
package com.revature.auth.jwts;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.revature.auth.service.UserDetailsInDAS;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author Jinwei Xiong
 *
 */
@Component
public class JSONWebTokens {
	private static final Logger LOG = LogManager.getFormatterLogger(JSONWebTokens.class);
	@Value("${DAS.app.jwtSecretKey}")
	private String jwtSecret;
	@Value("${DAS.app.jwtExpiration}")
	private int jwtExpirationMs;
	
	public String generateJwtToken(Authentication authentication) {
		UserDetailsInDAS userPrincipal = (UserDetailsInDAS)authentication.getPrincipal();
		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime()+jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512,jwtSecret)
				.compact();
	}
	
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			//System.out.println("In JSON web token:");
			//System.out.println(authToken);
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		}catch(SignatureException e) {
			LOG.error("Invalid JWT signature: {}", e.getMessage());
		}catch(MalformedJwtException e) {
			LOG.error("Invalid JWT token: {}", e.getMessage());
		}catch(ExpiredJwtException e) {
			LOG.error("JWT token is expired: {}", e.getMessage());
		}catch(UnsupportedJwtException e) {
			LOG.error("JWT token is unsupported: {}", e.getMessage());
		}catch(IllegalArgumentException e) {
			LOG.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
	
}
