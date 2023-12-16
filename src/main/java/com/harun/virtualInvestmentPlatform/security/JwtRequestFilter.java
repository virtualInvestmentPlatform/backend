package com.harun.virtualInvestmentPlatform.security;

import com.harun.virtualInvestmentPlatform.dao.UserRepository;
import com.harun.virtualInvestmentPlatform.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.util.Collections;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private UserRepository userRepository;

    @Autowired
    public JwtRequestFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Log the headers
        /*
        Collections.list(request.getHeaderNames()).forEach(headerName ->
                System.out.println(headerName + ": " + request.getHeader(headerName))
        );*/

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = jwtUtil.removeBearer(authorizationHeader);
            username = jwtUtil.extractEmail(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userRepository.findByEmail(username).orElse(null);
            if (user != null && jwtUtil.validateToken(jwt, user.getEmail())) {
                UserDetails userDetails = new InvestUserDetails(user);
                UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
