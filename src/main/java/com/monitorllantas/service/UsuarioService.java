package com.monitorllantas.service;

import com.monitorllantas.model.Usuario;
import com.monitorllantas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> autenticar(String nombre, String pass) {
        return usuarioRepository.findActivoByNombreAndPass(nombre, pass);
    }
}
