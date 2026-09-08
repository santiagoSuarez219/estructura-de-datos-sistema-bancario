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

    @Override
    public void pagar(double monto) {
        super.pagar(monto);
        if (monto > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar el pago");
        }
        this.saldo -= monto;
        System.out.println("Pago exitoso");
    }

    @Override
    public String abonar(double monto) {
        super.pagar(monto);
        consignar(monto);
        return "Saldo abonado exitosamente";
    }

    public double mostrarSaldo() {
        return this.saldo;
    }

    @Override
    public String generarRegistroAuditoria() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generarRegistroAuditoria'");
    }

    @Override
    public void procesarActivo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'procesarActivo'");
    }
}
