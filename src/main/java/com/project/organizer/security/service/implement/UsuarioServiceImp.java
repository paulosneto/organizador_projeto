package com.project.organizer.security.service.implement;


import com.project.organizer.security.entities.Usuario;
import com.project.organizer.security.repository.UsuarioRepository;
import com.project.organizer.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Usuario save(Usuario usuario) {
        return null;
    }
}
