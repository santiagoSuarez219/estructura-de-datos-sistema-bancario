package com.itm.sistemabancario.services;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.models.Ventanilla;
import com.itm.sistemabancario.structures.Cola;
import com.itm.sistemabancario.structures.ListaSimple;
import com.itm.sistemabancario.structures.Nodo;

public class ClienteService {
    private ListaSimple<Cliente> clientes = new ListaSimple<>();
    private Ventanilla ventanilla = new Ventanilla("Ventanilla 1");

    public Cliente crearCliente(String identificacion, String nombre, String telefono, String direccion) {
        Cliente cliente = new Cliente(identificacion, nombre, telefono, direccion);
        clientes.insertarFinal(cliente);
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
        return true;
    }

    public boolean actualizarTelefonoCliente(Cliente cliente, String nuevoTelefono) {
        if (cliente == null) {
            return false;
        }
        cliente.setTelefono(nuevoTelefono);
        return true;
    }

    public boolean actualizarDireccionCliente(Cliente cliente, String nuevaDireccion) {
        if (cliente == null) {
            return false;
        }
        cliente.setDireccion(nuevaDireccion);
        return true;
    }

    public boolean eliminarCliente(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        return clientes.eliminar(cliente);
    }

}