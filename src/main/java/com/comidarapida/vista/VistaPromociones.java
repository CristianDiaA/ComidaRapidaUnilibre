
package com.comidarapida.vista;

import javax.swing.*;
import java.awt.*;

import com.comidarapida.controlador.PromocionController;

public class VistaPromociones extends JFrame {

    private PromocionController controlador;

    private JTextField txtPrecio;
    private JLabel lblPromocion;
    private JLabel lblDescuento;
    private JLabel lblTotal;

    public VistaPromociones() {

        controlador = new PromocionController();

        setTitle("Promociones");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(40, 40, 40));

        JLabel titulo = new JLabel("PROMOCIONES DEL DIA");
        titulo.setBounds(150, 30, 400, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(Color.WHITE);

        lblPromocion = new JLabel(
            "<html>" + controlador.obtenerPromocion() + "</html>"
        );

        lblPromocion.setBounds(70, 110, 500, 50);
        lblPromocion.setForeground(Color.WHITE);
        lblPromocion.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel precio = new JLabel("Precio de la compra:");
        precio.setBounds(70, 190, 180, 30);
        precio.setForeground(Color.WHITE);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(250, 190, 180, 30);

        JButton calcular = new JButton("Calcular Descuento");
        calcular.setBounds(200, 240, 230, 40);

        lblDescuento = new JLabel("Descuento: $0");
        lblDescuento.setBounds(200, 310, 300, 30);
        lblDescuento.setForeground(Color.WHITE);

        lblTotal = new JLabel("Total: $0");
        lblTotal.setBounds(200, 350, 300, 30);
        lblTotal.setForeground(Color.WHITE);

        calcular.addActionListener(e -> calcularDescuento());

        add(titulo);
        add(lblPromocion);
        add(precio);
        add(txtPrecio);
        add(calcular);
        add(lblDescuento);
        add(lblTotal);

    }

    private void calcularDescuento() {

        try {

            double precio = Double.parseDouble(txtPrecio.getText());

            if (precio <= 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "El precio debe ser mayor que cero"
                );
                return;
            }

            double descuento =
                controlador.calcularDescuento(precio);

            double total = precio - descuento;

            lblDescuento.setText(
                "Descuento: $" + descuento
            );

            lblTotal.setText(
                "Total: $" + total
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Ingresa un precio valido"
            );

        }

    }

}
