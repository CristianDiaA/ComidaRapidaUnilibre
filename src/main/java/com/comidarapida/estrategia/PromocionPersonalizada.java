
package com.comidarapida.estrategia;

public class PromocionPersonalizada implements EstrategiaPromocion {

    private String descripcion;
    private double porcentaje;

    public PromocionPersonalizada(
        String descripcion,
        double porcentaje
    ) {
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
    }

    @Override
    public double calcularDescuento(double precio) {
        return precio * porcentaje;
    }

    @Override
    public String obtenerDescripcion() {
        return descripcion;
    }

}
