package com.test.service.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.HashMap;
import java.util.Map;

public final class TokenBuilder {
    private String securityKey;
    private Map<String, Object> tokenAttributes = new HashMap<>();

    public TokenBuilder(String securityKey) {
        this.securityKey = securityKey;
    }

    public TokenBuilder addAttribute(String key, Object value){
        tokenAttributes.put(key, value);
        return this;
    }

    public String buildToken(){
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(tokenAttributes);
        return jwtBuilder.signWith(SignatureAlgorithm.HS256, securityKey).compact();
    }
}