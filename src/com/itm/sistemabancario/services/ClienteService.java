package com.itm.sistemabancario.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.models.Ventanilla;
import com.itm.sistemabancario.structures.Cola;
import com.itm.sistemabancario.models.Cuenta;
import com.itm.sistemabancario.models.CuentaAhorros;
import com.itm.sistemabancario.models.CuentaCorriente;
import com.itm.sistemabancario.structures.ListaSimple;
import com.itm.sistemabancario.structures.Nodo;

public class ClienteService {
    private static final String ARCHIVO_CLIENTES = "data/clientes.txt";
    private static final String ARCHIVO_CUENTAS = "data/cuentas.txt";
    private static final String SEPARADOR = ";";
    private static final String AHORROS = "AHORROS";
    private static final String CORRIENTE = "CORRIENTE";

    private ListaSimple<Cliente> clientes = new ListaSimple<>();
    private Ventanilla ventanilla = new Ventanilla("Ventanilla 1");

    public ClienteService() {
        System.out.println(cargarClientes());
        System.out.println(cargarCuentas());
    }

    public Cliente crearCliente(String identificacion, String nombre, String telefono, String direccion) {
        Cliente cliente = new Cliente(identificacion, nombre, telefono, direccion);
        clientes.insertarFinal(cliente);
        persistir();
        return cliente;
    }

    public Cliente buscarPorIdentificacion(String identificacion) {
        Nodo<Cliente> clienteActual = clientes.getHead();
        while (clienteActual != null) {
            if (clienteActual.getDato().getIdentificacion().equals(identificacion)) {
                return clienteActual.getDato();
            }
            clienteActual = clienteActual.getSiguiente();
        }
        return null;
    }

    private Cliente buscarClienteOFallar(String identificacion) {
        Cliente cliente = buscarPorIdentificacion(identificacion);
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente " + identificacion + " no existe");
        }
        return cliente;
    }

    public void registrarLlegada(String identificacion) {
        Cliente cliente = buscarClienteOFallar(identificacion);
        ventanilla.getFila().enqueue(cliente);
    }

    public Cliente atenderSiguiente() {
        return ventanilla.getFila().dequeue();
    }

    public Cliente consultarSiguiente() {
        return ventanilla.getFila().front();
    }

    public boolean sinClientesEnEspera() {
        return ventanilla.getFila().isEmpty();
    }

    public int contarEnEspera() {
        return ventanilla.getFila().size();
    }

    public String listarEnEspera() {
        Cola<Cliente> fila = ventanilla.getFila();
        if (fila.isEmpty()) {
            return "No hay clientes en espera";
        }
        StringBuilder texto = new StringBuilder();
        int total = fila.size();
        for (int i = 1; i <= total; i++) {
            Cliente cliente = fila.dequeue();
            texto.append(i).append(". ").append(cliente.getNombre()).append("\n");
            fila.enqueue(cliente);
        }
        return texto.toString();
    }

    public Cliente buscarPorIndice(int indice) {
        if (indice < 0 || indice >= clientes.getTamano()) {
            return null;
        }
        return clientes.buscarPorIndice(indice);
    }

    public String listarClientes() {
        StringBuilder texto = new StringBuilder();
        Nodo<Cliente> actual = clientes.getHead();
        for (int i = 0; i < clientes.getTamano(); i++) {
            texto.append("Indice: ").append(i).append(" Cliente: ")
                    .append(actual.getDato().getNombre()).append("\n");
            actual = actual.getSiguiente();
        }
        return texto.toString();
    }

    public boolean actualizarNombreCliente(Cliente cliente, String nuevoNombre) {
        if (cliente == null) {
            return false;
        }
        cliente.setNombre(nuevoNombre);
        persistir();
        return true;
    }

    public boolean actualizarTelefonoCliente(Cliente cliente, String nuevoTelefono) {
        if (cliente == null) {
            return false;
        }
        cliente.setTelefono(nuevoTelefono);
        persistir();
        return true;
    }

    public boolean actualizarDireccionCliente(Cliente cliente, String nuevaDireccion) {
        if (cliente == null) {
            return false;
        }
        cliente.setDireccion(nuevaDireccion);
        persistir();
        return true;
    }

    public boolean eliminarCliente(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        boolean eliminado = clientes.eliminar(cliente);
        if (eliminado) {
            persistir();
            persistirCuentas(); // sus cuentas desaparecen con el
        }
        return eliminado;
    }

    public void persistirCuentas() {
        try {
            escribirCuentas();
        } catch (IOException e) {
            System.out.println("No se pudieron guardar las cuentas en el archivo: " + e.getMessage());
        }
    }

    public String guardarClientes() {
        try {
            escribirClientes();
            return "Se guardaron " + clientes.getTamano() + " clientes en " + ARCHIVO_CLIENTES;
        } catch (IOException e) {
            return "No se pudo guardar: " + e.getMessage();
        }
    }

    public String cargarClientes() {
        File archivo = new File(ARCHIVO_CLIENTES);
        clientes = new ListaSimple<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(SEPARADOR);
                if (campos.length != 4) {
                    continue;
                }
                clientes.insertarFinal(new Cliente(campos[0], campos[1], campos[2], campos[3]));
            }
        } catch (FileNotFoundException e) {
            return "No existe " + ARCHIVO_CLIENTES + "; se inicia con la lista de clientes vacía";
        } catch (IOException e) {
            return "No se pudo leer: " + e.getMessage();
        }
        return "Se cargaron " + clientes.getTamano() + " clientes. "; // las cuentas cuelgan de sus clientes
    }

    private void persistir() {
        try {
            escribirClientes();
        } catch (IOException e) {
            System.out.println("No se pudieron guardar los clientes en el archivo: " + e.getMessage());
        }
    }

    private void escribirClientes() throws IOException {
        File carpeta = new File(ARCHIVO_CLIENTES).getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        String texto = "";
        Nodo<Cliente> actual = clientes.getHead();
        while (actual != null) {
            Cliente cliente = actual.getDato();
            texto += String.join(SEPARADOR,
                    cliente.getIdentificacion(),
                    cliente.getNombre(),
                    cliente.getTelefono(),
                    cliente.getDireccion()) + "\n";
            actual = actual.getSiguiente();
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO_CLIENTES))) {
            escritor.write(texto);
        }
    }

    private String cargarCuentas() {
        File archivo = new File(ARCHIVO_CUENTAS);

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(SEPARADOR);
                if (campos.length != 6) {
                    continue;
                }
                Cliente cliente = buscarPorIdentificacion(campos[1]);
                if (cliente == null) {
                    continue; // la cuenta es de un cliente que ya no existe
                }
                int numero = Integer.parseInt(campos[2]);
                double saldo = Double.parseDouble(campos[3]);
                LocalDate fecha = LocalDate.parse(campos[4]);
                double extra = Double.parseDouble(campos[5]);
                if (AHORROS.equals(campos[0])) {
                    cliente.getCuentas().insertarFinal(new CuentaAhorros(cliente, numero, saldo, fecha, extra));
                } else if (CORRIENTE.equals(campos[0])) {
                    cliente.getCuentas().insertarFinal(new CuentaCorriente(cliente, numero, saldo, fecha, extra));
                }
            }
        } catch (FileNotFoundException e) {
            return "No existe " + ARCHIVO_CUENTAS + "; se inicia sin cuentas";
        } catch (IOException e) {
            return "No se pudo leer: " + e.getMessage();
        }
        return "Se cargaron las cuentas de los clientes";
    }

    // Una línea por cuenta: tipo;identificacionCliente;numero;saldo;fecha;tasaOCupo
    private void escribirCuentas() throws IOException {
        File carpeta = new File(ARCHIVO_CUENTAS).getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        String texto = "";
        Nodo<Cliente> clienteActual = clientes.getHead();
        while (clienteActual != null) {
            Cliente cliente = clienteActual.getDato();
            Nodo<Cuenta> cuentaActual = cliente.getCuentas().getHead();
            while (cuentaActual != null) {
                Cuenta cuenta = cuentaActual.getDato();
                String tipo;
                double extra;
                if (cuenta instanceof CuentaAhorros) {
                    tipo = AHORROS;
                    extra = ((CuentaAhorros) cuenta).getTasaInteres();
                } else {
                    tipo = CORRIENTE;
                    extra = ((CuentaCorriente) cuenta).getCupoSobregiro();
                }
                texto += String.join(SEPARADOR,
                        tipo,
                        cliente.getIdentificacion(),
                        String.valueOf(cuenta.getNumeroCuenta()),
                        String.valueOf(cuenta.getSaldo()),
                        String.valueOf(cuenta.getFechaApertura()),
                        String.valueOf(extra)) + "\n";
                cuentaActual = cuentaActual.getSiguiente();
            }
            clienteActual = clienteActual.getSiguiente();
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO_CUENTAS))) {
            escritor.write(texto);
        }
    }

}