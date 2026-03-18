package com.example;

import com.example.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JWTTest {

    @Test
    public void test1() {
        //生成JWT
        Map<String, Object> dataMap=new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");
        String JWT = Jwts.builder().signWith(SignatureAlgorithm.HS256, "dGhpcyBhIGp3dFRlc3Q=")//指定加密算法,和秘钥
                .addClaims(dataMap).
                setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)).compact();
        System.out.println(JWT);
    }

    @Test
    public  void testParseJWT(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "admin");

        String token = JwtUtils.generateJwt(claims);
        Claims body = JwtUtils.parseJwt(token);

        assertEquals(1, body.get("id"));
        assertEquals("admin", body.get("username"));
        System.out.println("body = " + body);
    }
}
