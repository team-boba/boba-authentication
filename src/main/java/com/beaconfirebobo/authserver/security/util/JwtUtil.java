package com.beaconfirebobo.authserver.security.util;

import com.beaconfirebobo.authserver.constant.Constant;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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

}
