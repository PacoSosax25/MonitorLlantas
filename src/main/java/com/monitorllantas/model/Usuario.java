package com.monitorllantas.model;

public class Usuario {

    private String nombre;
    private String pass;
    private String estado;
    private String nombreU;
    private String apellidoP;
    private String apellidoM;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getNombreU() { return nombreU; }
    public void setNombreU(String nombreU) { this.nombreU = nombreU; }

    public String getApellidoP() { return apellidoP; }
    public void setApellidoP(String apellidoP) { this.apellidoP = apellidoP; }

    public String getApellidoM() { return apellidoM; }
    public void setApellidoM(String apellidoM) { this.apellidoM = apellidoM; }

    public String getNombreCompleto() {
        return (nombreU != null ? nombreU : "") + " "
             + (apellidoP != null ? apellidoP : "") + " "
             + (apellidoM != null ? apellidoM : "");
    }
}
