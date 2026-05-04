package com.monitorllantas.dao;

import com.monitorllantas.model.LlantaRow;
import com.monitorllantas.repository.LlantaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LlantaDao implements LlantaRepository {

    private final JdbcTemplate jdbc;

    private static final String SQL_BASE =
            "WITH RankedData AS ( " +
            "    SELECT [UNIDAD], [POSICION], [PROFUNDIDAD_ACTUAL], " +
            "           ROW_NUMBER() OVER (PARTITION BY [UNIDAD], [POSICION] ORDER BY [FECHA_REVISION] DESC) AS RowNum " +
            "    FROM [LLANTAS] " +
            "    WHERE [POSICION] IN ('DI', 'DD', 'TDE', 'TDI', 'TIE', 'TII') " +
            ") " +
            "SELECT r.[UNIDAD], a.TIPO, " +
            "    MAX(CASE WHEN r.[POSICION] = 'DI'  THEN r.[PROFUNDIDAD_ACTUAL] END) AS DI, " +
            "    MAX(CASE WHEN r.[POSICION] = 'DD'  THEN r.[PROFUNDIDAD_ACTUAL] END) AS DD, " +
            "    MAX(CASE WHEN r.[POSICION] = 'TII' THEN r.[PROFUNDIDAD_ACTUAL] END) AS TII, " +
            "    MAX(CASE WHEN r.[POSICION] = 'TIE' THEN r.[PROFUNDIDAD_ACTUAL] END) AS TIE, " +
            "    MAX(CASE WHEN r.[POSICION] = 'TDI' THEN r.[PROFUNDIDAD_ACTUAL] END) AS TDI, " +
            "    MAX(CASE WHEN r.[POSICION] = 'TDE' THEN r.[PROFUNDIDAD_ACTUAL] END) AS TDE " +
            "FROM RankedData r " +
            "INNER JOIN AUTOBUS a ON r.UNIDAD = a.ECO " +
            "WHERE r.RowNum = 1 ";

    private static final String SQL_ALL       = SQL_BASE + "GROUP BY r.[UNIDAD], a.TIPO ORDER BY r.[UNIDAD]";
    private static final String SQL_BY_TIPO   = SQL_BASE + "AND a.TIPO = ? GROUP BY r.[UNIDAD], a.TIPO ORDER BY r.[UNIDAD]";
    private static final String SQL_TIPOS     = "SELECT DISTINCT TIPO FROM AUTOBUS WHERE TIPO IS NOT NULL ORDER BY TIPO";

    private static final RowMapper<LlantaRow> MAPPER = (rs, rowNum) -> {
        LlantaRow row = new LlantaRow();
        row.setUnidad(rs.getString("UNIDAD"));
        row.setTipo(rs.getString("TIPO"));
        row.setDelanteroIzquierdo(toDouble(rs.getObject("DI")));
        row.setDelanteroDerecho(toDouble(rs.getObject("DD")));
        row.setTraseraIzquierdaInterior(toDouble(rs.getObject("TII")));
        row.setTraseraIzquierdaExterior(toDouble(rs.getObject("TIE")));
        row.setTraseraDerechaInterior(toDouble(rs.getObject("TDI")));
        row.setTraseraDerechaExterior(toDouble(rs.getObject("TDE")));
        return row;
    };

    public LlantaDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<LlantaRow> findAll() {
        return jdbc.query(SQL_ALL, MAPPER);
    }

    @Override
    public List<LlantaRow> findByTipo(String tipo) {
        return jdbc.query(SQL_BY_TIPO, MAPPER, tipo);
    }

    @Override
    public List<String> findTiposDisponibles() {
        return jdbc.queryForList(SQL_TIPOS, String.class);
    }

    private static Double toDouble(Object value) {
        if (value == null) return null;
        return ((Number) value).doubleValue();
    }
}
