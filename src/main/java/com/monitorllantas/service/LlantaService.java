package com.monitorllantas.service;

import com.monitorllantas.model.LlantaDTO;
import com.monitorllantas.model.LlantaDTO.Celda;
import com.monitorllantas.model.LlantaRow;
import com.monitorllantas.repository.LlantaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LlantaService {

    private final LlantaRepository llantaRepository;

    public LlantaService(LlantaRepository llantaRepository) {
        this.llantaRepository = llantaRepository;
    }

    public List<LlantaDTO> obtenerTodas() {
        return llantaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<LlantaDTO> obtenerPorTipo(String tipo) {
        if (tipo == null || tipo.isBlank() || tipo.equalsIgnoreCase("TODOS")) {
            return obtenerTodas();
        }
        return llantaRepository.findByTipo(tipo).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<String> obtenerTiposDisponibles() {
        return llantaRepository.findTiposDisponibles();
    }

    // --- Reglas de semáforo ---

    /**
     * null o 0.0 -> ""        (sin color)
     * <= 3.0     -> "rojo"
     * 3.1 - 5.9  -> "amarillo"
     * >= 6.0     -> "verde"
     */
    private String calcularColor(Double valor) {
        if (valor == null || valor == 0.0) return "";
        if (valor <= 3.0) return "rojo";
        if (valor >= 6.0) return "verde";
        return "amarillo";
    }

    private Celda toCelda(Double valor) {
        return new Celda(valor, calcularColor(valor));
    }

    private LlantaDTO toDTO(LlantaRow row) {
        LlantaDTO dto = new LlantaDTO();
        dto.setUnidad(row.getUnidad());
        dto.setTipo(row.getTipo());
        dto.setDelanteroIzquierdo(toCelda(row.getDelanteroIzquierdo()));
        dto.setDelanteroDerecho(toCelda(row.getDelanteroDerecho()));
        dto.setTraseraIzquierdaInterior(toCelda(row.getTraseraIzquierdaInterior()));
        dto.setTraseraIzquierdaExterior(toCelda(row.getTraseraIzquierdaExterior()));
        dto.setTraseraDerechaInterior(toCelda(row.getTraseraDerechaInterior()));
        dto.setTraseraDerechaExterior(toCelda(row.getTraseraDerechaExterior()));
        return dto;
    }
}
