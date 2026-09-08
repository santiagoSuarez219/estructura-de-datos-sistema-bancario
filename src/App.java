import com.itm.sistemabancario.models.CuentaAhorros;

public class App {
    public static void main(String[] args) throws Exception {
        CuentaAhorros cuentaAhorros = new CuentaAhorros("123", 0.1);
        cuentaAhorros.depositar(20000.0);

    }
}
