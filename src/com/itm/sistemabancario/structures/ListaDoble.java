package com.itm.sistemabancario.structures;

public class ListaDoble<T> {
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
        } else {
            tail.setSiguiente(nuevo);
            nuevo.setAnterior(tail);
        }
        tail = nuevo;
        tamano++;
    }

    public void recorrer() {
        NodoDoble<T> actual = head;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public void recorrerInverso() {
        NodoDoble<T> actual = tail;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getAnterior();
        }
    }

    public NodoDoble<T> buscar(T dato) {
        NodoDoble<T> actual = head;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public T eliminar(NodoDoble<T> nodo) {
        NodoDoble<T> antes = nodo.getAnterior();
        NodoDoble<T> despues = nodo.getSiguiente();
        if (antes == null) {
            head = despues;
        } else {
            antes.setSiguiente(despues);
        }
        if (despues == null) {
            tail = antes;
        } else {
            despues.setAnterior(antes);
        }
        nodo.setSiguiente(null);
        nodo.setAnterior(null);
        tamano--;
        return nodo.getDato();
    }

}