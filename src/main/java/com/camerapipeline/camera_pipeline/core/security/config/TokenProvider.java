package com.camerapipeline.camera_pipeline.core.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
public class TokenProvider {
    public static final String AUTHORITIES_KEY = "scopes";
    private final JwtConfig jwtConfig;
    private final Key key;

    @Autowired
    public TokenProvider(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        if (jwtConfig.getClientSecret()!=null) {
            key = Keys.hmacShaKeyFor(jwtConfig.getClientSecret().getBytes());
        } else {
            key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        }
    }

    public Optional<String> getUsernameFromToken(String token) {
        return Optional.ofNullable(getAllClaimsFromToken(token).getSubject());
    } 
    
    public Optional<String> getClaimFromToken(String token, String claim) {
        return Optional.ofNullable(getAllClaimsFromToken(token).get(claim, String.class));
    }

    public Optional<Date> getExpirationDateFromToken(String token) {
        return Optional.ofNullable(getAllClaimsFromToken(token).getExpiration());
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token)
                .map(expiration -> expiration.before(new Date()))
                .orElse(true);
    }

    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, getAuthorities(authentication))
                .signWith(key)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getTokenValidityInSeconds() * 1000))
                .compact();
    }
    
    public String generateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(subject)
                .addClaims(claims)
                .signWith(key)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getTokenValidityInSeconds() * 1000))
                .compact();
    }

    private String getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        return getUsernameFromToken(token)
                .map(username -> username.equals(userDetails.getUsername()) && !isTokenExpired(token))
                .orElse(false);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(final String token, final UserDetails userDetails) {
        final Claims claims = getAllClaimsFromToken(token);
        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }
}
