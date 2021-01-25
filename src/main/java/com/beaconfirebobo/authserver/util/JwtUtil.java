package com.beaconfirebobo.authserver.util;

import com.beaconfirebobo.authserver.constant.Constant;
import io.jsonwebtoken.*;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Date;

public class JwtUtil {
    public static String generateToken(String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(Constant.JWT_EXPIRE_MINUTES).toInstant()))
                .signWith(SignatureAlgorithm.HS256, Constant.SIGNING_KEY);

        return builder.compact();
    }
    public static String getSubject(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(token == null) return null;
        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
    }

    public static String getSubjectFromToken(String token, String signingKey){
        try {
            String jwtSubject = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
            return jwtSubject;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT token compact of handler are invalid.");
        }
        return null;
    }


}
