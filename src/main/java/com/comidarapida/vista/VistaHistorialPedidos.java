
package com.comidarapida.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import com.comidarapida.modelo.Pedido;

public class VistaHistorialPedidos extends JFrame {

    private ArrayList<Pedido> historial;
    private DefaultTableModel modelo;

    private JLabel lblCantidad;
    private JLabel lblTotal;

    public VistaHistorialPedidos(ArrayList<Pedido> historial) {

        this.historial = historial;

        setTitle("Historial de Pedidos");
        setSize(750, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel(
            "HISTORIAL DE PEDIDOS",
            SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        modelo = new DefaultTableModel(
            new String[]{
                "ID",
                "Fecha",
                "Estado",
                "Total"
            }, 0
        );

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(25);
        tabla.setAutoCreateRowSorter(true);

        JScrollPane scroll = new JScrollPane(tabla);

        lblCantidad = new JLabel();
        lblTotal = new JLabel();

        lblCantidad.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel panelInferior = new JPanel();

        panelInferior.add(lblCantidad);
        panelInferior.add(lblTotal);

        JButton actualizar = new JButton("Actualizar");

        actualizar.addActionListener(e -> cargarHistorial());

        panelInferior.add(actualizar);

        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        cargarHistorial();

    }

    private void cargarHistorial() {

        modelo.setRowCount(0);

        double total = 0;

        for (Pedido pedido : historial) {

            modelo.addRow(new Object[]{
                pedido.getId(),
                pedido.getFechaTexto(),
                pedido.getEstado(),
                pedido.calcularTotal()
            });

            total += pedido.calcularTotal();

        }

        lblCantidad.setText(
            "Cantidad de pedidos: " + historial.size()
        );

        lblTotal.setText(
            "Total acumulado: $" + total
        );

    }

}
