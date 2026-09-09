package com.itm.sistemabancario.structures;

public interface OperacionesEstructuras<T> {
    void crear(T dato);

    void buscarPorIndice(int index);

    void buscarPorValor(T dato);

    void actualizar(int index, T dato);

    void eliminar(int index);

}
