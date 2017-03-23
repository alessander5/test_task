package com.test.controller;

import com.test.service.security.TokenProvider;
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
    public String getTokenForLoginAndPasssword(@RequestParam String login, @RequestParam String password) throws Exception{
        return tokenProvider.generateToken(login, password).getToken();
    }

}
