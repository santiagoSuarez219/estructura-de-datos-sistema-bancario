package com.itm.sistemabancario.models;

public class CuentaAhorros extends Cuenta {
    private double tasaInteres;

    public CuentaAhorros(Cliente cliente) {
        super(cliente);
        this.tasaInteres = Math.random();
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
