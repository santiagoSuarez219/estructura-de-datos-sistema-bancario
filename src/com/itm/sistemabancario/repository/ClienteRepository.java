package com.itm.sistemabancario.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.structures.ListaSimple;
import com.itm.sistemabancario.structures.Nodo;

public class ClienteRepository {
    private final File archivo = new File("src/com/itm/sistemabancario/data/clientes.txt");

    public void guardar(Cliente cliente) throws IOException {
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, true))) { // true = agrega al final
            escritor.write(cliente.getIdentificacion() + ";" + cliente.getNombre() + ";"
                    + cliente.getTelefono() + ";" + cliente.getDireccion());
            escritor.newLine();
        }
    }

    public void guardarTodos(ListaSimple<Cliente> clientes) throws IOException {
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) { // sin true: sobrescribe todo
            Nodo<Cliente> actual = clientes.getHead();
            while (actual != null) {
                Cliente cliente = actual.getDato();
                escritor.write(cliente.getIdentificacion() + ";" + cliente.getNombre() + ";"
                        + cliente.getTelefono() + ";" + cliente.getDireccion());
                escritor.newLine();
                actual = actual.getSiguiente();
            }
        }
    }

    public ListaSimple<Cliente> cargarTodos() throws IOException {
        ListaSimple<Cliente> clientes = new ListaSimple<>();
        if (!archivo.exists()) {
            return clientes; // primera ejecucion: aun no hay nada que cargar
        }
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea = lector.readLine(); // null cuando no hay mas lineas
            while (linea != null) {
                String[] campos = linea.split(";", -1); // -1 conserva los campos vacios del final
                if (campos.length == 4) { // ignora lineas que no tienen el formato esperado
                    clientes.insertarFinal(new Cliente(campos[0], campos[1], campos[2], campos[3]));
                }
                linea = lector.readLine();
            }
        }
        return clientes;
    }
}
