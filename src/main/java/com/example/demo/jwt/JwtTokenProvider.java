package com.example.demo.jwt;

import com.example.demo.dto.CustomUserDetails;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final String JWT_SECRET = "demo_security";

    private final long JWT_EXPIRATION = 60000L * 30L;

    public String generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getSampleUserDto().getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            LOGGER.error(ex.getMessage());
        } catch (ExpiredJwtException ex) {
            LOGGER.error(ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            LOGGER.error(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error(ex.getMessage());
        } catch (SignatureException ex) {
            LOGGER.error(ex.getMessage());
        }
        return false;
    }

}
