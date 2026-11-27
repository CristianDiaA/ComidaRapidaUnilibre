
package com.comidarapida.controlador;

import com.comidarapida.modelo.Venta;
import com.comidarapida.servicios.ServicioVentas;
import java.util.ArrayList;

public class CajaController {

    private ServicioVentas servicio;

    public CajaController(ServicioVentas servicio) {
        this.servicio = servicio;
    }

    public boolean registrarVenta(String descripcion, double total) {

        return servicio.registrarVenta(descripcion, total);

    }

    public ArrayList<Venta> obtenerVentas() {
        return servicio.obtenerVentasDelDia();
    }

    public double obtenerTotal() {
        return servicio.calcularTotalDelDia();
    }

    public int obtenerCantidadVentas() {
        return servicio.obtenerVentasDelDia().size();
    }

    public boolean estaAbierta() {
        return servicio.isCajaAbierta();
    }

    public void cerrarCaja() {
        servicio.cerrarCaja();
    }

}
