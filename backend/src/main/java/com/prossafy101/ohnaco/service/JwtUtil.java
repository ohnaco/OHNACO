package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    public final static long TOKEN_VALIDATION_SECOND = 1000L * 3;
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 60 *24 * 2;

    public final static String ACCESS_TOKEN_NAME = "accessToken";
    public final static String REFRESH_TOKEN_NAME = "refreshToken";

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    /*
    secretKey 해싱 키로 만들기
     */
    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /*
    JWT Payload에 담는 정보의 한 '조각'을 Claim이라 한다.

    Jwt Parser를 빌드하고 Parser에 토큰 넣어서 payload(body) 부분 발췌
     */
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    // 발췌한 payload에서 email 추출
    public String getEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }

    // 토큰이 만료되었는지 확인
    public Boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    // AccessToken 생성
    public String generateAccessToken(String email) {
        return generateToken(email, TOKEN_VALIDATION_SECOND);
    }

    // RefreshToken 생성
    public String generateRefreshToken(String email) {
        return generateToken(email, REFRESH_TOKEN_VALIDATION_SECOND);
    }

    // Token 생성 메소드
    public String generateToken(String email, long expireSecond) {

        Claims claims = Jwts.claims();
        claims.put("email", email);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireSecond))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    // 인증 토큰 생성
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 유효한 토큰인지 확인
    public Boolean validateToken(String token, User user) {
        String email = getEmail(token);

        return (email.equals(user.getEmail()) && !isTokenExpired(token));
    }

}
