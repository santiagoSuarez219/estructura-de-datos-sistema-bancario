package com.itm.sistemabancario.structures;

public class Pila<T> {
    private Nodo<T> tope;
    private int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    public void push(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.setSiguiente(this.tope);
        this.tope = nuevo;
        this.tamanio++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        T valor = this.tope.getDato();
        this.tope = this.tope.getSiguiente();
        this.tamanio--;
        return valor;
    }

    public boolean isEmpty() {
        return tope == null;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return this.tope.getDato();
    }

    public int size() {
        return tamanio;
    }

    public Nodo<T> getTope() {
        return tope;
    }
}
