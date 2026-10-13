package com.itm.sistemabancario.view;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.models.CuentaAhorros;
import com.itm.sistemabancario.models.CuentaCorriente;
import com.itm.sistemabancario.services.ClienteService;
import com.itm.sistemabancario.services.CuentaService;
import com.itm.sistemabancario.utils.ConsoleUtils;

public class MenuCuentaView {
    private ClienteService clienteService;
    private CuentaService cuentaService;

    private MenuMovimientoView menuMovimiento;

    public MenuCuentaView(ClienteService clienteService, CuentaService cuentaService) {
        this.clienteService = clienteService;
        this.cuentaService = cuentaService;
        this.menuMovimiento = new MenuMovimientoView(clienteService, cuentaService);

    }

    private void mostrarMenuCuenta() {
        System.out.println("1. Crear cuenta");
        // System.out.println("3. Buscar cliente por identificacion");
        // System.out.println("4. Actualizar cliente por identificacion");
        // System.out.println("5. Eliminar cliente por identificacion");
        // System.out.println("6. Listar clientes");
        System.out.println("7. Gestionar movimientos de la cuenta");
        System.out.println("0. Atras");
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenuCuenta();
            opcion = ConsoleUtils.leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 -> crearCuenta();
                case 7 -> this.menuMovimiento.iniciar();
                case 0 -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

    }

    private void crearCuenta() {
        String identificacionCliente = ConsoleUtils.leerTexto("Ingrese la identificacion del cliente: ");
        Cliente cliente = clienteService.buscarPorIdentificacion(identificacionCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        int tipoCuenta = ConsoleUtils.leerEntero("Que tipo de cuenta desea crear (1) Corriente (2) Ahorros: ");
        if (tipoCuenta == 1) {
            double cupoSobregiro = ConsoleUtils.leerDecimal("Ingrese el cupo de sobregiro de la cuenta: ");
            CuentaCorriente nuevaCuenta = this.cuentaService.crearCuentaCorriente(cliente, cupoSobregiro);
            System.out.println("Se ha creado la cuenta numero " + nuevaCuenta.getNumeroCuenta() + " del cliente "
                    + cliente.getNombre());

        } else if (tipoCuenta == 2) {
            CuentaAhorros nuevaCuenta = this.cuentaService.crearCuentaAhorros(cliente);
            System.out.println("Se ha creado la cuenta numero " + nuevaCuenta.getNumeroCuenta() + " del cliente "
                    + cliente.getNombre());
        } else {
            System.out.println("Opcion invalida");
        }
    }
}
