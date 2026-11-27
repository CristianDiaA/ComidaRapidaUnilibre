
package com.comidarapida.controlador;

import com.comidarapida.estrategia.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class PromocionController {

    private EstrategiaPromocion estrategia;

    public PromocionController() {
        seleccionarPromocion();
    }

    private void seleccionarPromocion() {

        DayOfWeek dia = LocalDate.now().getDayOfWeek();

        switch (dia) {

            case MONDAY:
                estrategia = new PromocionHamburguesa();
                break;

            case TUESDAY:
                estrategia = new PromocionPizza();
                break;

            case WEDNESDAY:
                estrategia = new PromocionBebidas();
                break;

            case THURSDAY:
                estrategia = new PromocionPersonalizada(
                    "Jueves: 15% de descuento en perros calientes",
                    0.15
                );
                break;

            case FRIDAY:
                estrategia = new PromocionPersonalizada(
                    "Viernes: 10% de descuento en salchipapas",
                    0.10
                );
                break;

            case SATURDAY:
                estrategia = new PromocionPersonalizada(
                    "Sabado: 15% de descuento en mazorcadas",
                    0.15
                );
                break;

            default:
                estrategia = new PromocionSinDescuento();
                break;

        }

    }

    public double calcularDescuento(double precio) {
        return estrategia.calcularDescuento(precio);
    }

    public String obtenerPromocion() {
        return estrategia.obtenerDescripcion();
    }

}
