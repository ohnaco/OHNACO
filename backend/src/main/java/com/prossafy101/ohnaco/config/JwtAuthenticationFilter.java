package com.prossafy101.ohnaco.config;

import com.prossafy101.ohnaco.service.JwtUtil;
import com.prossafy101.ohnaco.service.RedisUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, RedisUtil redisUtil) {
        this.jwtUtil = jwtUtil;
        this.redisUtil = redisUtil;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        String token = req.getHeader("Authorization");
        String email = null;
        String refreshToken = null;

        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                logger.info("token : " + token);
                email = jwtUtil.getEmail(token);
                Authentication authentication = jwtUtil.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch(ExpiredJwtException e) {
            email = e.getClaims().get("email", String.class);
            logger.info("email : " + email);
            refreshToken = redisUtil.getData(email);
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            if (refreshToken != null) {
                if (email.equals(jwtUtil.getEmail(refreshToken))) {
                    String newToken = jwtUtil.generateAccessToken(jwtUtil.getUserid(refreshToken), email);
                    Authentication authentication = jwtUtil.getAuthentication(newToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    HttpServletResponse response = (HttpServletResponse) servletResponse;
                    response.addHeader("Authorization", "Bearer " + newToken);
                }
            }
        } catch(ExpiredJwtException e) {

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
