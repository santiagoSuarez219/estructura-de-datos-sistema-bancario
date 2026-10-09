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
        System.out.println("Dato no encontrado");
        return null;
    }

    public void listarMovimientosCuenta(Cuenta cuenta) {
        Pila<Movimiento> movimientos = cuenta.getMovimientos();
        if (movimientos.isEmpty()) {
            return;
        }
        Nodo<Movimiento> actual = movimientos.getTope();
        for (int i = 0; i < movimientos.size(); i++) {
            Movimiento movimientoActual = actual.getDato();
            System.out.println("Tipo: " + movimientoActual.getTipo() + " Monto: " +
                    movimientoActual.getMonto() + " Fecha: " + movimientoActual.getFecha());
            actual = actual.getSiguiente();
        }
    }

    public void registrarOperacion(String tipo, double monto, Cliente cliente, int numeroCuenta) {
        Cuenta cuenta = buscarCuentaPorNumero(cliente, numeroCuenta);
        if (tipo == "Deposito") {
            cuenta.depositar(numeroCuenta);
        } else if (tipo == "Retiro") {
            cuenta.retirar(numeroCuenta);
        } else {
            throw new IllegalArgumentException("Tipo de movimiento invalido");
        }
        Movimiento movimiento = new Movimiento(tipo, monto);
        cuenta.getMovimientos().push(movimiento);
    }

    public Movimiento deshacerUltimaOperacion(Cliente cliente, int numeroCuenta) {
        Cuenta cuenta = buscarCuentaPorNumero(cliente, numeroCuenta);
        Movimiento ultimoMovimiento = cuenta.getMovimientos().pop();
        if (ultimoMovimiento.getTipo() == "Deposito") {
            cuenta.retirar(ultimoMovimiento.getMonto());
        } else if (ultimoMovimiento.getTipo() == "Retiro") {
            cuenta.depositar(ultimoMovimiento.getMonto());
        }
        return ultimoMovimiento;
    }

    public Movimiento consultarUltimaOperacion(Cuenta cuenta) {
        Movimiento ultimoMovimiento = cuenta.getMovimientos().peek();
        return ultimoMovimiento;
    }

    public boolean sinOperacionesPorDeshacer(Cuenta cuenta) {
        return cuenta.getMovimientos().isEmpty();
    }

    public int contarOperaciones(Cuenta cuenta) {
        return cuenta.getMovimientos().size();
    }

}
