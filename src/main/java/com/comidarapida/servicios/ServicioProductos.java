
package com.comidarapida.servicios;

import com.comidarapida.modelo.Producto;
import java.util.ArrayList;

public class ServicioProductos {

    private ArrayList<Producto> productos;

    public ServicioProductos() {
        productos = new ArrayList<>();
    }

    public boolean registrarProducto(Producto producto) {

        if (producto == null) {
            return false;
        }

        if (producto.getNombre() == null ||
            producto.getNombre().trim().isEmpty()) {
            return false;
        }

        if (producto.getPrecio() <= 0) {
            return false;
        }

        productos.add(producto);

        return true;
    }

    public ArrayList<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    public boolean eliminarProducto(Producto producto) {
        return productos.remove(producto);
    }
}
