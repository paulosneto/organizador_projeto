package com.project.organizer.security.service;

import com.project.organizer.security.entities.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> findByEmail(String email);

    Usuario save(Usuario usuario);
}
