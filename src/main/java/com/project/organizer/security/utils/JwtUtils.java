package com.project.organizer.security.utils;


import com.project.organizer.security.request.UsuarioJwtRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.ValueGenerationType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;


    private Date gerarDataExpiracao(){
        return new Date(System.currentTimeMillis() + expiration * 68000);
    }

    public String obterToken(UsuarioJwtRequest usuarioJwtRequest){
        return Jwts.builder()
                .setSubject(usuarioJwtRequest.getEmail())
                .setExpiration(gerarDataExpiracao())
                .signWith(SignatureAlgorithm.ES256, secret)
                .compact();
    }

    // Cria os claims a partir do token
    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJwt(token)
                .getBody();
    }

    // Retorna a data de expiração
    public Date getDataExpiracao(String token){
        Claims claims = getClaims(token);
        return  claims.getExpiration();
    }

    // Verifica se o token está expirado
    public boolean isTokenExpirado(String token){
        final Date dataExpiracao = getDataExpiracao(token);
        return dataExpiracao.before(new Date());
    }

    // Retorna se o token está valido ou não
    public boolean isTokenValido(String token){
        return !isTokenExpirado(token);
    }
}
