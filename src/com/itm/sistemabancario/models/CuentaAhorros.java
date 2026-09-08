package com.itm.sistemabancario.models;

import java.time.LocalDate;

public class CuentaAhorros extends Cuenta {
    private double tasaInteres;

    public CuentaAhorros(String numeroCuenta, double saldo, LocalDate fechaApertura, double tasaInteres) {
        super(numeroCuenta, saldo, fechaApertura);
        this.tasaInteres = tasaInteres;
    }

    public CuentaAhorros(String numeroCuenta, double tasaInteres) {
        super(numeroCuenta);
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void retirar(double monto) {
        if (monto > consultarSaldo()) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar el retiro");
        }
        debitar(monto);
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }
}
