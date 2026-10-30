package com.itm.sistemabancario.models;

public class ProductoBancario {
    private String codigo;
    private String nombre;
    private String tipo;
    private double tasaAnual;

    public ProductoBancario(String codigo, String nombre, String tipo, double tasaAnual) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.tasaAnual = tasaAnual;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public double getTasaAnual() {
        return tasaAnual;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + tipo + ") " + tasaAnual + "% anual";
    }
}
