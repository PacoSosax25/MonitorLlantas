package com.monitorllantas.dao;

import com.monitorllantas.model.Usuario;
import com.monitorllantas.repository.UsuarioRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDao implements UsuarioRepository {

    private final JdbcTemplate jdbc;

    private static final String SQL_FIND_ACTIVO =
            "SELECT NOMBRE, PASS, ESTADO, NOMBRE_U, APELLIDO_P, APELLIDO_M " +
            "FROM USUARIO " +
            "WHERE NOMBRE = ? AND PASS = ? AND ESTADO = 'ACTIVO'";

    private static final RowMapper<Usuario> MAPPER = (rs, rowNum) -> {
        Usuario u = new Usuario();
        u.setNombre(rs.getString("NOMBRE"));
        u.setPass(rs.getString("PASS"));
        u.setEstado(rs.getString("ESTADO"));
        u.setNombreU(rs.getString("NOMBRE_U"));
        u.setApellidoP(rs.getString("APELLIDO_P"));
        u.setApellidoM(rs.getString("APELLIDO_M"));
        return u;
    };

    public UsuarioDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<Usuario> findActivoByNombreAndPass(String nombre, String pass) {
        List<Usuario> result = jdbc.query(SQL_FIND_ACTIVO, MAPPER, nombre, pass);
        return result.stream().findFirst();
    }
}
