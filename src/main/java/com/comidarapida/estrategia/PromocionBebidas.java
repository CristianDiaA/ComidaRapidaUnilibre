
package com.comidarapida.estrategia;

public class PromocionBebidas implements EstrategiaPromocion {

    @Override
    public double calcularDescuento(double precio) {
        return precio * 0.10;
    }

    @Override
    public String obtenerDescripcion() {
        return "Miercoles: 10% de descuento en bebidas";
    }

}
