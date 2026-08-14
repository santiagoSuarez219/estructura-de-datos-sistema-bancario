public class Cuenta {

    String numeroCuenta;
    String tipoCuenta;
    double saldo;
    String titular;

    public Cuenta(String numeroCuenta, String tipoCuenta, double saldo, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.titular = titular;
    }

    public void mostrarInfo() {
        System.out.println("Numero de cuenta: " + this.numeroCuenta);
        System.out.println("Tipo de cuenta: " + this.tipoCuenta);
        System.out.println("Saldo: " + this.saldo);
        System.out.println("Titular: " + this.titular);
    }

}
