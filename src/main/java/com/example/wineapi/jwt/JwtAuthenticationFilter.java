package com.example.wineapi.jwt;


import lombok.RequiredArgsConstructor;
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
        System.out.println("필터실행@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");


        String token = jwtAuthenticationProvider.resolveToken(request);
        if(token != null && jwtAuthenticationProvider.validateToken(token)){
            System.out.println("인증 성공");
            Authentication authentication = jwtAuthenticationProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        else if(token != null && !jwtAuthenticationProvider.validateToken(token)) {
            throw new InvalidParameterException("유효하지 않은 토큰입니다");
        }
        else if(token == null && request.getMethod()=="GET") {
            throw new NullPointerException("토큰이 없습니다.");
        }

        filterChain.doFilter(request, response);
    }
}
