package com.monitorllantas.model;

/**
 * Resultado crudo de la consulta SQL antes de aplicar lógica de semáforo.
 */
public class LlantaRow {

    private String unidad;
    private String tipo;
    private Double delanteroIzquierdo;
    private Double delanteroDerecho;
    private Double traseraIzquierdaInterior;
    private Double traseraIzquierdaExterior;
    private Double traseraDerechaInterior;
    private Double traseraDerechaExterior;

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Double getDelanteroIzquierdo() { return delanteroIzquierdo; }
    public void setDelanteroIzquierdo(Double delanteroIzquierdo) { this.delanteroIzquierdo = delanteroIzquierdo; }

    public Double getDelanteroDerecho() { return delanteroDerecho; }
    public void setDelanteroDerecho(Double delanteroDerecho) { this.delanteroDerecho = delanteroDerecho; }

    public Double getTraseraIzquierdaInterior() { return traseraIzquierdaInterior; }
    public void setTraseraIzquierdaInterior(Double traseraIzquierdaInterior) { this.traseraIzquierdaInterior = traseraIzquierdaInterior; }

    public Double getTraseraIzquierdaExterior() { return traseraIzquierdaExterior; }
    public void setTraseraIzquierdaExterior(Double traseraIzquierdaExterior) { this.traseraIzquierdaExterior = traseraIzquierdaExterior; }

    public Double getTraseraDerechaInterior() { return traseraDerechaInterior; }
    public void setTraseraDerechaInterior(Double traseraDerechaInterior) { this.traseraDerechaInterior = traseraDerechaInterior; }

    public Double getTraseraDerechaExterior() { return traseraDerechaExterior; }
    public void setTraseraDerechaExterior(Double traseraDerechaExterior) { this.traseraDerechaExterior = traseraDerechaExterior; }
}
