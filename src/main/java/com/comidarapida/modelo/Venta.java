
package com.comidarapida.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venta {

    private String descripcion;
    private double total;
    private LocalDateTime fecha;

    public Venta(String descripcion, double total) {

        this.descripcion = descripcion;
        this.total = total;
        this.fecha = LocalDateTime.now();

    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getFechaTexto() {

        DateTimeFormatter formato =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return fecha.format(formato);

    }

}
