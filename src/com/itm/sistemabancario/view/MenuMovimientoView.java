package com.itm.sistemabancario.view;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.models.Movimiento;
import com.itm.sistemabancario.services.ClienteService;
import com.itm.sistemabancario.services.CuentaService;
import com.itm.sistemabancario.utils.ConsoleUtils;

public class MenuMovimientoView {
    private CuentaService cuentaService;
    private ClienteService clienteService;
    private Cliente clienteActual;

    public MenuMovimientoView(ClienteService clienteService, CuentaService cuentaService) {
        this.cuentaService = cuentaService;
        this.clienteService = clienteService;
    }

    public void iniciar() {
        String identificacionCliente = ConsoleUtils.leerTexto("Ingrese la identificacion del cliente: ");
        this.clienteActual = clienteService.buscarPorIdentificacion(identificacionCliente);
        if (this.clienteActual == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        int opcion;
        do {
            mostrarOpciones();
            opcion = ConsoleUtils.leerEntero("Elija una opción: ");
            switch (opcion) {
                case 1 -> registrar();
                case 2 -> deshacer();
                case 3 -> consultarUltima();
                case 4 -> verSiHayOperaciones();
                case 5 -> contar();
                case 6 -> listar();
                case 0 -> System.out.println("Volviendo al menu de cuentas...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("1. Registrar movimiento");
        System.out.println("2. Deshacer último movimiento");
        System.out.println("3. Ver último movimiento");
        System.out.println("4. ¿Hay operaciones por deshacer?");
        System.out.println("5. Cantidad de movimientos de la cuenta");
        System.out.println("6. Listar movimientos");
        System.out.println("0. Salir");
    }

    private void registrar() {
        int numeroCuenta = ConsoleUtils.leerEntero("Número de cuenta: ");
        String tipo = ConsoleUtils.leerTexto("Tipo (Deposito / Retiro): ");
        double monto = ConsoleUtils.leerDecimal("Monto: ");
        cuentaService.registrarOperacion(tipo, monto, this.clienteActual, numeroCuenta);
        System.out.println("Movimiento registrado exitosamente");
    }

    private void deshacer() {
        int numeroCuenta = ConsoleUtils.leerEntero("Número de cuenta: ");
        if (cuentaService.sinOperacionesPorDeshacer(this.clienteActual, numeroCuenta)) {
            System.out.println("No hay movimientos por deshacer");
            return;
        }
        Movimiento deshecho = cuentaService.deshacerUltimaOperacion(this.clienteActual, numeroCuenta);
        System.out.println("Se deshizo: " + deshecho.getTipo() + " de " + deshecho.getMonto());
    }

    private void consultarUltima() {
        int numeroCuenta = ConsoleUtils.leerEntero("Número de cuenta: ");
        if (cuentaService.sinOperacionesPorDeshacer(clienteActual, numeroCuenta)) {
            System.out.println("No hay movimientos por deshacer");
            return;
        }
        Movimiento ultimo = cuentaService.consultarUltimaOperacion(clienteActual, numeroCuenta);
        System.out.println("Ultimo movimiento: " + ultimo.getTipo() + " de " + ultimo.getMonto() + " ("
                + ultimo.getFecha() + ")");
    }

    private void verSiHayOperaciones() {
        int numeroCuenta = ConsoleUtils.leerEntero("Número de cuenta: ");
        if (cuentaService.sinOperacionesPorDeshacer(clienteActual, numeroCuenta)) {
            System.out.println("No hay operaciones por deshacer");
        } else {
            System.out.println("Hay operaciones");
        }
    }

    private void contar() {
        int numeroCuenta = ConsoleUtils.leerEntero("Número de cuenta: ");
        int cantidadOperaciones = cuentaService.contarOperaciones(this.clienteActual, numeroCuenta);
        System.out.println("La cuenta " + numeroCuenta + " ha realizado " + cantidadOperaciones + " operaciones");
    }

    private void listar() {
        int numeroCuenta = ConsoleUtils.leerEntero("Número de cuenta: ");
        System.out.println(cuentaService.listarMovimientos(this.clienteActual, numeroCuenta));
    }

}
