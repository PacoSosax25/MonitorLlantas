package com.monitorllantas.repository;

import com.monitorllantas.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findActivoByNombreAndPass(String nombre, String pass);
}
