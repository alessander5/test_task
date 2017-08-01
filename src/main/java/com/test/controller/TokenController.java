package com.test.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.service.security.TokenProvider;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private TokenProvider tokenProvider;

    @RequestMapping(path="", method = GET)
    public TokenResponse getTokenForLoginAndPasssword(@RequestParam String login, @RequestParam String password){
        String token = tokenProvider.generateToken(login, password).getToken();
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return tokenResponse;
    }

    @Data
    public static class TokenResponse{
        @JsonProperty
        private String token;
    }

}
