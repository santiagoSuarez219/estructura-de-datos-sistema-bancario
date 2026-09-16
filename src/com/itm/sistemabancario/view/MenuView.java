package com.itm.sistemabancario.view;

import com.itm.sistemabancario.models.Cliente;
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
                case 6 -> clienteService.recorrerLista();
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
        System.out.println("4. Actualizar cliente por identificacion");
        System.out.println("5. Eliminar cliente por identificacion");
        System.out.println("6. Listar clientes");
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

    private Cliente buscarClientePorIdentificacion() {
        String identificacion = ConsoleUtils.leerTexto("Identificacion del cliente: ");
        return clienteService.buscarPorIdentificacion(identificacion);
    }

    private void buscarClientePorIndice() {
        int indice = ConsoleUtils.leerEntero("Indice del cliente: ");
        Cliente cliente = clienteService.buscarPorIndice(indice);
        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente.getNombre());
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void mostrarMenuActualizarCliente() {
        System.out.println("1. Actualizar nombre");
        System.out.println("2. Actualizar telefono");
        System.out.println("3. Actualizar direccion");
    }

    private void actualizarClientePorIdentificacion() {
        String identificacion = ConsoleUtils.leerTexto("Identificacion del cliente a actualizar: ");
        Cliente cliente = clienteService.buscarPorIdentificacion(identificacion);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        mostrarMenuActualizarCliente();
        int opcion = ConsoleUtils.leerEntero("Seleccione una opcion: ");
        switch (opcion) {
            case 1: {
                String nuevoNombre = ConsoleUtils.leerTexto("Nuevo nombre: ");
                clienteService.actualizarNombreCliente(cliente, nuevoNombre);
                break;
            }
            case 2: {
                String nuevoTelefono = ConsoleUtils.leerTexto("Nuevo telefono: ");
                clienteService.actualizarTelefonoCliente(cliente, nuevoTelefono);
                break;
            }
            case 3: {
                String nuevaDireccion = ConsoleUtils.leerTexto("Nueva direccion: ");
                clienteService.actualizarDireccionCliente(cliente, nuevaDireccion);
                break;
            }
            default:
                System.out.println("Opcion invalida.");
        }
    }

    private void eliminarClientePorIdentificacion() {
        String identificacion = ConsoleUtils.leerTexto("Identificacion del cliente a eliminar: ");
        Cliente cliente = clienteService.buscarPorIdentificacion(identificacion);
        if (cliente != null) {
            boolean eliminado = clienteService.eliminarCliente(cliente);
            if (eliminado) {
                System.out.println("Cliente eliminado exitosamente.");
            } else {
                System.out.println("Error al eliminar el cliente.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

}
