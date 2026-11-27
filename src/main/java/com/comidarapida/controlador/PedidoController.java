
package com.comidarapida.controlador;

import com.comidarapida.modelo.*;
import com.comidarapida.servicios.ServicioProductos;
import com.comidarapida.servicios.ServicioVentas;
import com.comidarapida.observer.*;

import java.util.ArrayList;

public class PedidoController {

    private ServicioProductos servicio;
    private ServicioVentas servicioVentas;

    private Pedido pedido;
    private ArrayList<Pedido> historialPedidos;

    private PedidoObservable observable;

    public PedidoController(
        ServicioProductos servicio,
        ServicioVentas servicioVentas,
        ArrayList<Pedido> historialPedidos
    ) {

        this.servicio = servicio;
        this.servicioVentas = servicioVentas;
        this.historialPedidos = historialPedidos;

        pedido = new Pedido();

        observable = new PedidoObservable();

        observable.agregarObservador(new ObservadorCocina());
        observable.agregarObservador(new ObservadorCaja());
        observable.agregarObservador(new ObservadorMesero());

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

        DetallePedido detalle = new DetallePedido(
            producto,
            cantidad,
            adicion,
            precioAdicion
        );

        pedido.agregarDetalle(detalle);

    }

    public ArrayList<DetallePedido> obtenerDetalles() {
        return pedido.getDetalles();
    }

    public double obtenerTotal() {
        return pedido.calcularTotal();
    }

    public String obtenerEstado() {
        return pedido.getEstado();
    }

    public void avanzarEstado() {

        String estado = pedido.getEstado();

        if (estado.equals("Pendiente")) {
            pedido.setEstado("En preparacion");
        }

        else if (estado.equals("En preparacion")) {
            pedido.setEstado("Listo");
        }

        else if (estado.equals("Listo")) {

            if (!servicioVentas.isCajaAbierta()) {
                throw new IllegalStateException(
                    "No se puede entregar: la caja está cerrada."
                );
            }

            boolean registrada = servicioVentas.registrarVenta(
                "Pedido #" + pedido.getId(),
                pedido.calcularTotal()
            );

            if (!registrada) {
                throw new IllegalStateException(
                    "No se pudo registrar la venta."
                );
            }

            pedido.setEstado("Entregado");

            historialPedidos.add(pedido);

        }

        else {
            return;
        }

        observable.notificar(
            pedido.getEstado(),
            "El pedido ha cambiado de estado"
        );

    }

    public ArrayList<Pedido> obtenerHistorial() {
        return new ArrayList<>(historialPedidos);
    }

}
