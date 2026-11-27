
package com.comidarapida.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.comidarapida.modelo.*;
import com.comidarapida.controlador.PedidoController;
import com.comidarapida.servicios.ServicioProductos;

public class VistaPedidos extends JFrame {

    private PedidoController controlador;

    private JComboBox<Producto> cbProductos;
    private JComboBox<String> cbAdiciones;
    private JSpinner spCantidad;

    private DefaultTableModel modelo;
    private JLabel lblTotal;
    private JLabel lblEstado;

    public VistaPedidos(ServicioProductos servicio) {

        controlador = new PedidoController(servicio);

        setTitle("Gestion de Pedidos");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        getContentPane().setBackground(new Color(40, 40, 40));

        JLabel titulo = new JLabel("GESTION DE PEDIDOS");
        titulo.setBounds(300, 20, 400, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(Color.WHITE);

        JLabel producto = new JLabel("Producto:");
        producto.setBounds(50, 100, 100, 30);
        producto.setForeground(Color.WHITE);

        cbProductos = new JComboBox<>();

        for (Producto p : controlador.obtenerProductos()) {
            cbProductos.addItem(p);
        }

        cbProductos.setBounds(150, 100, 220, 30);

        JLabel cantidad = new JLabel("Cantidad:");
        cantidad.setBounds(50, 150, 100, 30);
        cantidad.setForeground(Color.WHITE);

        spCantidad = new JSpinner(
            new SpinnerNumberModel(1, 1, 100, 1)
        );

        spCantidad.setBounds(150, 150, 220, 30);

        JLabel adicion = new JLabel("Adicion:");
        adicion.setBounds(50, 200, 100, 30);
        adicion.setForeground(Color.WHITE);

        cbAdiciones = new JComboBox<>(new String[]{
            "Sin adicion",
            "Queso (+2000)",
            "Tocineta (+3000)",
            "Champiñones (+2500)",
            "Salsa especial (+1000)"
        });

        cbAdiciones.setBounds(150, 200, 220, 30);

        JButton agregar = new JButton("Agregar al Pedido");
        agregar.setBounds(150, 250, 220, 35);

        modelo = new DefaultTableModel(
            new String[]{
                "Producto",
                "Cantidad",
                "Adicion",
                "Subtotal"
            }, 0
        );

        JTable tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(400, 100, 450, 300);

        lblTotal = new JLabel("TOTAL: $0.0");
        lblTotal.setBounds(400, 420, 250, 30);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        lblTotal.setForeground(Color.WHITE);

        lblEstado = new JLabel("Estado: Pendiente");
        lblEstado.setBounds(400, 460, 250, 30);
        lblEstado.setForeground(Color.WHITE);

        JButton cambiarEstado = new JButton("Avanzar Estado");
        cambiarEstado.setBounds(150, 350, 220, 40);

        agregar.addActionListener(e -> agregarProducto());

        cambiarEstado.addActionListener(e -> cambiarEstado());

        add(titulo);
        add(producto);
        add(cbProductos);
        add(cantidad);
        add(spCantidad);
        add(adicion);
        add(cbAdiciones);
        add(agregar);
        add(scroll);
        add(lblTotal);
        add(lblEstado);
        add(cambiarEstado);

    }

    private void agregarProducto() {

        Producto producto = (Producto) cbProductos.getSelectedItem();

        if (producto == null) {

            JOptionPane.showMessageDialog(
                this,
                "Primero registra productos"
            );

            return;
        }

        int cantidad = (int) spCantidad.getValue();

        String adicion = cbAdiciones.getSelectedItem().toString();

        double precioAdicion = 0;

        if (adicion.startsWith("Queso")) {
            precioAdicion = 2000;
        }

        else if (adicion.startsWith("Tocineta")) {
            precioAdicion = 3000;
        }

        else if (adicion.startsWith("Champiñones")) {
            precioAdicion = 2500;
        }

        else if (adicion.startsWith("Salsa")) {
            precioAdicion = 1000;
        }

        controlador.agregarProducto(
            producto,
            cantidad,
            adicion,
            precioAdicion
        );

        actualizarTabla();

    }

    private void actualizarTabla() {

        modelo.setRowCount(0);

        for (DetallePedido detalle : controlador.obtenerDetalles()) {

            modelo.addRow(new Object[]{
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getAdicion(),
                detalle.getSubtotal()
            });

        }

        lblTotal.setText(
            "TOTAL: $" + controlador.obtenerTotal()
        );

    }

    private void cambiarEstado() {

        controlador.avanzarEstado();

        lblEstado.setText(
            "Estado: " + controlador.obtenerEstado()
        );

        if (controlador.obtenerEstado().equals("Entregado")) {

            JOptionPane.showMessageDialog(
                this,
                "El pedido ha sido entregado"
            );

        }

    }

}
