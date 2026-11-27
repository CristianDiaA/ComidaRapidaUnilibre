
package com.comidarapida.vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import com.comidarapida.servicios.ServicioProductos;
import com.comidarapida.servicios.ServicioVentas;
import com.comidarapida.modelo.Pedido;

public class VistaPrincipal extends JFrame {

    private ServicioProductos servicio;
    private ServicioVentas servicioVentas;
    private ArrayList<Pedido> historialPedidos;

    public VistaPrincipal(
        ServicioProductos servicio,
        ServicioVentas servicioVentas,
        ArrayList<Pedido> historialPedidos
    ) {

        this.servicio = servicio;
        this.servicioVentas = servicioVentas;
        this.historialPedidos = historialPedidos;

        setTitle("Sistema de Comida Rapida");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(35, 35, 35));

        JLabel titulo = new JLabel("COMIDA RAPIDA");
        titulo.setBounds(290, 40, 300, 50);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);

        ImageIcon banana = new ImageIcon("resources/banana.gif");

        if (banana.getIconWidth() < 0) {
            throw new IllegalStateException(
                "No se pudo cargar resources/banana.gif"
            );
        }

        JLabel bananaIzquierda = new JLabel(banana);
        bananaIzquierda.setBounds(250, 44, 33, 35);

        JLabel bananaDerecha = new JLabel(banana);
        bananaDerecha.setBounds(546, 44, 33, 35);

        JButton pedidos = new JButton("Gestionar Pedidos");
        pedidos.setBounds(300, 150, 230, 45);

        JButton productos = new JButton("Productos");
        productos.setBounds(300, 220, 230, 45);

        JButton promociones = new JButton("Promociones");
        promociones.setBounds(300, 290, 230, 45);

        JButton caja = new JButton("Cierre de Caja");
        caja.setBounds(300, 360, 230, 45);

        JButton salir = new JButton("Salir");
        salir.setBounds(300, 500, 230, 45);

        JButton historial = new JButton("Historial de Pedidos");
        historial.setBounds(300, 430, 230, 45);

        historial.addActionListener(e -> {
        VistaHistorialPedidos ventana =
        new VistaHistorialPedidos(historialPedidos);

            ventana.setVisible(true);
        });

        productos.addActionListener(e -> {
            VistaProductos ventana = new VistaProductos(servicio);
            ventana.setVisible(true);
        });

        pedidos.addActionListener(e -> {
            VistaPedidos ventana = new VistaPedidos(
                servicio,
                servicioVentas,
                historialPedidos
            );
            ventana.setVisible(true);
        });

        promociones.addActionListener(e -> {
            VistaPromociones ventana = new VistaPromociones();
            ventana.setVisible(true);
        });

        caja.addActionListener(e -> {
            VistaCaja ventana = new VistaCaja(servicioVentas);
            ventana.setVisible(true);
        });

        salir.addActionListener(e -> dispose());

        add(titulo);
        add(bananaIzquierda);
        add(bananaDerecha);
        add(pedidos);
        add(productos);
        add(promociones);
        add(caja);
        add(salir);
        add(historial);

    }

}