
package com.comidarapida.modelo;

import java.util.ArrayList;

public class Pedido {

    private ArrayList<DetallePedido> detalles;
    private String estado;

    public Pedido() {

        detalles = new ArrayList<>();
        estado = "Pendiente";

    }

    public void agregarDetalle(DetallePedido detalle) {
        detalles.add(detalle);
    }

    public ArrayList<DetallePedido> getDetalles() {
        return new ArrayList<>(detalles);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double calcularTotal() {

        double total = 0;

        for (DetallePedido detalle : detalles) {
            total += detalle.getSubtotal();
        }

        return total;
    }

}
