package com.itm.sistemabancario.models;

public class Cliente {
    private String identificacion;
    private String nombre;
    private String telefono;
    private String direccion;
    private Cuenta[] cuentas;
    private AsesorFinanciero asesor;

    public Cliente(String identificacion, String nombre, String telefono, String direccion) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cuentas = new Cuenta[0];
    }

    public void agregarCuenta(Cuenta cuenta) {
        Cuenta[] nuevasCuentas = new Cuenta[this.cuentas.length + 1];
        for (int i = 0; i < this.cuentas.length; i++) {
            nuevasCuentas[i] = this.cuentas[i];
        }
        nuevasCuentas[this.cuentas.length] = cuenta;
        this.cuentas = nuevasCuentas;
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

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Cuenta[] getCuentas() {
        return cuentas;
    }

    public AsesorFinanciero getAsesor() {
        return asesor;
    }
}
