
package com.comidarapida.observer;

import java.util.ArrayList;

public class PedidoObservable {

    private ArrayList<ObservadorPedido> observadores;

    public PedidoObservable() {
        observadores = new ArrayList<>();
    }

    public void agregarObservador(ObservadorPedido observador) {
        observadores.add(observador);
    }

    public void notificar(String estado, String mensaje) {

        for (ObservadorPedido observador : observadores) {

            observador.actualizar(estado, mensaje);

        }

    }

}
