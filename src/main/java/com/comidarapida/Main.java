
package com.comidarapida;

import javax.swing.SwingUtilities;
import java.util.ArrayList;

import com.comidarapida.vista.VistaPrincipal;
import com.comidarapida.servicios.ServicioProductos;
import com.comidarapida.servicios.ServicioVentas;
import com.comidarapida.modelo.Pedido;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ServicioProductos servicioProductos =
                new ServicioProductos();

            ServicioVentas servicioVentas =
                new ServicioVentas();

            ArrayList<Pedido> historialPedidos =
                new ArrayList<>();

            VistaPrincipal ventana = new VistaPrincipal(
                servicioProductos,
                servicioVentas,
                historialPedidos
            );

            ventana.setVisible(true);

        });

    }

}