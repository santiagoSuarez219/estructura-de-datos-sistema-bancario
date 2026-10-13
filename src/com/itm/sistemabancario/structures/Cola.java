package com.itm.sistemabancario.structures;

import java.util.NoSuchElementException;

public class Cola<T> {
    private Nodo<T> front;
    private Nodo<T> back;
    private int size;

    public Cola() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    public Nodo<T> getFront() {
        return front;
    }

    public void setFront(Nodo<T> front) {
        this.front = front;
    }

    public Nodo<T> getBack() {
        return back;
    }

    public void setBack(Nodo<T> back) {
        this.back = back;
    }

    // enqueue
    public void enqueue(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (isEmpty()) {
            this.front = nuevoNodo;
        } else {
            this.back.setSiguiente(nuevoNodo);
        }
        this.back = nuevoNodo;
        this.size++;
    }

    // dequeue
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola esta vacia");
        }
        T dato = this.front.getDato();
        this.front = this.front.getSiguiente();
        if (this.size == 1) {
            this.back = null;
        }
        this.size--;
        return dato;
    }

    // front
    public T front() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola esta vacia");
        }
        return this.front.getDato();
    }

    // size
    public int size() {
        return this.size;
    }

    // isEmpty
    public boolean isEmpty() {
        return this.front == null;
    }
}
