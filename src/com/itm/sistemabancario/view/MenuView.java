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
                case 2 -> buscarClientePorIdentificacion();
                case 3 -> buscarClientePorIndice();
                case 4 -> recorrerClientes();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        ConsoleUtils.cerrar();
    }

    private void mostrarMenu() {
        System.out.println("1. Crear cliente");
        System.out.println("2. Crear cuenta para un cliente");
        System.out.println("3. Buscar cuentas de un cliente");
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

    private Cliente buscarPorIndice() {

    }
}
