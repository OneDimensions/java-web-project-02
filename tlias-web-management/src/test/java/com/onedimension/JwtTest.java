package com.onedimension;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtTest {
    private final String secret = "b25lZGltZW5zaW9u";

    /**
     * 生成jwt令牌
     */
    @Test
    public void testGenerateJwt() {

        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("username", "admin");

        // 使用jwt构建token
        String token = Jwts.builder()
                // 指定加密算法, 密钥
                .signWith(SignatureAlgorithm.HS256, secret)
                // 添加自定义信息
                .addClaims(map)
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + 1000))
                // 生成token
                .compact();

        System.out.println(token);
    }

    /**
     * 解析jwt
     */
    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc1NDQ0ODY3Mn0.mxWaeBU9-JEChwGJwH4DxT4BmTw4mseVfCAGuPwOGgY";

        try {
            // 解析token
            Claims claims = Jwts.parser()
                    // 设置密钥
                    .setSigningKey(secret)
                    // 解析token
                    .parseClaimsJws(token)
                    // 获取自定义信息 {id=1, username=admin, exp=1754451767}
                    .getBody();
            System.out.println(claims);
        } catch (ExpiredJwtException e) {
            log.info("token已过期", e);
        }
    }

}
