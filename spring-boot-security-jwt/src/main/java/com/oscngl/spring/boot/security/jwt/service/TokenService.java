package com.oscngl.spring.boot.security.jwt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TokenService {

    void refreshToken(HttpServletRequest request, HttpServletResponse response);

}
