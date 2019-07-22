package com.example.ud4_ejemplo5;

public class Vehiculo {
    private String nombre; // Nombre del vehículo.
    private String aparicion; // Serie o película donde aparece.

    public Vehiculo(String nombre, String aparicion) {
        this.nombre = nombre;
        this.aparicion = aparicion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAparicion() {
        return aparicion;
    }
}

