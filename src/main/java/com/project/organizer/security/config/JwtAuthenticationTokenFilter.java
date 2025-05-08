package com.project.organizer.security.config;

import com.project.organizer.security.service.implement.UsuarioDetailsServiceImp;
import com.project.organizer.security.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final UsuarioDetailsServiceImp userDetailsService;
    private final JwtUtils jwtUtils;

    public JwtAuthenticationTokenFilter(UsuarioDetailsServiceImp userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, FilterChain filterChain)
            throws ServletException, IOException {

        // Pega o token do cabeçalho da requisição
        String token = jwtUtils.obterToken(request);

        if (token != null && jwtUtils.validateToken(token)) {
            String username = jwtUtils.getUsernameFromToken(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Cria um objeto de autenticação com o username e o papel (role) do usuário
                User userDetails = (User) userDetailsService.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Define a autenticação no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Passa a requisição para o próximo filtro da cadeia
        filterChain.doFilter(request, response);
    }
}