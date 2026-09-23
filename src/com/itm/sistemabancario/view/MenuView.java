package com.itm.sistemabancario.view;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.models.Cuenta;
import com.itm.sistemabancario.models.CuentaAhorros;
import com.itm.sistemabancario.models.CuentaCorriente;
import com.itm.sistemabancario.services.ClienteService;
import com.itm.sistemabancario.services.CuentaService;
import com.itm.sistemabancario.utils.ConsoleUtils;

public class MenuView {
    private ClienteService clienteService = new ClienteService();
    private CuentaService cuentaService = new CuentaService();

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = ConsoleUtils.leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 -> accionesCliente();
                case 2 -> accionesCuenta();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        ConsoleUtils.cerrar();
    }

    private void mostrarMenu() {
        System.out.println("1. Gestionar clientes");
        System.out.println("2. Gestionar cuentas");
        System.out.println("0. Salir");
    }

    private void mostrarMenuCliente() {
        System.out.println("1. Crear cliente");
        System.out.println("2. Buscar cliente por indice");
        System.out.println("3. Buscar cliente por identificacion");
        System.out.println("4. Actualizar cliente por identificacion");
        System.out.println("5. Eliminar cliente por identificacion");
        System.out.println("6. Listar clientes");
        System.out.println("0. Atras");
    }

    private void accionesCliente() {
        int opcion;
        do {
            mostrarMenuCliente();
            opcion = ConsoleUtils.leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> buscarClientePorIndice();
                case 3 -> buscarClientePorIdentificacion();
                case 4 -> actualizarClientePorIdentificacion();
                case 5 -> eliminarClientePorIdentificacion();
                case 6 -> clienteService.recorrerLista();
                case 0 -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuCuenta() {
        System.out.println("1. Crear cuenta");
        System.out.println("2. Ver los movimientos de la cuenta");
        // System.out.println("3. Buscar cliente por identificacion");
        // System.out.println("4. Actualizar cliente por identificacion");
        // System.out.println("5. Eliminar cliente por identificacion");
        // System.out.println("6. Listar clientes");
        System.out.println("0. Atras");
    }

    private void accionesCuenta() {
        int opcion;
        do {
            mostrarMenuCuenta();
            opcion = ConsoleUtils.leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 -> crearCuenta();
                case 2 -> listarMovimientosCuenta();
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

    private void listarMovimientosCuenta() {
        String identificacionCliente = ConsoleUtils.leerTexto("Ingrese la identificacion del cliente: ");
        Cliente cliente = clienteService.buscarPorIdentificacion(identificacionCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        int numeroCuenta = ConsoleUtils.leerEntero("Ingrese el numero de la cuenta: ");
        Cuenta cuenta = this.cuentaService.buscarCuentaPorNumero(cliente, numeroCuenta);
        if (cuenta == null) {
            System.out.println("La cuenta no existe");
        } else {
            this.cuentaService.listarMovimientosCuenta(cuenta);
        }
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