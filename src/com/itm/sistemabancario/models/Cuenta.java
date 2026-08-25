package com.itm.sistemabancario.models;

public class Cuenta {

    private String numeroCuenta;
    private String tipoCuenta;
    private double saldo;
    private String titular;

    public Cuenta(String numeroCuenta, String tipoCuenta, double saldo, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.titular = titular;
    }

    public void mostrarInfo() {
        System.out.println("Numero de cuenta: " + this.numeroCuenta);
        System.out.println("Tipo de cuenta: " + this.tipoCuenta);
        System.out.println("Saldo: " + this.saldo);
        System.out.println("Titular: " + this.titular);
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

}
