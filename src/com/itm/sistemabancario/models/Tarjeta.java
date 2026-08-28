package com.itm.sistemabancario.models;

public abstract class Tarjeta {
    protected double numertoTarjeta;
    protected int cvc;
    protected String fechaCaducidad;
    protected String franquicia;
    protected String titular;

    public Tarjeta(double numertoTarjeta, String franquicia, String titular) {
        this.numertoTarjeta = numertoTarjeta;
        this.franquicia = franquicia;
        this.titular = titular;
    }

    public double getNumertoTarjeta() {
        return numertoTarjeta;
    }

    public void setNumertoTarjeta(double numertoTarjeta) {
        this.numertoTarjeta = numertoTarjeta;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public abstract String abonar(double monto);

    public void pagar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        System.out.println("Procesando ...");
    }
}
