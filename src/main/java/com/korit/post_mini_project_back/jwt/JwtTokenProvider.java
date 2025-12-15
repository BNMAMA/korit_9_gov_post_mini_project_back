package com.korit.post_mini_project_back.jwt;

import com.korit.post_mini_project_back.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class JwtTokenProvider {

    private final SecretKey key;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret) {
                            // Value  = yml에 있는 값을 쓸때
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

    }

    public String createAccessToken(User userEntity) {
        Date now = new Date();
        Long expiredTime = now.getTime() + (1000l * 60l * 60l * 24l);
        Date expioredDate = new Date(expiredTime);

        return Jwts.builder()
                .subject("server access Token")
                .issuer("BNMAMA")
                .issuedAt(new Date())
                .expiration(expioredDate) // 필수
                .claim("userId", userEntity.getUserId()) // 필수 (객체 형식으로 넣는 방식)
                .signWith(key, SignatureAlgorithm.HS256)// 필수 (암호키)
                .compact(); //토큰 생성
    }

    public boolean validateToken(String Token) {
        Claims claims = null;
        try {
            JwtParser jwtParser = Jwts.parser()
                    .setSigningKey(key)
                    .build();
            jwtParser.parseClaimsJws(Token);
                    // 키 감정하는 역할을 함
            return true;
        } catch (JwtException e) {
            return false;
        }

    }

    public int getUserId(String token) {
        return (int) Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getPayload()
                .get("userId");
    }
}
