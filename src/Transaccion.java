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

}
