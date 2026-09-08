package com.itm.sistemabancario.models;

public interface OperacionesCuenta {
    void depositar(double monto);

    void retirar(double monto);

    double consultarSaldo();
}
