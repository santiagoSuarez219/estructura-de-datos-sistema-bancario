package com.itm.sistemabancario.services;

import java.io.IOException;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.repository.ClienteRepository;
import com.itm.sistemabancario.repository.CuentaRepository;
import com.itm.sistemabancario.structures.ListaSimple;
import com.itm.sistemabancario.structures.Nodo;

public class ClienteService {
    private ListaSimple<Cliente> clientes = new ListaSimple<>();
    private ClienteRepository repositorio = new ClienteRepository();
    private CuentaRepository cuentaRepositorio = new CuentaRepository();

    public ClienteService() {
        try {
            clientes = repositorio.cargarTodos();
            cuentaRepositorio.cargarTodas(clientes); // las cuentas cuelgan de sus clientes
        } catch (IOException e) {
            System.out.println("No se pudieron cargar los datos del archivo: " + e.getMessage());
        }
    }

    public Cliente crearCliente(String identificacion, String nombre, String telefono, String direccion) {
        Cliente cliente = new Cliente(identificacion, nombre, telefono, direccion);
        clientes.insertarFinal(cliente);
        try {
            repositorio.guardar(cliente);
        } catch (IOException e) {
            System.out.println("No se pudo guardar el cliente en el archivo: " + e.getMessage());
        }
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
            cuentaRepositorio.guardarTodas(clientes);
        } catch (IOException e) {
            System.out.println("No se pudieron guardar las cuentas en el archivo: " + e.getMessage());
        }
    }

    private void persistir() {
        try {
            repositorio.guardarTodos(clientes);
        } catch (IOException e) {
            System.out.println("No se pudieron guardar los clientes en el archivo: " + e.getMessage());
        }
    }

}