package com.monitorllantas.repository;

import com.monitorllantas.model.LlantaRow;
import java.util.List;

public interface LlantaRepository {

    List<LlantaRow> findAll();

    List<LlantaRow> findByTipo(String tipo);

    List<String> findTiposDisponibles();
}
