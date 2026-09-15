package com.itm.sistemabancario.services;

import java.time.LocalDate;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.models.Cuenta;
import com.itm.sistemabancario.models.CuentaAhorros;
import com.itm.sistemabancario.models.CuentaCorriente;
import com.itm.sistemabancario.structures.ListaSimple;
import com.itm.sistemabancario.structures.Nodo;

public class ClienteService {
    private ListaSimple<Cliente> clientes = new ListaSimple<>();

    public Cliente crearCliente(String identificacion, String nombre, String telefono, String direccion) {
        Cliente cliente = new Cliente(identificacion, nombre, telefono, direccion);
        clientes.insertarFinal(cliente);
        return cliente;
    }

    // public Cliente buscarPorIdentificacion(String identificacion) {
    // // clienteEncontrado = buscarPorValor();
    // if (clienteEncontrado.getIdentificacion().equals(identificacion)) {
    // return cliente;
    // }
    // return null;
    // }

    // public Cliente buscarPorIndice(int indice) {
    // // clienteEncontrado = buscarPorIndice();
    // if (clienteEncontrado.getIdentificacion().equals(identificacion)) {
    // return cliente;
    // }
    // return null;
    // }

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
