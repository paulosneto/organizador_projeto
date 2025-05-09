package com.project.organizer.security.controllers;

import com.project.organizer.security.domain.Users;
import com.project.organizer.security.dto.LoginRequestDTO;
import com.project.organizer.security.dto.RegisterResponseDTO;
import com.project.organizer.security.dto.ResponseDTO;
import com.project.organizer.security.infra.security.TokenService;
import com.project.organizer.security.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthContoller {

    private final UsersRepository usersRepository;
    private  final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    private ResponseEntity login(@RequestBody LoginRequestDTO body){

        Users users = usersRepository.findByEmail(body.email()).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));

        if(passwordEncoder.matches(body.password(), users.getPassword())){
                String token = this.tokenService.generateToken(users);
                return ResponseEntity.ok().body(new ResponseDTO(users.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    private ResponseEntity register(@RequestBody RegisterResponseDTO body){
        // busca o usuario informado pra ver se existe um cadastro
        Optional<Users> users = this.usersRepository.findByEmail(body.email());
        // Se houver cadastro para o login informado, devolve o token, caso nao exista o usuário,
        if(users.isEmpty()){
            Users nUser = new Users();
            nUser.setPassword(passwordEncoder.encode(body.password()));
            nUser.setEmail(body.email());
            nUser.setName(body.name());
            this.usersRepository.save(nUser);

            String token = this.tokenService.generateToken(nUser);
            return ResponseEntity.ok().body(new ResponseDTO(nUser.getName(), token));
        }

        return ResponseEntity.badRequest().build();
    }

}

