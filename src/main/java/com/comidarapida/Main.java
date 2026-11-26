
package com.comidarapida;

import javax.swing.SwingUtilities;

import com.comidarapida.vista.VistaPrincipal;
import com.comidarapida.servicios.ServicioProductos;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ServicioProductos servicio = new ServicioProductos();

            VistaPrincipal ventana = new VistaPrincipal(servicio);

            ventana.setVisible(true);

        });

    }

}
