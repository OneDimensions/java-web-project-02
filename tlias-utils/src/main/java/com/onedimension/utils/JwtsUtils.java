package com.onedimension.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtsUtils {
    private final static String SECRET_KEY = "b25lZGltZW5zaW9u";
    private final static long EXPIRATION_TIME = 3600 * 1000 * 24;

    private JwtsUtils() {
    }

    /**
     * 生成令牌
     */
    public static String generateJwt(Map<String, Object> infoMap) {

        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .addClaims(infoMap)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
        log.info("生成token: {}", token);
        return token;
    }

    /**
     * 解析令牌
     */
    public static Map<String, Object> parseJwt(String token) throws Exception {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

}
