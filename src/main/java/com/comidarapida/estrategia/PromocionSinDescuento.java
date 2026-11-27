
package com.comidarapida.estrategia;

public class PromocionSinDescuento implements EstrategiaPromocion {

    @Override
    public double calcularDescuento(double precio) {
        return 0;
    }

    @Override
    public String obtenerDescripcion() {
        return "Hoy no hay promociones disponibles";
    }

}
