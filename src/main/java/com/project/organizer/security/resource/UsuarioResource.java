package com.project.organizer.security.resource;

import com.project.organizer.security.entities.Usuario;
import com.project.organizer.security.request.UsuarioJwtRequest;
import com.project.organizer.security.request.UsuarioRequest;
import com.project.organizer.security.response.UsuarioJwtResponse;
import com.project.organizer.security.response.UsuarioResponse;
import com.project.organizer.security.service.UsuarioService;
import com.project.organizer.security.service.implement.UsuarioDetailsServiceImp;
import com.project.organizer.security.utils.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.PasswordAuthentication;

@Log4j2
@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    private final UsuarioDetailsServiceImp usuarioDetailsServiceImp;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse save(@RequestBody @Valid UsuarioRequest usuarioRequest){

        // Cria a instancia do usuario
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioRequest, usuario);

        //Seta a senha do usuario
        usuario.setSenha(passwordEncoder.encode(usuarioRequest.getSenha()));
        usuarioService.save(usuario);

        //Instancia a response do usuário
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        BeanUtils.copyProperties(usuario, usuarioResponse);

        return usuarioResponse;
    }



    @PostMapping("/autenticacao")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioJwtResponse getAutenticacao(@RequestBody UsuarioJwtRequest usuarioJwtRequest){
        try{
            UserDetails userDetails = usuarioDetailsServiceImp.autenticar(usuarioJwtRequest);
            String token = jwtUtils.obterToken(usuarioJwtRequest);
            return UsuarioJwtResponse.builder()
                    .email(userDetails.getUsername())
                    .token(token)
                    .build();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }



}
