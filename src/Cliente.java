public class Cliente {

    String nombre;
    String cedula;
    String telefono;
    int edad;

    public Cliente(String nombre, String cedula, String telefono, int edad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.edad = edad;
    }

    public void mostrarInfo() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Cedula: " + this.cedula);
        System.out.println("Telefono: " + this.telefono);
        System.out.println("Edad: " + this.edad);
    }

}
