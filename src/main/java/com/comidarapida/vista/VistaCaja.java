
package com.comidarapida.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.comidarapida.modelo.Venta;
import com.comidarapida.controlador.CajaController;
import com.comidarapida.servicios.ServicioVentas;

public class VistaCaja extends JFrame {

    private CajaController controlador;

    private JTextField txtDescripcion;
    private JTextField txtTotal;

    private DefaultTableModel modelo;

    private JLabel lblIngresos;
    private JLabel lblCantidad;

    private JButton btnRegistrar;
    private JButton btnCerrar;

    public VistaCaja(ServicioVentas servicio) {

        controlador = new CajaController(servicio);

        setTitle("Caja y Reportes");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(40, 40, 40));

        JLabel titulo = new JLabel("CAJA Y VENTAS");
        titulo.setBounds(320, 20, 350, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(Color.WHITE);

        JLabel descripcion = new JLabel("Descripcion:");
        descripcion.setBounds(40, 100, 100, 30);
        descripcion.setForeground(Color.WHITE);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(150, 100, 200, 30);

        JLabel total = new JLabel("Total:");
        total.setBounds(40, 150, 100, 30);
        total.setForeground(Color.WHITE);

        txtTotal = new JTextField();
        txtTotal.setBounds(150, 150, 200, 30);

        btnRegistrar = new JButton("Registrar Venta");
        btnRegistrar.setBounds(150, 200, 200, 40);

        modelo = new DefaultTableModel(
            new String[]{
                "Descripcion",
                "Total",
                "Fecha"
            }, 0
        );

        JTable tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(390, 100, 460, 300);

        lblIngresos = new JLabel("Ingresos del dia: $0");
        lblIngresos.setBounds(390, 420, 300, 30);
        lblIngresos.setForeground(Color.WHITE);
        lblIngresos.setFont(new Font("Arial", Font.BOLD, 17));

        lblCantidad = new JLabel("Ventas realizadas: 0");
        lblCantidad.setBounds(390, 460, 300, 30);
        lblCantidad.setForeground(Color.WHITE);

        btnCerrar = new JButton("Cerrar Caja");
        btnCerrar.setBounds(150, 280, 200, 40);

        btnRegistrar.addActionListener(e -> registrarVenta());

        btnCerrar.addActionListener(e -> cerrarCaja());

        add(titulo);
        add(descripcion);
        add(txtDescripcion);
        add(total);
        add(txtTotal);
        add(btnRegistrar);
        add(scroll);
        add(lblIngresos);
        add(lblCantidad);
        add(btnCerrar);

        actualizarTabla();

    }

    private void registrarVenta() {

        String descripcion = txtDescripcion.getText();

        try {

            double total = Double.parseDouble(txtTotal.getText());

            boolean resultado = controlador.registrarVenta(
                descripcion, total
            );

            if (resultado) {

                actualizarTabla();

                txtDescripcion.setText("");
                txtTotal.setText("");

                JOptionPane.showMessageDialog(
                    this,
                    "Venta registrada correctamente"
                );

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar la venta. "
                    + "Verifica los datos y el estado de la caja."
                );

            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Ingresa un valor numerico valido"
            );

        }

    }

    private void actualizarTabla() {

        modelo.setRowCount(0);

        for (Venta venta : controlador.obtenerVentas()) {

            modelo.addRow(new Object[]{
                venta.getDescripcion(),
                venta.getTotal(),
                venta.getFechaTexto()
            });

        }

        lblIngresos.setText(
            "Ingresos del dia: $" + controlador.obtenerTotal()
        );

        lblCantidad.setText(
            "Ventas realizadas: " + controlador.obtenerCantidadVentas()
        );

    }

    private void cerrarCaja() {

        int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¿Estas seguro de cerrar la caja?",
            "Cierre de caja",
            JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            controlador.cerrarCaja();

            btnRegistrar.setEnabled(false);
            btnCerrar.setEnabled(false);

            JOptionPane.showMessageDialog(
                this,
                "CAJA CERRADA\n"
                + "Ventas: " + controlador.obtenerCantidadVentas()
                + "\nTotal recaudado: $" + controlador.obtenerTotal()
            );

        }

    }

}
