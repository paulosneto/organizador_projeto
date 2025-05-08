package com.project.organizer.security.service.implement;

import com.project.organizer.exceptions.ExceptionCustomError;
import com.project.organizer.security.entities.Usuario;
import com.project.organizer.security.enums.PerfilEnum;
import com.project.organizer.security.repository.UsuarioRepository;
import com.project.organizer.security.request.UsuarioJwtRequest;
import com.project.organizer.security.request.UsuarioRequest;
import com.project.organizer.security.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow(() -> new ExceptionCustomError("Email não encontrado", 404));

        return User
                .builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .authorities(mapPermissoesAutorizadas(usuario.getPerfil()))
                .build();
    }

    private static List<GrantedAuthority> mapPermissoesAutorizadas(PerfilEnum perfilEnum){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));

        return authorities;
    }


    public UserDetails autenticar(UsuarioJwtRequest usuarioJwtRequest){
        UserDetails userDetails = loadUserByUsername(usuarioJwtRequest.getEmail());
        boolean senhaValida = passwordEncoder.matches(usuarioJwtRequest.getSenha(), userDetails.getPassword());

        if(senhaValida){
            return userDetails;
        }

        throw new ExceptionCustomError("Senha invalida", 403);
    }




}
