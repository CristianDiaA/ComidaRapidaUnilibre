
package com.comidarapida;

import com.comidarapida.modelo.*;
import com.comidarapida.builder.ComboBuilder;

public class PruebaBuilder {

    public static void main(String[] args) {

        Producto hamburguesa = new Producto(
            "Hamburguesa", 15000, "Hamburguesa"
        );

        Producto bebida = new Producto(
            "Gaseosa", 5000, "Bebida"
        );

        Combo combo = new ComboBuilder()
            .establecerNombre("Combo Especial")
            .agregarProducto(hamburguesa, 1, "Queso", 2000)
            .agregarProducto(bebida, 1, "Sin adicion", 0)
            .construir();

        double esperado = 22000;
        double obtenido = combo.calcularTotal();

        System.out.println("PRUEBA BUILDER");
        System.out.println("----------------------");
        System.out.println("Combo: " + combo.getNombre());
        System.out.println("Total esperado: $" + esperado);
        System.out.println("Total obtenido: $" + obtenido);

        if (obtenido == esperado) {
            System.out.println("RESULTADO: PRUEBA EXITOSA");
        } else {
            System.out.println("RESULTADO: PRUEBA FALLIDA");
        }

    }
}
