
package com.comidarapida.builder;

import com.comidarapida.modelo.*;
import java.util.ArrayList;

public class ComboBuilder {

    private String nombre;
    private ArrayList<DetallePedido> productos;

    public ComboBuilder() {

        nombre = "Combo personalizado";
        productos = new ArrayList<>();

    }

    public ComboBuilder establecerNombre(String nombre) {

        this.nombre = nombre;

        return this;
    }

    public ComboBuilder agregarProducto(
        Producto producto,
        int cantidad,
        String adicion,
        double precioAdicion
    ) {

        DetallePedido detalle = new DetallePedido(
            producto,
            cantidad,
            adicion,
            precioAdicion
        );

        productos.add(detalle);

        return this;
    }

    public Combo construir() {

        return new Combo(nombre, productos);

    }

}
