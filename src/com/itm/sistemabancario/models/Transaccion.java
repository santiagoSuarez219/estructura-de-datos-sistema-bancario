package com.itm.sistemabancario.models;

public class Transaccion {

    String tipoTransaccion;
    double monto;
    String fecha;
    String cuentaDestino;

    public Transaccion(String tipoTransaccion, double monto, String fecha, String cuentaDestino) {
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.fecha = fecha;
        this.cuentaDestino = cuentaDestino;
    }

    public void mostrarInfo() {
        System.out.println("Tipo: " + this.tipoTransaccion);
        System.out.println("Monto: " + this.monto);
        System.out.println("Fecha: " + this.fecha);
        System.out.println("Cuenta destino: " + this.cuentaDestino);
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

}
