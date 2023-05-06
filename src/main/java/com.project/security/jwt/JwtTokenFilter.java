package com.project.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtTokenFilter extends GenericFilter {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveAccessToken((HttpServletRequest) servletRequest);
//        Enumeration<String> headerNames = ((HttpServletRequest)servletRequest).getHeaderNames();
//////        if (headerNames != null) {
//////            while (headerNames.hasMoreElements()) {
//////                System.out.println("Header: " + ((HttpServletRequest)servletRequest).getHeader(headerNames.nextElement()));
//////            }
//////        }
        log.info("In doFilter with token: {}", token);
        String refreshToken = jwtTokenProvider.resolveRefreshToken((HttpServletRequest) servletRequest);
        log.info("In doFilter with refresh token: {}", refreshToken);
        try {
            if (token != null && jwtTokenProvider.validateAccessToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                if (authentication != null)
                    SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (JwtAuthenticationException e) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("token is expired");
        }

    }
}
