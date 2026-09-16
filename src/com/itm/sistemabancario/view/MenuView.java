package com.itm.sistemabancario.view;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.models.Cuenta;
import com.itm.sistemabancario.services.ClienteService;
import com.itm.sistemabancario.utils.ConsoleUtils;

public class MenuView {
    private ClienteService clienteService = new ClienteService();

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = ConsoleUtils.leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> buscarClientePorIndice();
                case 3 -> buscarClientePorIdentificacion();
                case 4 -> actualizarClientePorIdentificacion();
                case 5 -> eliminarClientePorIdentificacion();
                case 6 -> listarClientes();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        ConsoleUtils.cerrar();
    }

    private void mostrarMenu() {
        System.out.println("1. Crear cliente");
        System.out.println("2. Buscar cliente por indice");
        System.out.println("3. Buscar cliente por identificacion");
        System.out.println("4. Actualizar cliente");
        System.out.println("5. Eliminar un cliente por identificacion");
        System.out.println("6. Listar clientes en el sistema");
        System.out.println("0. Salir");
    }

    private void crearCliente() {
        String identificacion = ConsoleUtils.leerTexto("Identificacion: ");
        String nombre = ConsoleUtils.leerTexto("Nombre: ");
        String telefono = ConsoleUtils.leerTexto("Telefono: ");
        String direccion = ConsoleUtils.leerTexto("Direccion: ");
        clienteService.crearCliente(identificacion, nombre, telefono, direccion);
        System.out.println("Cliente creado exitosamente.");
    }

    // private Cliente buscarClientePorIdentificacion() {
    // String identificacion = ConsoleUtils.leerTexto("Identificacion del cliente:
    // ");
    // }

    private void buscarClientePorIndice() {
        int indice = ConsoleUtils.leerEntero("Ingrese el indice del cliente: ");
        Cliente clienteEncontrado = clienteService.buscarPorIndice(indice);
        if (clienteEncontrado == null) {
            System.out.println("Cliente no encontrado");
        } else {
            System.out.println("Cliente encontrado: " + clienteEncontrado.getNombre());

        }
    }
}
