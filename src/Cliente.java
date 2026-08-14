public class Cliente {

    private String nombre; // Obligatorio
    private String cedula; // Obligatorio
    private String telefono; // Opcional
    private int edad; // Opcional
    private int saldo; // Automatizar

    public Cliente(String nombre, String cedula, String telefono, int edad, int saldo) {
        this(nombre, cedula);
        this.telefono = telefono;
        this.edad = edad;
    }

    public Cliente(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.saldo = 0;
    }

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public void mostrarInfo() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Cedula: " + this.cedula);
        System.out.println("Telefono: " + this.telefono);
        System.out.println("Edad: " + this.edad);
    }

    public int getSaldo() {
        return this.saldo;
    }

    public void setSaldo(int saldo) {
        if (saldo < 0) {
            System.out.println("El saldo no puede ser negativo");
        } else {
            this.saldo = saldo;
            System.out.println("El saldo ha sido actualizado correctamente");
        }
    }

    // get: nombre
    public String getNombre() {
        return this.nombre;
    }

    // set : nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCedula() {
        return cedula;
    }

}
