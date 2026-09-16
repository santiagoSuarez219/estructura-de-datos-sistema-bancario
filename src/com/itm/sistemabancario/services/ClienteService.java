package com.itm.sistemabancario.services;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.structures.ListaSimple;
import com.itm.sistemabancario.structures.Nodo;

public class ClienteService {
    private ListaSimple<Cliente> clientes = new ListaSimple<>();

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
        System.out.println("Dato no encontrado");
        return null;
    }

    public Cliente buscarPorIndice(int indice) {
        Cliente clienteEncontrado = clientes.buscarPorIndice(indice);
        if (clienteEncontrado != null) {
            return clienteEncontrado;
        }
        return null;
    }

    public void recorrerLista() {
        Nodo<Cliente> actual = clientes.getHead();
        for (int i = 0; i < clientes.getTamano(); i++) {
            Cliente clienteActual = actual.getDato();
            System.out.println("Indice: " + i + " Cliente: " +
                    clienteActual.getNombre());
            actual = actual.getSiguiente();
        }
    }

    public void actualizarNombreCliente(Cliente cliente, String nuevoNombre) {
        if (cliente != null) {
            cliente.setNombre(nuevoNombre);
            System.out.println("Nombre del cliente actualizado exitosamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void actualizarTelefonoCliente(Cliente cliente, String nuevoTelefono) {
        if (cliente != null) {
            cliente.setTelefono(nuevoTelefono);
            System.out.println("Teléfono del cliente actualizado exitosamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void actualizarDireccionCliente(Cliente cliente, String nuevaDireccion) {
        if (cliente != null) {
            cliente.setDireccion(nuevaDireccion);
            System.out.println("Dirección del cliente actualizada exitosamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public boolean eliminarCliente(Cliente cliente) {
        if (cliente != null) {
            boolean eliminado = clientes.eliminar(cliente);
            if (eliminado) {
                System.out.println("Cliente eliminado exitosamente.");
                return true;
            } else {
                System.out.println("Error al eliminar el cliente.");
                return false;
            }
        } else {
            System.out.println("Cliente no encontrado.");
            return false;
        }
    }

}
