
package com.comidarapida.estrategia;

public class PromocionPizza implements EstrategiaPromocion {

    @Override
    public double calcularDescuento(double precio) {
        return precio * 0.15;
    }

    @Override
    public String obtenerDescripcion() {
        return "Martes: 15% de descuento en pizzas";
    }

}
