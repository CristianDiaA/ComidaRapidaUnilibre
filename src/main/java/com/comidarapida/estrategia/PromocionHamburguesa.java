
package com.comidarapida.estrategia;

public class PromocionHamburguesa implements EstrategiaPromocion {

    @Override
    public double calcularDescuento(double precio) {
        return precio * 0.10;
    }

    @Override
    public String obtenerDescripcion() {
        return "Lunes: 10% de descuento en hamburguesas";
    }

}
