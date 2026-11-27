
package com.comidarapida;

import com.comidarapida.observer.*;

public class PruebaObserver {

    public static void main(String[] args) {

        PedidoObservable pedido = new PedidoObservable();

        pedido.agregarObservador(new ObservadorCocina());
        pedido.agregarObservador(new ObservadorCaja());
        pedido.agregarObservador(new ObservadorMesero());

        System.out.println("PRUEBA OBSERVER");
        System.out.println("----------------------");

        pedido.notificar(
            "En preparacion",
            "El pedido cambio de estado"
        );

        System.out.println("----------------------");
        System.out.println("PRUEBA EJECUTADA");

    }
}
