public class App {
    public static void main(String[] args) throws Exception {
        Cliente maria = new Cliente("Maria", "123", "3456", 27, 20000);
        // System.out.println(maria.telefono);
        System.out.println(maria.getSaldo());
        maria.setSaldo(-30000);
        System.out.println(maria.getSaldo());

        Cliente carlos = new Cliente("Carlos", "567");
        carlos.setTelefono("345545");
        carlos.setEdad(45);
        System.out.println(carlos.getSaldo());
        System.out.println(carlos.getEdad());

        Cliente ana = new Cliente("Ana");

        Cliente juan = new Cliente("Juan", "4567", "23345", 21, 30000);
        System.out.println(juan.getSaldo());

    }
}
