
package com.comidarapida.observer;

public class ObservadorCocina implements ObservadorPedido {

    @Override
    public void actualizar(String estado, String mensaje) {

        System.out.println(
            "[COCINA] " + mensaje + " - Estado: " + estado
        );

    }

}
