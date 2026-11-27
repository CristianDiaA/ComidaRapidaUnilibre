
package com.comidarapida.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.comidarapida.modelo.*;
import com.comidarapida.controlador.ComboController;
import com.comidarapida.servicios.ServicioProductos;

public class VistaCombos extends JFrame {

    private ComboController controlador;

    private JTextField txtNombre;
    private JComboBox<Producto> cbProductos;
    private JComboBox<String> cbAdiciones;
    private JSpinner spCantidad;

    private DefaultTableModel modelo;
    private JLabel lblTotal;

    private double total = 0;

    public VistaCombos(ServicioProductos servicio) {

        controlador = new ComboController(servicio);

        setTitle("Constructor de Combos");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(40, 40, 40));

        JLabel titulo = new JLabel("CONSTRUIR COMBO");
        titulo.setBounds(270, 20, 350, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(Color.WHITE);

        JLabel nombre = new JLabel("Nombre del combo:");
        nombre.setBounds(40, 100, 130, 30);
        nombre.setForeground(Color.WHITE);

        txtNombre = new JTextField();
        txtNombre.setBounds(180, 100, 200, 30);

        JLabel producto = new JLabel("Producto:");
        producto.setBounds(40, 150, 100, 30);
        producto.setForeground(Color.WHITE);

        cbProductos = new JComboBox<>();

        for (Producto p : controlador.obtenerProductos()) {
            cbProductos.addItem(p);
        }

        cbProductos.setBounds(180, 150, 200, 30);

        JLabel cantidad = new JLabel("Cantidad:");
        cantidad.setBounds(40, 200, 100, 30);
        cantidad.setForeground(Color.WHITE);

        spCantidad = new JSpinner(
            new SpinnerNumberModel(1, 1, 100, 1)
        );

        spCantidad.setBounds(180, 200, 200, 30);

        JLabel adicion = new JLabel("Adicion:");
        adicion.setBounds(40, 250, 100, 30);
        adicion.setForeground(Color.WHITE);

        cbAdiciones = new JComboBox<>(new String[]{
            "Sin adicion",
            "Queso (+2000)",
            "Tocineta (+3000)",
            "Champiñones (+2500)",
            "Salsa especial (+1000)"
        });

        cbAdiciones.setBounds(180, 250, 200, 30);

        JButton agregar = new JButton("Agregar al Combo");
        agregar.setBounds(180, 300, 200, 35);

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
        scroll.setBounds(420, 100, 380, 280);

        lblTotal = new JLabel("TOTAL: $0.0");
        lblTotal.setBounds(420, 400, 250, 30);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        lblTotal.setForeground(Color.WHITE);

        JButton construir = new JButton("Construir Combo");
        construir.setBounds(180, 400, 200, 40);

        agregar.addActionListener(e -> agregarProducto());

        construir.addActionListener(e -> construirCombo());

        add(titulo);
        add(nombre);
        add(txtNombre);
        add(producto);
        add(cbProductos);
        add(cantidad);
        add(spCantidad);
        add(adicion);
        add(cbAdiciones);
        add(agregar);
        add(scroll);
        add(lblTotal);
        add(construir);

    }

    private void agregarProducto() {

        Producto producto = (Producto) cbProductos.getSelectedItem();

        if (producto == null) {
            JOptionPane.showMessageDialog(
                this, "Primero registra productos"
            );
            return;
        }

        int cantidad = (int) spCantidad.getValue();

        String adicion = cbAdiciones.getSelectedItem().toString();

        double precioAdicion = obtenerPrecioAdicion(adicion);

        controlador.agregarProducto(
            producto,
            cantidad,
            adicion,
            precioAdicion
        );

        double subtotal =
            (producto.getPrecio() + precioAdicion) * cantidad;

        total += subtotal;

        modelo.addRow(new Object[]{
            producto.getNombre(),
            cantidad,
            adicion,
            subtotal
        });

        lblTotal.setText("TOTAL: $" + total);

    }

    private double obtenerPrecioAdicion(String adicion) {

        if (adicion.startsWith("Queso")) {
            return 2000;
        }

        if (adicion.startsWith("Tocineta")) {
            return 3000;
        }

        if (adicion.startsWith("Champiñones")) {
            return 2500;
        }

        if (adicion.startsWith("Salsa")) {
            return 1000;
        }

        return 0;

    }

    private void construirCombo() {

        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Escribe el nombre del combo"
            );
            return;
        }

        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(
                this, "Agrega productos al combo"
            );
            return;
        }

        Combo combo = controlador.construirCombo(nombre);

        JOptionPane.showMessageDialog(
            this,
            "COMBO CREADO CORRECTAMENTE\n"
            + "Nombre: " + combo.getNombre()
            + "\nProductos: " + combo.getProductos().size()
            + "\nTotal: $" + combo.calcularTotal()
        );

        modelo.setRowCount(0);
        txtNombre.setText("");
        total = 0;
        lblTotal.setText("TOTAL: $0.0");

        controlador.reiniciarBuilder();

    }

}
