
package com.comidarapida.servicios;

import com.comidarapida.modelo.Venta;
import java.util.ArrayList;
import java.time.LocalDate;

public class ServicioVentas {

    private ArrayList<Venta> ventas;
    private boolean cajaAbierta;

    public ServicioVentas() {

        ventas = new ArrayList<>();
        cajaAbierta = true;

    }

    public boolean registrarVenta(String descripcion, double total) {

        if (!cajaAbierta || descripcion == null ||
            descripcion.trim().isEmpty() || total <= 0) {

            return false;

        }

        ventas.add(new Venta(descripcion, total));

        return true;

    }

    public ArrayList<Venta> obtenerVentas() {
        return new ArrayList<>(ventas);
    }

    public double calcularTotalVentas() {

        double total = 0;

        for (Venta venta : ventas) {
            total += venta.getTotal();
        }

        return total;

    }

    public int contarVentas() {
        return ventas.size();
    }

    public ArrayList<Venta> obtenerVentasDelDia() {

        ArrayList<Venta> resultado = new ArrayList<>();

        for (Venta venta : ventas) {

            if (venta.getFecha().toLocalDate()
                .equals(LocalDate.now())) {

                resultado.add(venta);

            }

        }

        return resultado;

    }

    public double calcularTotalDelDia() {

        double total = 0;

        for (Venta venta : obtenerVentasDelDia()) {
            total += venta.getTotal();
        }

        return total;

    }

    public boolean isCajaAbierta() {
        return cajaAbierta;
    }

    public void cerrarCaja() {
        cajaAbierta = false;
    }

}
