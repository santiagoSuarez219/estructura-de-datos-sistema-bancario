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
}
