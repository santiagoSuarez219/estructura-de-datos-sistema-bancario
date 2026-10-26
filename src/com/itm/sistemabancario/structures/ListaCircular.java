package com.itm.sistemabancario.structures;

public class ListaCircular<T> {
    private NodoDoble<T> head;
    private NodoDoble<T> tail;
    private int tamano;

    public boolean estaVacia() {
        return head == null;
    }

    public int getTamano() {
        return tamano;
    }

    public void insertarFinal(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (estaVacia()) {
            head = nuevo;
            tail = nuevo;
        } else {
            tail.setSiguiente(nuevo);
            nuevo.setAnterior(tail);
            tail = nuevo;
        }
        tail.setSiguiente(head);
        head.setAnterior(tail);
        tamano++;
    }

    public void recorrerConLimite(int maximo) {
        NodoDoble<T> actual = head;
        int pasos = 0;
        while (actual != null && pasos < maximo) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
            pasos++;
        }
    }

    public void recorrer() {
        if (estaVacia()) {
            return;
        }
        NodoDoble<T> actual = head;
        do {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        } while (actual != head);
    }

}
