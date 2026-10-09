package com.itm.sistemabancario.models;

public class CuentaCorriente extends Cuenta {
    private double cupoSobregiro;

    public CuentaCorriente(Cliente cliente, double cupoSobregiro) {
        super(cliente);
        this.cupoSobregiro = cupoSobregiro;
    }

    @Override
    public void retirar(double monto) {
        if (monto > consultarSaldo() + this.cupoSobregiro) {
            throw new IllegalArgumentException("El monto supera el saldo disponible y el cupo de sobregiro");
        }
        debitar(monto);
    }

    public double getCupoSobregiro() {
        return cupoSobregiro;
    }

    public void setCupoSobregiro(double cupoSobregiro) {
        this.cupoSobregiro = cupoSobregiro;
    }
}
