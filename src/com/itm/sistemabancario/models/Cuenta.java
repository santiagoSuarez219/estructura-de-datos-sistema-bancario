package com.itm.sistemabancario.models;

import java.time.LocalDate;

import com.itm.sistemabancario.structures.Pila;

public abstract class Cuenta implements OperacionesCuenta {
    private int numeroCuenta;
    private double saldo;
    private LocalDate fechaApertura;
    private Pila<Movimiento> movimientos;
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cuenta(Cliente cliente) {
        this(cliente, (int) (Math.random() * 900) + 100, 0, LocalDate.now());
    }

    // Para reconstruir una cuenta que ya existia (carga desde archivo)
    public Cuenta(Cliente cliente, int numeroCuenta, double saldo, LocalDate fechaApertura) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.movimientos = new Pila<Movimiento>();
        this.cliente = cliente;
    }

    @Override
    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        this.saldo += monto;
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
