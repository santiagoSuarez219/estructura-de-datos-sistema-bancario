package com.itm.sistemabancario.models;

import java.time.LocalDate;

import com.itm.sistemabancario.structures.Pila;

public abstract class Cuenta implements OperacionesCuenta {
    private int numeroCuenta;
    private double saldo;
    private LocalDate fechaApertura;
    private Pila<Movimiento> movimientos;
    private Cliente cliente;

    public Cuenta(int numeroCuenta, double saldo, LocalDate fechaApertura, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.movimientos = new Pila<Movimiento>();
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cuenta(Cliente cliente) {
        this.numeroCuenta = (int) (Math.random() * 900) + 100;
        this.saldo = 0;
        this.fechaApertura = LocalDate.now();
        this.cliente = cliente;
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
        this.movimientos.push(movimiento);
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Pila<Movimiento> getMovimientos() {
        return movimientos;
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
}
