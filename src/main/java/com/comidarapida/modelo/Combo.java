
package com.comidarapida.modelo;

import java.util.ArrayList;

public class Combo {

    private String nombre;
    private ArrayList<DetallePedido> productos;

    public Combo(String nombre, ArrayList<DetallePedido> productos) {

        this.nombre = nombre;
        this.productos = new ArrayList<>(productos);

    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<DetallePedido> getProductos() {
        return new ArrayList<>(productos);
    }

    public double calcularTotal() {

        double total = 0;

        for (DetallePedido detalle : productos) {
            total += detalle.getSubtotal();
        }

        return total;
    }

}
