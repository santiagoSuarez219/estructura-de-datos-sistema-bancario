package com.itm.sistemabancario.models;

import java.time.LocalDate;

public abstract class Cuenta implements OperacionesCuenta {
    private String numeroCuenta;
    private double saldo;
    private LocalDate fechaApertura;
    private Movimiento[] movimientos;

    public Cuenta(String numeroCuenta, double saldo, LocalDate fechaApertura) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.movimientos = new Movimiento[0];
    }

    public Cuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0;
        this.fechaApertura = LocalDate.now();
        this.movimientos = new Movimiento[0];
    }

    @Override
    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        this.saldo += monto;
        registrarMovimiento("Deposito", monto);
    }

    @Override
    public double consultarSaldo() {
        return this.saldo;
    }

    protected void debitar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        this.saldo -= monto;
        registrarMovimiento("Retiro", monto);
    }

    protected void registrarMovimiento(String tipo, double monto) {
        Movimiento movimiento = new Movimiento(tipo, monto, LocalDate.now());
        Movimiento[] nuevosMovimientos = new Movimiento[this.movimientos.length + 1];
        for (int i = 0; i < this.movimientos.length; i++) {
            nuevosMovimientos[i] = this.movimientos[i];
        }
        nuevosMovimientos[this.movimientos.length] = movimiento;
        this.movimientos = nuevosMovimientos;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public Movimiento[] getMovimientos() {
        return movimientos;
    }
}
