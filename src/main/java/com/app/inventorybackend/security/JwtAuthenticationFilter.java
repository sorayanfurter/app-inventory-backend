package com.app.inventorybackend.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    JwtService jwtService;
    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String userEmail = null;
        String jwtToken = null;


        if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ") ){
            filterChain.doFilter(request, response);
            return;
        }


        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);


            try {
                userEmail = jwtService.extractUsername(jwtToken);
            } catch (ExpiredJwtException exception) {
                System.out.println("El token ha expirado");
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            System.out.println("Token invalido , no empieza con bearer string");
        }


        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } else {
            System.out.println("El token no es valido");
        }
        filterChain.doFilter(request, response);
    }
}



