
package com.comidarapida.controlador;

import com.comidarapida.modelo.Producto;
import com.comidarapida.servicios.ServicioProductos;
import java.util.ArrayList;

public class ProductoController {

    private ServicioProductos servicio;

    public ProductoController(ServicioProductos servicio) {
        this.servicio = servicio;
    }

    public boolean agregarProducto(
        String nombre, double precio, String categoria
    ) {

        Producto producto = new Producto(
            nombre, precio, categoria
        );

        return servicio.registrarProducto(producto);
    }

    public ArrayList<Producto> obtenerProductos() {
        return servicio.listarProductos();
    }

}
