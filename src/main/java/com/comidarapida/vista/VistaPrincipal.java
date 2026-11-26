
package com.comidarapida.vista;

import javax.swing.*;
import java.awt.*;

import com.comidarapida.servicios.ServicioProductos;

public class VistaPrincipal extends JFrame {

    private ServicioProductos servicio;

    public VistaPrincipal(ServicioProductos servicio) {

        this.servicio = servicio;

        setTitle("Sistema de Comida Rapida");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(35, 35, 35));

        JLabel titulo = new JLabel("COMIDA RAPIDA");
        titulo.setBounds(280, 40, 300, 50);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);

        JButton pedidos = new JButton("Gestionar Pedidos");
        pedidos.setBounds(300, 150, 230, 45);

        JButton productos = new JButton("Productos");
        productos.setBounds(300, 220, 230, 45);

        JButton promociones = new JButton("Promociones");
        promociones.setBounds(300, 290, 230, 45);

        JButton caja = new JButton("Cierre de Caja");
        caja.setBounds(300, 360, 230, 45);

        JButton salir = new JButton("Salir");
        salir.setBounds(300, 430, 230, 45);

        productos.addActionListener(e -> {
            VistaProductos ventana = new VistaProductos(servicio);
            ventana.setVisible(true);
        });

        pedidos.addActionListener(e -> {
            VistaPedidos ventana = new VistaPedidos(servicio);
            ventana.setVisible(true);
        });

        salir.addActionListener(e -> dispose());

        add(titulo);
        add(pedidos);
        add(productos);
        add(promociones);
        add(caja);
        add(salir);

    }

}
