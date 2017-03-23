package com.test.filter;

import com.test.filter.security.TokenAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static com.test.filter.security.SecurityConstant.SECURITY_TOKEN_KEY;
import static com.test.util.http.HttpUtil.findParamInCookies;
import static com.test.util.http.HttpUtil.findParamInHeader;
import static com.test.util.http.HttpUtil.findParamInRequest;

public class TokenAuthenticationFilter extends GenericFilterBean {

    protected AuthenticationManager authenticationManager;

    public TokenAuthenticationFilter(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        Optional<String> token = findToken(SECURITY_TOKEN_KEY, request);
        if (token.isPresent()) {
            try {
                TokenAuthentication tokenAuthentication = new TokenAuthentication(token.get());
                Authentication authentication = authenticationManager.authenticate(tokenAuthentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (AuthenticationException failed) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setHeader("ERROR", failed.getMessage());
            }
        }
        chain.doFilter(request, response);
    }

    private Optional<String> findToken(String token, HttpServletRequest request) {
        return Arrays.asList(findParamInHeader(token, request),
                             findParamInRequest(token, request),
                             findParamInCookies(token, request.getCookies()))
                                    .stream().findFirst().get();
    }

}
