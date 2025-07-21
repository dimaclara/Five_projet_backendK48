package com.marieteck.gestionstock_backend.config;

import com.marieteck.gestionstock_backend.service.auth.CustomUserDetailsService;
import com.marieteck.gestionstock_backend.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.slf4j.MDC;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import reactor.util.annotation.NonNull;

import java.io.IOException;

@Component
public class ApplicationRequestFilter extends OncePerRequestFilter {
    // injection of customUserDetails
    private final CustomUserDetailsService userDetailsService;

    // injection of Jwts

    private final JwtUtils jwtUtils;
    public ApplicationRequestFilter(CustomUserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull  FilterChain filterChain) throws ServletException, IOException {
        // retrieve the header value

        final String authHeader = request.getHeader("Authorization");
        String userEmail = null;
        String jwt = null;
        String idEnterprise = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            userEmail = jwtUtils.extractUsername(jwt);
            idEnterprise = jwtUtils.extractIdEnterprise(jwt);
        }
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails =this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtUtils.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        MDC.put("idEntreprise", idEnterprise);
        filterChain.doFilter(request, response);
    }
}
