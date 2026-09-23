package com.itm.sistemabancario.models;

import com.itm.sistemabancario.structures.ListaSimple;

public class Cliente {
    private String identificacion;
    private String nombre;
    private String telefono;
    private String direccion;
    private ListaSimple<Cuenta> cuentas;
    private AsesorFinanciero asesor;

    public Cliente(String identificacion, String nombre, String telefono, String direccion) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cuentas = new ListaSimple<Cuenta>();
    }

    public void trasladarA(Sucursal sucursal) {
        sucursal.agregarCliente(this);
    }

    public void asignarAsesor(AsesorFinanciero asesor) {
        this.asesor = asesor;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public ListaSimple<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public AsesorFinanciero getAsesor() {
        return asesor;
    }
}
