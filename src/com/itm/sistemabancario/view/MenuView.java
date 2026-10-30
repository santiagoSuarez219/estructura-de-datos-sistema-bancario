package com.itm.sistemabancario.view;

import com.itm.sistemabancario.services.ClienteService;
import com.itm.sistemabancario.services.CuentaService;
import com.itm.sistemabancario.services.ProductoService;
import com.itm.sistemabancario.utils.ConsoleUtils;

public class MenuView {
    private ClienteService clienteService = new ClienteService();
    private CuentaService cuentaService = new CuentaService(clienteService);
    private ProductoService productoService = new ProductoService();

    private MenuClienteView menuCliente = new MenuClienteView(clienteService);
    private MenuCuentaView menuCuenta = new MenuCuentaView(clienteService, cuentaService);
    private MenuCatalogoView menuCatalogo = new MenuCatalogoView(productoService);

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = ConsoleUtils.leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 -> menuCliente.iniciar();
                case 2 -> menuCuenta.iniciar();
                case 3 -> menuCatalogo.iniciar();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        ConsoleUtils.cerrar();
    }

    private void mostrarMenu() {
        System.out.println("1. Gestionar clientes");
        System.out.println("2. Gestionar cuentas");
        System.out.println("3. Catalogo de productos");
        System.out.println("0. Salir");
    }
}