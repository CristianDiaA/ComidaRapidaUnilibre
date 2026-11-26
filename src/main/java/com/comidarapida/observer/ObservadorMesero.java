
package com.comidarapida.observer;

public class ObservadorMesero implements ObservadorPedido {

    @Override
    public void actualizar(String estado, String mensaje) {

        System.out.println(
            "[MESERO] " + mensaje + " - Estado: " + estado
        );

    }

}
