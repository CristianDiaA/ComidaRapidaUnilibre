
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
    private JComboBox<Adicion> cbAdiciones;
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

        cbAdiciones = new JComboBox<>();

        cbAdiciones.setBounds(150, 200, 220, 30);

        cbProductos.addActionListener(e -> actualizarAdiciones());
        actualizarAdiciones();

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
        tabla.setAutoCreateRowSorter(true);

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

        Adicion seleccion = (Adicion) cbAdiciones.getSelectedItem();
        String adicion = seleccion.toString();

        controlador.agregarProducto(
            producto,
            cantidad,
            adicion,
            seleccion.getPrecio()
        );

        actualizarTabla();

    }

    private void actualizarAdiciones() {
        cbAdiciones.removeAllItems();
        cbAdiciones.addItem(new Adicion("Sin adicion", 0));

        Producto producto = (Producto) cbProductos.getSelectedItem();
        if (producto == null) {
            return;
        }

        switch (producto.getCategoria()) {
            case "Hamburguesa":
                agregarAdicion("Queso", 2000);
                agregarAdicion("Tocineta", 3000);
                agregarAdicion("Salsa especial", 1000);
                break;
            case "Pizza":
                agregarAdicion("Queso", 2000);
                agregarAdicion("Tocineta", 3000);
                agregarAdicion("Champiñones", 2500);
                break;
            case "Perro Caliente":
                agregarAdicion("Queso", 2000);
                agregarAdicion("Tocineta", 3000);
                agregarAdicion("Salsa especial", 1000);
                break;
            case "Salchipapa":
                agregarAdicion("Queso", 2000);
                agregarAdicion("Tocineta", 3000);
                agregarAdicion("Salsa especial", 1000);
                break;
            case "Mazorcada":
                agregarAdicion("Queso", 2000);
                agregarAdicion("Tocineta", 3000);
                agregarAdicion("Champiñones", 2500);
                agregarAdicion("Salsa especial", 1000);
                break;
            case "Arepa":
                agregarAdicion("Queso", 2000);
                agregarAdicion("Tocineta", 3000);
                break;
            case "Bebida":
                agregarAdicion("Hielo", 500);
                break;
            default:
                break;
        }
    }

    private void agregarAdicion(String nombre, double precio) {
        cbAdiciones.addItem(new Adicion(nombre, precio));
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

        if (controlador.obtenerDetalles().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "El pedido esta vacío"
            );
            return;
        }

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
    private static class Adicion {

        private final String nombre;
        private final double precio;

        private Adicion(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        private double getPrecio() {
            return precio;
        }

        @Override
        public String toString() {
            if (precio == 0) {
                return nombre;
            }
            return nombre + " (+" + precio + ")";
        }
    }

}
