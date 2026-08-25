package com.itm.sistemabancario.models;

public class Tarjeta {
    private double numertoTarjeta;
    private int cvc;
    private String fechaCaducidad;
    protected String franquicia;
    private String titular;

    public Tarjeta(double numertoTarjeta, String franquicia, String titular) {
        this.numertoTarjeta = numertoTarjeta;
        this.franquicia = franquicia;
        this.titular = titular;
        System.out.println("Constructor del padre");
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

    public String pagar(double monto) {
        return "Pago exitoso";
    }
}
