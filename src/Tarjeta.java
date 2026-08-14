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

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public double getCupo() {
        return cupo;
    }

    public void setCupo(double cupo) {
        this.cupo = cupo;
    }

    public int getMesesVigencia() {
        return mesesVigencia;
    }

    public void setMesesVigencia(int mesesVigencia) {
        this.mesesVigencia = mesesVigencia;
    }

}
