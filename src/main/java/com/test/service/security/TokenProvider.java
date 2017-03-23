package com.test.service.security;

import com.test.filter.security.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import java.util.Collection;

import static org.springframework.util.StringUtils.isEmpty;

@Component
public class TokenProvider {

    @Autowired
    private UserDetailsService userService;
    @Value("${application.security.key}")
    private String securityKey;

    public TokenAuthentication generateToken(String login, String password) throws Exception {
        if (isEmpty(login) || isEmpty(password)){
            throw new AuthenticationException("Can't generate token"){};
        }
        User user = (User)userService.loadUserByUsername(login);
        if(!checkPassword(user, password)){
            throw new AuthenticationException("Authentication error"){};
        }
        return buildFullTokenAuthentication(user, buildToken(user));
    }

    private String buildToken(User user){
        return new TokenBuilder(securityKey)
                .addAttribute("login", user.getUsername())
                .buildToken();
    }

    private boolean checkPassword(User user, String password) {
        return password.equals(user.getPassword());
    }

    private TokenAuthentication buildFullTokenAuthentication(User user, String token) {
        if (!user.isEnabled()) {
            throw new AuthenticationException("User disabled"){};
        }
        if(isEmpty(token)){
            throw new AuthenticationException("Can't create token"){};
        }
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        return new TokenAuthentication(token, authorities, true, user);
    }
}