public class Tarjeta {

    String numeroTarjeta;
    String tipoTarjeta;
    double cupo;
    int mesesVigencia;

    public Tarjeta(String numeroTarjeta, String tipoTarjeta, double cupo, int mesesVigencia) {
        this.numeroTarjeta = numeroTarjeta;
        this.tipoTarjeta = tipoTarjeta;
        this.cupo = cupo;
        this.mesesVigencia = mesesVigencia;
    }

    public void mostrarInfo() {
        System.out.println("Numero de tarjeta: " + this.numeroTarjeta);
        System.out.println("Tipo de tarjeta: " + this.tipoTarjeta);
        System.out.println("Cupo: " + this.cupo);
        System.out.println("Meses de vigencia: " + this.mesesVigencia);
    }

}
