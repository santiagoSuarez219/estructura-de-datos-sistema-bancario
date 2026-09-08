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

    @Override
    public void pagar(double monto) {
        super.pagar(monto);
        if (monto > mostrarCupo()) {
            throw new IllegalArgumentException("El monto supera el cupo disponible");
        }
        this.deuda += monto;
        System.out.println("Pago exitoso");
    }

    @Override
    public String abonar(double monto) {
        super.pagar(monto);
        this.deuda = this.deuda - monto;
        return "Saldo abonado exitosamente";
    }

    public double mostrarCupo() {
        return this.cupo - this.deuda;
    }

    @Override
    public String generarRegistroAuditoria() {
        System.out.println("Audiante tarjeta " + this.numertoTarjeta + "de la franquicia " + this.franquicia);
        return "Auditoria exitosa";
    }

    @Override
    public void procesarActivo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'procesarActivo'");
    }
}
