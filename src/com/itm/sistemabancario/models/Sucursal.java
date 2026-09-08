package com.itm.sistemabancario.models;

public class Sucursal {
    private String nombre;
    private String ciudad;
    private Cliente[] clientes;

    public Sucursal(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.clientes = new Cliente[0];
    }

    public void agregarCliente(Cliente cliente) {
        Cliente[] nuevosClientes = new Cliente[this.clientes.length + 1];
        for (int i = 0; i < this.clientes.length; i++) {
            nuevosClientes[i] = this.clientes[i];
        }
        nuevosClientes[this.clientes.length] = cliente;
        this.clientes = nuevosClientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Cliente[] getClientes() {
        return clientes;
    }
}
