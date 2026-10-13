package com.itm.sistemabancario.models;

import com.itm.sistemabancario.structures.Cola;

public class Ventanilla {
    private String nombre;
    private Cola<Cliente> fila;

    public Ventanilla(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la ventanilla no puede estar vacío");
        }
        this.nombre = nombre;
        this.fila = new Cola<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Cola<Cliente> getFila() {
        return fila;
    }
}
