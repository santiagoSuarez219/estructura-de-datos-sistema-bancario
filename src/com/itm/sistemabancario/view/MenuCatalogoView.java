package com.itm.sistemabancario.view;

import com.itm.sistemabancario.services.ProductoService;
import com.itm.sistemabancario.utils.ConsoleUtils;

public class MenuCatalogoView {
    private ProductoService productoService;

    public MenuCatalogoView(ProductoService productoService) {
        this.productoService = productoService;
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = ConsoleUtils.leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 -> registrar();
                case 2 -> System.out.println(productoService.listarProductosEnOrden());
                case 3 -> System.out.println(productoService.listarProductosEnOrdenInverso());
                case 4 -> verAnterior();
                case 5 -> verSiguiente();
                case 6 -> retirar();
                case 7 -> guardarTodo();
                case 0 -> guardarTodo();
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("1. Registrar producto en el catalogo");
        System.out.println("2. Listar del primero al ultimo");
        System.out.println("3. Listar del ultimo al primero");
        System.out.println("4. Hojear producto anterior");
        System.out.println("5. Hojear producto siguiente");
        System.out.println("6. Retirar el producto que estoy mirando");
        System.out.println("7. Guardar");
        System.out.println("0. Guardar y salir");
    }

    private void registrar() {
        String codigo = ConsoleUtils.leerTexto("Codigo: ");
        String nombre = ConsoleUtils.leerTexto("Nombre: ");
        String tipo = ConsoleUtils.leerTexto("Tipo: ");
        double tasaAnual = ConsoleUtils.leerDecimal("Tasa anual: ");
        productoService.registrarProducto(codigo, nombre, tipo, tasaAnual);
        System.out.println("Producto registrado exitosamente.");
    }

    private void verAnterior() {
        if (productoService.contarProductos() == 0) {
            System.out.println("No hay productos en el catalogo.");
        } else if (!productoService.hayProductoAnterior()) {
            System.out.println("No hay producto anterior.");
        } else {
            System.out.println(productoService.verProductoAnterior());
        }
    }

    private void verSiguiente() {
        if (productoService.contarProductos() == 0) {
            System.out.println("No hay productos en el catalogo.");
        } else if (!productoService.hayProductoSiguiente()) {
            System.out.println("No hay producto siguiente.");
        } else {
            System.out.println(productoService.verProductoSiguiente());
        }
    }

    private void retirar() {
        if (!productoService.hayProductoSeleccionado()) {
            System.out.println("No hay producto seleccionado. Hojee uno primero.");
        } else {
            System.out.println("Producto retirado: " + productoService.retirarProducto());
        }
    }

    private void guardarTodo() {
        System.out.println(productoService.guardarProductos());
    }
}
