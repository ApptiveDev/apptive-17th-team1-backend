package com.example.wineapi.jwt;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidParameterException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    public JwtAuthenticationFilter(JwtAuthenticationProvider provider) {
        jwtAuthenticationProvider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtAuthenticationProvider.resolveToken(request);

        if(token != null && jwtAuthenticationProvider.validateToken(token)){
            System.out.println("인증 성공");
            Authentication authentication = jwtAuthenticationProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        else if(token != null && !jwtAuthenticationProvider.validateToken(token)) {
            request.setAttribute("exception", HttpStatus.BAD_REQUEST);
            //throw new InvalidParameterException("유효하지 않은 토큰입니다");
        }

        filterChain.doFilter(request, response);
    }
}
