package com.itm.sistemabancario.view;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.services.ClienteService;
import com.itm.sistemabancario.utils.ConsoleUtils;

public class MenuTaquillaView {
    private ClienteService clienteService;

    public MenuTaquillaView(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = ConsoleUtils.leerEntero("Elija una opción: ");
            switch (opcion) {
                case 1 -> registrarLlegada();
                case 2 -> atender();
                case 3 -> consultarSiguiente();
                case 4 -> verSiHayEnEspera();
                case 5 -> contar();
                case 6 -> listar();
                case 0 -> System.out.println("Saliendo del menú de colas...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("1. Registrar llegada (enqueue)");
        System.out.println("2. Atender al siguiente (dequeue)");
        System.out.println("3. Ver quién sigue (front)");
        System.out.println("4. ¿Hay clientes en espera? (isEmpty)");
        System.out.println("5. Cantidad en espera (size)");
        System.out.println("6. Listar la fila");
        System.out.println("0. Salir");
    }

    private void registrarLlegada() {
        String identificacion = ConsoleUtils.leerTexto("Identificación del cliente: ");
        clienteService.registrarLlegada(identificacion);
        System.out.println("Llegada registrada exitosamente");
    }

    private void atender() {
        if (clienteService.sinClientesEnEspera()) {
            System.out.println("No hay clientes en espera");
            return;
        }
        Cliente atendido = clienteService.atenderSiguiente();
        System.out.println("Se atendió a: " + atendido.getNombre());
    }

    private void consultarSiguiente() {
        if (clienteService.sinClientesEnEspera()) {
            System.out.println("No hay clientes en espera");
            return;
        }
        Cliente siguiente = clienteService.consultarSiguiente();
        System.out.println("Sigue: " + siguiente.getNombre());
    }

    private void verSiHayEnEspera() {
        if (clienteService.sinClientesEnEspera()) {
            System.out.println("No hay clientes en espera");
        } else {
            System.out.println("Hay clientes en espera");
        }
    }

    private void contar() {
        System.out.println("Clientes en espera: " + clienteService.contarEnEspera());
    }

    private void listar() {
        System.out.println(clienteService.listarEnEspera());
    }
}
