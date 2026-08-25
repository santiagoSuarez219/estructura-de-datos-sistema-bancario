import com.itm.sistemabancario.models.TarjetaCredito;
import com.itm.sistemabancario.models.TarjetaDebito;

public class App {
    public static void main(String[] args) throws Exception {
        TarjetaDebito visa01 = new TarjetaDebito(123, "Visa", "Santiago");
        TarjetaCredito mastercard02 = new TarjetaCredito(456, "MasterCard", "Maria");

        double saldo = visa01.mostrarSaldo();
        double saldo2 = mastercard02.mostrarCupo();

        visa01.pagar(20000.0);
        mastercard02.pagar(20000.0);

    }
}
