
package com.comidarapida.modelo;

public class DetallePedido {

    private Producto producto;
    private int cantidad;
    private String adicion;
    private double precioAdicion;

    public DetallePedido(
        Producto producto,
        int cantidad,
        String adicion,
        double precioAdicion
    ) {

        this.producto = producto;
        this.cantidad = cantidad;
        this.adicion = adicion;
        this.precioAdicion = precioAdicion;

    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getAdicion() {
        return adicion;
    }

    public double getSubtotal() {

        return (producto.getPrecio() + precioAdicion) * cantidad;

    }

}
