package com.itm.sistemabancario.services;

import java.time.LocalDate;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.structures.ListaSimple;

public class ClienteService {
    private ListaSimple<Cliente> clientes = new ListaSimple<>(); // Lista de clientes del sistema

    public Cliente crearCliente(String identificacion, String nombre, String telefono, String direccion) {
        Cliente cliente = new Cliente(identificacion, nombre, telefono, direccion); // Crea el objeto de tipo Cliente
        clientes.crear(0, cliente);
        return cliente;
    }

    // public Cliente buscarPorIdentificacion(String identificacion) {
    // // clienteEncontrado = buscarPorValor();
    // if (clienteEncontrado.getIdentificacion().equals(identificacion)) {
    // return cliente;
    // }
    // return null;
    // }

    public Cliente buscarPorIndice(int indice) {
        Cliente clienteEncontrado = clientes.buscarPorIndice(indice);
        if (clienteEncontrado == null) {
            return null;
        }
        return clienteEncontrado;
    }

    // public void recorrerLista() {
    // Nodo<Cliente> actual = clientes.getHead();
    // for (int i = 0; i < clientes.getTamano(); i++) {
    // Cliente clienteActual = actual.getDato();
    // System.out.println("Indice: " + i + " Cliente: " +
    // clienteActual.getNombre());
    // actual = actual.getSiguiente();
    // }
    // }

}
