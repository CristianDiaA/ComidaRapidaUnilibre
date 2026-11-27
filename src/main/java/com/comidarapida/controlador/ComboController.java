
package com.comidarapida.controlador;

import com.comidarapida.modelo.*;
import com.comidarapida.builder.ComboBuilder;
import com.comidarapida.servicios.ServicioProductos;

import java.util.ArrayList;

public class ComboController {

    private ServicioProductos servicio;
    private ComboBuilder builder;

    public ComboController(ServicioProductos servicio) {

        this.servicio = servicio;
        builder = new ComboBuilder();

    }

    public ArrayList<Producto> obtenerProductos() {
        return servicio.listarProductos();
    }

    public void agregarProducto(
        Producto producto,
        int cantidad,
        String adicion,
        double precioAdicion
    ) {

        builder.agregarProducto(
            producto,
            cantidad,
            adicion,
            precioAdicion
        );

    }

    public Combo construirCombo(String nombre) {

        return builder
            .establecerNombre(nombre)
            .construir();

    }

    public void reiniciarBuilder() {
        builder = new ComboBuilder();
    }

}
