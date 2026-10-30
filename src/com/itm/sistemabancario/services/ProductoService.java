package com.itm.sistemabancario.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.itm.sistemabancario.models.CatalogoProductos;
import com.itm.sistemabancario.models.ProductoBancario;
import com.itm.sistemabancario.structures.NodoDoble;

public class ProductoService {
    private static final String ARCHIVO_PRODUCTOS = "data/productos.txt";
    private static final String SEPARADOR = ";";

    // Un solo catálogo por Service: si se creara uno nuevo en cada método,
    // cada operación vería una lista distinta
    private final CatalogoProductos catalogo = new CatalogoProductos("Catálogo del banco");

    public ProductoService() {
        cargarProductos();
    }

    public void registrarProducto(String codigo, String nombre, String tipo, double tasaAnual) {
        ProductoBancario producto = new ProductoBancario(codigo, nombre, tipo, tasaAnual);
        catalogo.getProductos().insertarAlFinal(producto);
    }

    public String listarProductosEnOrden() {
        if (catalogo.getProductos().isEmpty()) {
            return "No hay productos en el catálogo";
        }
        return catalogo.getProductos().recorrerAdelante();
    }

    public String listarProductosEnOrdenInverso() {
        if (catalogo.getProductos().isEmpty()) {
            return "No hay productos en el catálogo";
        }
        return catalogo.getProductos().recorrerAtras();
    }

    // La View no usa try / catch: consulta antes de navegar o retirar
    public boolean hayProductoAnterior() {
        return catalogo.getProductos().hayAnterior();
    }

    public boolean hayProductoSiguiente() {
        return catalogo.getProductos().haySiguiente();
    }

    public boolean hayProductoSeleccionado() {
        return catalogo.getProductos().hayActual();
    }

    // El cursor vive en la lista: el Service no guarda posiciones
    public ProductoBancario verProductoAnterior() {
        return catalogo.getProductos().anterior();
    }

    public ProductoBancario verProductoSiguiente() {
        return catalogo.getProductos().siguiente();
    }

    public ProductoBancario retirarProducto() {
        return catalogo.getProductos().retirarActual();
    }

    public int contarProductos() {
        return catalogo.getProductos().size();
    }

    private void escribirProductos() throws IOException {
        File carpeta = new File(ARCHIVO_PRODUCTOS).getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        // se escribe de head a tail: si se guardara hacia atrás, el archivo quedaría invertido al cargar
        String texto = "";
        NodoDoble<ProductoBancario> actual = catalogo.getProductos().getHead();
        while (actual != null) {
            ProductoBancario producto = actual.getDato();
            texto += String.join(SEPARADOR,
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getTipo(),
                    String.valueOf(producto.getTasaAnual())) + "\n";
            actual = actual.getSiguiente();
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO_PRODUCTOS))) {
            escritor.write(texto);
        }
    }

    public String guardarProductos() {
        try {
            escribirProductos();
            return "Se guardaron " + catalogo.getProductos().size() + " productos en " + ARCHIVO_PRODUCTOS;
        } catch (IOException e) {
            return "No se pudo guardar: " + e.getMessage();
        }
    }

    public String cargarProductos() {
        File archivo = new File(ARCHIVO_PRODUCTOS);

        // sin vaciar la lista, cargar dos veces duplicaría todos los productos
        while (catalogo.getProductos().getHead() != null) {
            catalogo.getProductos().eliminar(catalogo.getProductos().getHead());
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(SEPARADOR);
                if (campos.length != 4) {
                    continue;
                }
                // insertarAlFinal conserva el orden del archivo y enlaza anterior y siguiente
                registrarProducto(campos[0], campos[1], campos[2], Double.parseDouble(campos[3]));
            }
        } catch (FileNotFoundException e) {
            return "No existe " + ARCHIVO_PRODUCTOS + "; se inicia con el catálogo vacío";
        } catch (IOException e) {
            return "No se pudo leer: " + e.getMessage();
        }
        return "Se cargaron " + catalogo.getProductos().size() + " productos";
    }
}
