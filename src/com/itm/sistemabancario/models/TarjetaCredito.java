package com.itm.sistemabancario.models;

public class TarjetaCredito extends Tarjeta {
    private double cupo;
    private double deuda;

    public TarjetaCredito(double numertoTarjeta, String franquicia, String titular) {
        super(numertoTarjeta, franquicia, titular);
    }

    public double getCupo() {
        return cupo;
    }

    public void setCupo(double cupo) {
        this.cupo = cupo;
    }

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }

    public String abonar(double monto) {
        return "Saldo abonado exitosamente";
    }

    public double mostrarCupo() {
        return 0.0;
    }
}
