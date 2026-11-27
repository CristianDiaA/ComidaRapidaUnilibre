
package com.comidarapida;

import com.comidarapida.estrategia.*;

public class PruebaStrategy {

    public static void main(String[] args) {

        EstrategiaPromocion promocion =
            new PromocionHamburguesa();

        double precio = 20000;
        double descuento =
            promocion.calcularDescuento(precio);

        double total = precio - descuento;

        System.out.println("PRUEBA STRATEGY");
        System.out.println("----------------------");
        System.out.println(promocion.obtenerDescripcion());
        System.out.println("Precio: $" + precio);
        System.out.println("Descuento: $" + descuento);
        System.out.println("Total: $" + total);

        if (descuento == 2000 && total == 18000) {
            System.out.println("RESULTADO: PRUEBA EXITOSA");
        } else {
            System.out.println("RESULTADO: PRUEBA FALLIDA");
        }

    }
}
