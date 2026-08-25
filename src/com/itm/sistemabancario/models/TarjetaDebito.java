package com.itm.sistemabancario.models;

public class TarjetaDebito extends Tarjeta {
    private double saldo;

    public TarjetaDebito(double numertoTarjeta, String franquicia, String titular) {
        super(numertoTarjeta, franquicia, titular);
        System.out.println(this.franquicia);
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void consignar(double monto) {
        this.saldo += monto;
        System.out.println("Consignacion exitosa");
    }

    public double mostrarSaldo() {
        return 0.0;
    }
}
