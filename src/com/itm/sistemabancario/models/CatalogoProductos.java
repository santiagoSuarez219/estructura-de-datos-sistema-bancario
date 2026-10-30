package com.itm.sistemabancario.models;

import com.itm.sistemabancario.structures.ListaDoble;

public class CatalogoProductos {
    private String nombre;
    private ListaDoble<ProductoBancario> productos;

    public CatalogoProductos(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del catálogo no puede estar vacío");
        }
        this.nombre = nombre;
        this.productos = new ListaDoble<ProductoBancario>();
    }

    public String getNombre() {
        return nombre;
    }

    public ListaDoble<ProductoBancario> getProductos() {
        return productos;
    }
}
