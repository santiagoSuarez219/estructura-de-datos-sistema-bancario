package com.itm.sistemabancario.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.models.Cuenta;
import com.itm.sistemabancario.models.CuentaAhorros;
import com.itm.sistemabancario.models.CuentaCorriente;
import com.itm.sistemabancario.structures.ListaSimple;
import com.itm.sistemabancario.structures.Nodo;

public class CuentaRepository {
    private static final String AHORROS = "AHORROS";
    private static final String CORRIENTE = "CORRIENTE";

    private final File archivo = new File("src/com/itm/sistemabancario/data/cuentas.txt");

    // Una linea por cuenta: tipo;identificacionCliente;numero;saldo;fecha;tasaOCupo
    public void guardarTodas(ListaSimple<Cliente> clientes) throws IOException {
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) { // sobrescribe todo
            Nodo<Cliente> clienteActual = clientes.getHead();
            while (clienteActual != null) {
                Cliente cliente = clienteActual.getDato();
                Nodo<Cuenta> cuentaActual = cliente.getCuentas().getHead();
                while (cuentaActual != null) {
                    escritor.write(formatear(cliente, cuentaActual.getDato()));
                    escritor.newLine();
                    cuentaActual = cuentaActual.getSiguiente();
                }
                clienteActual = clienteActual.getSiguiente();
            }
        }
    }

    // Agrega cada cuenta a la lista de su cliente. Las lineas invalidas o de
    // clientes que ya no existen se ignoran.
    public void cargarTodas(ListaSimple<Cliente> clientes) throws IOException {
        if (!archivo.exists()) {
            return;
        }
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea = lector.readLine();
            while (linea != null) {
                String[] campos = linea.split(";", -1);
                if (campos.length == 6) {
                    Cliente cliente = buscarCliente(clientes, campos[1]); // Identificar el cliente en el sistema
                    Cuenta cuenta = construir(cliente, campos);
                    cliente.getCuentas().insertarFinal(cuenta);
                }
                linea = lector.readLine();
            }
        }
    }

    private String formatear(Cliente cliente, Cuenta cuenta) {
        String tipo;
        double extra;
        if (cuenta instanceof CuentaAhorros) {
            tipo = AHORROS;
            extra = ((CuentaAhorros) cuenta).getTasaInteres();
        } else {
            tipo = CORRIENTE;
            extra = ((CuentaCorriente) cuenta).getCupoSobregiro();
        }
        return tipo + ";" + cliente.getIdentificacion() + ";" + cuenta.getNumeroCuenta() + ";"
                + cuenta.getSaldo() + ";" + cuenta.getFechaApertura() + ";" + extra;
    }

    private Cuenta construir(Cliente cliente, String[] campos) {
        try {
            int numero = Integer.parseInt(campos[2]);
            double saldo = Double.parseDouble(campos[3]);
            LocalDate fecha = LocalDate.parse(campos[4]);
            double extra = Double.parseDouble(campos[5]);
            if (AHORROS.equals(campos[0])) {
                return new CuentaAhorros(cliente, numero, saldo, fecha, extra);
            }
            if (CORRIENTE.equals(campos[0])) {
                return new CuentaCorriente(cliente, numero, saldo, fecha, extra);
            }
        } catch (NumberFormatException | DateTimeException e) {
            // linea con datos mal formados: se ignora
        }
        return null;
    }

    private Cliente buscarCliente(ListaSimple<Cliente> clientes, String identificacion) {
        Nodo<Cliente> actual = clientes.getHead();
        while (actual != null) {
            if (actual.getDato().getIdentificacion().equals(identificacion)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}
