package com.comidarapida.observer;

public class ObservadorCaja implements ObservadorPedido {

    @Override
    public void actualizar(String estado, String mensaje) {

        System.out.println(
            "[CAJA] " + mensaje + " - Estado: " + estado
        );

    }

}
