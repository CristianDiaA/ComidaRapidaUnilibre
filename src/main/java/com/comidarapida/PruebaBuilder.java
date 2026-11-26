
package com.comidarapida;

import com.comidarapida.modelo.*;
import com.comidarapida.builder.ComboBuilder;

public class PruebaBuilder {

    public static void main(String[] args) {

        Producto hamburguesa = new Producto(
            "Hamburguesa",
            15000,
            "Hamburguesa"
        );

        Producto bebida = new Producto(
            "Gaseosa",
            5000,
            "Bebida"
        );

        Combo combo = new ComboBuilder()
            .establecerNombre("Combo Especial")
            .agregarProducto(
                hamburguesa, 1, "Queso", 2000
            )
            .agregarProducto(
                bebida, 1, "Sin adicion", 0
            )
            .construir();

        System.out.println("COMBO: " + combo.getNombre());

        System.out.println(
            "TOTAL: $" + combo.calcularTotal()
        );

    }

}
