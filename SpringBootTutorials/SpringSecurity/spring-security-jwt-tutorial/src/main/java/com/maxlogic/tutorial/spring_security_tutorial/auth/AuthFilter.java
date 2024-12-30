package com.maxlogic.tutorial.spring_security_tutorial.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.maxlogic.tutorial.spring_security_tutorial.auth.jwt.JwtTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        String token = extractToken(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            String userName = jwtTokenProvider.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7); // Extract JWT token
        }
        return null;
    }
}
