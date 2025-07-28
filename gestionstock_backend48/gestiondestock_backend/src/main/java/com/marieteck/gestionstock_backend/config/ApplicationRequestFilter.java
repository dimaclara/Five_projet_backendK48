package com.marieteck.gestionstock_backend.config;

import com.marieteck.gestionstock_backend.service.auth.CustomUserDetailsService;
import com.marieteck.gestionstock_backend.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationRequestFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    // Liste des endpoints publics qui ne nécessitent pas d'authentification
    private static final List<String> PUBLIC_ENDPOINTS = Arrays.asList(
            "/gestiondestock/v1/authenticate",
            "/gestiondestock/v1/entreprises/create",
            "/gestiondestock/v1/users/create",
            "/v3/api-docs",
            "/swagger-ui",
            "/swagger-resources",
            "/webjars",
            "/h2-console",
            "/actuator/health"
    );

    public ApplicationRequestFilter(CustomUserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

//    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestPath = request.getRequestURI();

        // Vérifier si l'endpoint est public
        boolean isPublicEndpoint = PUBLIC_ENDPOINTS.stream()
                .anyMatch(requestPath::startsWith);

        if (isPublicEndpoint) {
            // Pour les endpoints publics, passer directement au filtre suivant
            filterChain.doFilter(request, response);
            return;
        }

        // Pour les endpoints protégés, traiter l'authentification JWT
        final String authHeader = request.getHeader("Authorization");
        String userEmail = null;
        String jwt = null;
        String idEnterprise = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                userEmail = jwtUtils.extractUsername(jwt);
                idEnterprise = jwtUtils.extractIdEnterprise(jwt);
            } catch (Exception e) {
                // Token invalide, mais on laisse Spring Security gérer l'erreur
                logger.debug("Erreur lors de l'extraction du token: " + e.getMessage());
            }
        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                if (jwtUtils.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (Exception e) {
                // Utilisateur non trouvé ou erreur de validation, on laisse Spring Security gérer
                logger.debug("Erreur lors de l'authentification: " + e.getMessage());
            }
        }

        if (idEnterprise != null) {
            MDC.put("idEntreprise", idEnterprise);
        }

        filterChain.doFilter(request, response);
    }
}