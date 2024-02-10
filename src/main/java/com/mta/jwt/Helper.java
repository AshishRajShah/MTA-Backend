package com.mta.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mta.entities.Admin;
import com.mta.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class Helper {
	
	 //requirement :
    public static final long JWT_TOKEN_VALIDITY = 500 * 6000 * 60000;

    public static final String SECRET = "5367566B597033abfdftdh546fhgdfgrfgre56yhtr67675t65hfghfghfghfghfghftg8576D5A71347437"; 
    
    
    //generate token for user
    public String generateToken(User usr) { 
        Map<String, Object> claims = new HashMap<>(); 
        claims.put("role", usr.getAuthorities());
        return createToken(claims, usr.getEmail()); 
    } 
    
    
  //generate token for user
    public String generateTokenForAdmin(Admin usr) { 
        Map<String, Object> claims = new HashMap<>(); 
        claims.put("role", usr.getAuthorities());
        return createToken(claims, usr.getEmail()); 
    } 
  
   
    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
	private String createToken(Map<String, Object> claims, String userName) { 
        return Jwts.builder() 
                .setClaims(claims) 
                .setSubject(userName) 
                .setIssuedAt(new Date(System.currentTimeMillis())) 
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) 
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact(); 
    } 
  
    private Key getSignKey() { 
        byte[] keyBytes= Decoders.BASE64.decode(SECRET); 
        return Keys.hmacShaKeyFor(keyBytes); 
    } 
  
    //retrieve userName from jwt token
    public String getUsernameFromToken(String token) { 
        return extractClaim(token, Claims::getSubject); 
    } 
  
    //retrieve expiration date from jwt token
    public Date extractExpiration(String token) { 
        return extractClaim(token, Claims::getExpiration); 
    } 
  
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { 
        final Claims claims = extractAllClaims(token); 
        return claimsResolver.apply(claims); 
    } 
  
    //for retrieving any information from token we will need the secret key
    private Claims extractAllClaims(String token) { 
        return Jwts 
                .parserBuilder() 
                .setSigningKey(getSignKey()) 
                .build() 
                .parseClaimsJws(token) 
                .getBody(); 
    } 
  
    //check if the token has expired
    private Boolean isTokenExpired(String token) { 
        return extractExpiration(token).before(new Date()); 
    } 
  
    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) { 
        final String username = getUsernameFromToken(token); 
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); 
    } 

}