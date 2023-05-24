package com.production.hrow.helper;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            final String full_token = request.getHeader("Authorization");
            System.out.println(full_token);
            String username=null;
            String jwtToken=null;

            if(full_token!=null && full_token.startsWith("Bearer "))
            {
                jwtToken = full_token.substring(7);
                try {
                    username = jwtUtil.extractUsername(jwtToken);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println("Invalid Token");
            }

            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());

                usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                if(jwtUtil.validateToken(jwtToken, userDetails))
                {
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
                }
            }
            
            filterChain.doFilter(request, response);
            
    }
    
}
