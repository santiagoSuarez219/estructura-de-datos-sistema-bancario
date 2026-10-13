package com.itm.sistemabancario.services;

import com.itm.sistemabancario.models.Cliente;
import com.itm.sistemabancario.models.Cuenta;
import com.itm.sistemabancario.models.CuentaAhorros;
import com.itm.sistemabancario.models.CuentaCorriente;
import com.itm.sistemabancario.models.Movimiento;
import com.itm.sistemabancario.structures.Nodo;
import com.itm.sistemabancario.structures.Pila;

public class CuentaService {
    public CuentaAhorros crearCuentaAhorros(Cliente cliente) {
        CuentaAhorros nuevaCuenta = new CuentaAhorros(cliente);
        cliente.getCuentas().crear(0, nuevaCuenta);
        return nuevaCuenta;
    }

    public CuentaCorriente crearCuentaCorriente(Cliente cliente, double cupoSobregiro) {
        CuentaCorriente nuevaCuenta = new CuentaCorriente(cliente, cupoSobregiro);
        cliente.getCuentas().crear(0, nuevaCuenta);
        return nuevaCuenta;
    }

    public Cuenta buscarCuentaPorNumero(Cliente cliente, int numeroCuenta) {
        Nodo<Cuenta> cuentaActual = cliente.getCuentas().getHead();
        while (cuentaActual != null) {
            if (cuentaActual.getDato().getNumeroCuenta() == numeroCuenta) {
                return cuentaActual.getDato();
            }
            cuentaActual = cuentaActual.getSiguiente();
        }
        return null;
    }

    public boolean eliminarCuenta(Cliente cliente, Cuenta cuenta) {
        if (cuenta == null) {
            return false;
        }
        return cliente.getCuentas().eliminar(cuenta);
    }

    public String listarCuentas(Cliente cliente) {
        StringBuilder texto = new StringBuilder();
        Nodo<Cuenta> actual = cliente.getCuentas().getHead();
        while (actual != null) {
            Cuenta cuenta = actual.getDato();
            texto.append("Cuenta: ").append(cuenta.getNumeroCuenta())
                    .append(" Saldo: ").append(cuenta.getSaldo())
                    .append(" Apertura: ").append(cuenta.getFechaApertura()).append("\n");
            actual = actual.getSiguiente();
        }
        return texto.toString();
    }

    private Cuenta obtenerCuenta(Cliente cliente, int numeroCuenta) {
        Cuenta cuenta = buscarCuentaPorNumero(cliente, numeroCuenta);
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta " + numeroCuenta + " no existe para el cliente");
        }
        return cuenta;
    }

    public void registrarOperacion(String tipo, double monto, Cliente cliente, int numeroCuenta) {
        Cuenta cuenta = obtenerCuenta(cliente, numeroCuenta);
        if ("Deposito".equalsIgnoreCase(tipo)) {
            cuenta.depositar(monto);
        } else if ("Retiro".equalsIgnoreCase(tipo)) {
            cuenta.retirar(monto);
        } else {
            throw new IllegalArgumentException("Tipo de movimiento invalido");
        }
        Movimiento movimiento = new Movimiento(tipo, monto);
        cuenta.getMovimientos().push(movimiento);
    }

    public Movimiento deshacerUltimaOperacion(Cliente cliente, int numeroCuenta) {
        Cuenta cuenta = obtenerCuenta(cliente, numeroCuenta);
        Movimiento ultimoMovimiento = cuenta.getMovimientos().pop();
        if ("Deposito".equalsIgnoreCase(ultimoMovimiento.getTipo())) {
            cuenta.retirar(ultimoMovimiento.getMonto());
        } else if ("Retiro".equalsIgnoreCase(ultimoMovimiento.getTipo())) {
            cuenta.depositar(ultimoMovimiento.getMonto());
        }
        return ultimoMovimiento;
    }

    public Movimiento consultarUltimaOperacion(Cliente cliente, int numeroCuenta) {
        Cuenta cuenta = obtenerCuenta(cliente, numeroCuenta);
        return cuenta.getMovimientos().peek();
    }

    public boolean sinOperacionesPorDeshacer(Cliente cliente, int numeroCuenta) {
        Cuenta cuenta = obtenerCuenta(cliente, numeroCuenta);
        return cuenta.getMovimientos().isEmpty();
    }

    public int contarOperaciones(Cliente cliente, int numeroCuenta) {
        Cuenta cuenta = obtenerCuenta(cliente, numeroCuenta);
        return cuenta.getMovimientos().size();
    }

    public String listarMovimientos(Cliente cliente, int numeroCuenta) {
        Cuenta cuenta = obtenerCuenta(cliente, numeroCuenta);
        Pila<Movimiento> original = cuenta.getMovimientos();

        if (original.isEmpty()) {
            return "No hay operaciones por deshacer";
        }
        Pila<Movimiento> auxiliar = new Pila<>();
        StringBuilder texto = new StringBuilder();
        int numero = 1;

        while (!original.isEmpty()) {
            Movimiento movimientoActual = original.pop();
            texto.append(numero++).append(". ").append(movimientoActual.getTipo()).append(",")
                    .append(movimientoActual.getMonto()).append(",").append(movimientoActual.getFecha()).append("\n");
            auxiliar.push(movimientoActual);
        }

        while (!auxiliar.isEmpty()) {
            original.push(auxiliar.pop());
        }

        return texto.toString();
    }

}
