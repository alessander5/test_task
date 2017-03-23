package com.test.util.http;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public abstract class HttpUtil {
    private HttpUtil(){};

    public static Optional<String> findParamInRequest(String param, HttpServletRequest request) {
        String tokenRequestValue = request.getParameter(param);
        return tokenRequestValue==null
                ? Optional.empty()
                : Optional.of(tokenRequestValue);
    }

    public static Optional<String> findParamInCookies(String param, Cookie... cookies) {
        return Arrays.stream(cookies)
                .filter(cookie -> param.equals(cookie.getName()))
                .findAny()
                .map(cookie -> cookie.getValue());
    }

    public static Optional<String> findParamInHeader(String param, HttpServletRequest request) {
        String tokenHeaderValue = request.getHeader(param);
        return tokenHeaderValue==null
                ? Optional.empty()
                : Optional.of(tokenHeaderValue);
    }

}
