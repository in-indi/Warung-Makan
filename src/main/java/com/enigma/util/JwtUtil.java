package com.enigma.util;

import com.enigma.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JwtUtil {

    @Value("${JWT_SECRET}")
    private String secret;

    @Value("${JWT_EXPIRATION}")
    private Integer jwtExpiration;

    public String generateToken(String subject){
        System.out.println(subject);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,secret)
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .compact();
    }

    public Boolean validateToken(String token){
        try {
            if(token == null){
                throw new UnauthorizedException("Token empty");
            }
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException e){
            throw new UnauthorizedException("Expired Token");
        }catch (UnsupportedJwtException e){
            throw new UnauthorizedException("Token unsupported");
        }catch (MalformedJwtException e){
            throw new UnauthorizedException("Token malformed");
        }catch (SignatureException e){
            throw new UnauthorizedException("Signature unknown");
        }catch (IllegalArgumentException e){
            throw new UnauthorizedException("Token invalid");
        }
    }
}
