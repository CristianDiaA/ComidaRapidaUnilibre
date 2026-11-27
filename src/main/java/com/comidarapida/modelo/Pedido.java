
package com.comidarapida.modelo;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {

    private static int contador = 1;

    private int id;
    private ArrayList<DetallePedido> detalles;
    private String estado;
    private LocalDateTime fecha;

    public Pedido() {

        id = contador++;
        detalles = new ArrayList<>();
        estado = "Pendiente";
        fecha = LocalDateTime.now();

    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getFechaTexto() {
        DateTimeFormatter formato =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return fecha.format(formato);
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
