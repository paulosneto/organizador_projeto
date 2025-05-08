package com.project.organizer.security.config;

import com.project.organizer.security.service.implement.UsuarioDetailsServiceImp;
import com.project.organizer.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {


    @Autowired
    private UsuarioDetailsServiceImp userDetailsServiceImp;

    @Autowired
    private JwtUtils jwtUtils;

    // Define o filtro que vai interceptar as requisições e validar o JWT
    @Bean
    public OncePerRequestFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter(this.userDetailsServiceImp, this.jwtUtils);
    }

   /* @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/usuario/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/usuario/**").permitAll()  // Exemplo de endpoint público
                .anyRequest().authenticated()           // Requer autenticação para os demais
                .and()
                .formLogin()                             // Permite autenticação via formulário
                .and()
                .httpBasic();                            // Autenticação básica, se necessário

        return http.build();
    }*/


    // Cria o PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configura o filtro de segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       /* httpSecurity
                .csrf().disable()  // Desabilita CSRF (ideal para APIs REST)
                .authorizeRequests()
                .requestMatchers("/usuario/**").permitAll() // Permite acesso livre para /usuario/**
                .anyRequest().authenticated() // Requer autenticação para outras rotas
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sessão sem estado
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class); // Adiciona o filtro JWT

        return httpSecurity.build(); // Retorna a configuração do filtro de segurança*/


        httpSecurity.csrf(csrf -> csrf.disable() )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/usuario/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
