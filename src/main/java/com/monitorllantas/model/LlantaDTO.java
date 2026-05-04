package com.monitorllantas.model;

/**
 * DTO con valores de profundidad y clase CSS de semáforo por celda.
 * Se usa tanto en la vista Thymeleaf como en la respuesta JSON del filtro AJAX.
 */
public class LlantaDTO {

    private String unidad;
    private String tipo;
    private Celda delanteroIzquierdo;
    private Celda delanteroDerecho;
    private Celda traseraIzquierdaInterior;
    private Celda traseraIzquierdaExterior;
    private Celda traseraDerechaInterior;
    private Celda traseraDerechaExterior;

    public static class Celda {
        private Double valor;
        private String color; // "rojo" | "amarillo" | "verde" | ""

        public Celda(Double valor, String color) {
            this.valor = valor;
            this.color = color;
        }

        public Double getValor() { return valor; }
        public String getColor() { return color; }
        public String getValorFormateado() {
            return (valor == null || valor == 0.0) ? "-" : String.valueOf(valor);
        }
    }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Celda getDelanteroIzquierdo() { return delanteroIzquierdo; }
    public void setDelanteroIzquierdo(Celda delanteroIzquierdo) { this.delanteroIzquierdo = delanteroIzquierdo; }

    public Celda getDelanteroDerecho() { return delanteroDerecho; }
    public void setDelanteroDerecho(Celda delanteroDerecho) { this.delanteroDerecho = delanteroDerecho; }

    public Celda getTraseraIzquierdaInterior() { return traseraIzquierdaInterior; }
    public void setTraseraIzquierdaInterior(Celda traseraIzquierdaInterior) { this.traseraIzquierdaInterior = traseraIzquierdaInterior; }

    public Celda getTraseraIzquierdaExterior() { return traseraIzquierdaExterior; }
    public void setTraseraIzquierdaExterior(Celda traseraIzquierdaExterior) { this.traseraIzquierdaExterior = traseraIzquierdaExterior; }

    public Celda getTraseraDerechaInterior() { return traseraDerechaInterior; }
    public void setTraseraDerechaInterior(Celda traseraDerechaInterior) { this.traseraDerechaInterior = traseraDerechaInterior; }

    public Celda getTraseraDerechaExterior() { return traseraDerechaExterior; }
    public void setTraseraDerechaExterior(Celda traseraDerechaExterior) { this.traseraDerechaExterior = traseraDerechaExterior; }
}
