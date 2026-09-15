package com.itm.sistemabancario.structures;

public class ListaSimple<T> implements OperacionesEstructuras<T> {
    private Nodo<T> head;
    private int tamano;

    public ListaSimple() {
        this.head = null;
        this.tamano = 0;
    }

    public boolean estaVacia() {
        return head == null;
    }

    public int getTamano() {
        return tamano;
    }

    public void insertarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(head);
        head = nuevo;
        tamano++;
    }

    public void insertarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            head = nuevo;
        } else {
            Nodo<T> actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamano++;
    }

    public void insertarEnPosicion(int indice, T dato) {
        if (indice < 0 || indice > tamano) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + indice);
        }
        if (indice == 0) {
            this.insertarInicio(dato);
            return;
        }
        if (indice == tamano) {
            this.insertarFinal(dato);
            return;
        }

        Nodo<T> anterior = head;
        for (int i = 0; i < indice - 1; i++) {
            anterior = anterior.getSiguiente();
        }

        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(anterior.getSiguiente());
        anterior.setSiguiente(nuevo);
        tamano++;
    }

    @Override
    public void crear(int indice, T dato) {
        insertarEnPosicion(indice, dato);
    }

    @Override
    public void buscarPorIndice(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorIndice'");
    }

    @Override
    public void buscarPorValor(T dato) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorValor'");
    }

    @Override
    public void actualizar(int index, T dato) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public void eliminar(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    public Nodo<T> getHead() {
        return head;
    }
}
