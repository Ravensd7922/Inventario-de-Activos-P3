package com.p3.inventarioactivos;

public class Activo {
    private String id;
    private String tipoActivo;
    private String marca;
    private String modelo;
    private String serial;
    private String responsable;

    // Constructor
    public Activo(String id, String tipoActivo, String marca, String modelo, String serial, String responsable) {
        this.id = id;
        this.tipoActivo = tipoActivo;
        this.marca = marca;
        this.modelo = modelo;
        this.serial = serial;
        this.responsable = responsable;
    }

    // Getters
    public String getId() { return id; }
    public String getTipoActivo() { return tipoActivo; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getSerial() { return serial; }
    public String getResponsable() { return responsable; }
}
