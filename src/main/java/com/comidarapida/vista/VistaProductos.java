
package com.comidarapida.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.comidarapida.controlador.ProductoController;
import com.comidarapida.modelo.Producto;

public class VistaProductos extends JFrame {

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JComboBox<String> cbCategoria;

    private DefaultTableModel modelo;

    private ProductoController controlador;

    public VistaProductos(com.comidarapida.servicios.ServicioProductos servicio

    ) {

        controlador = new ProductoController(servicio);
        setTitle("Gestion de Productos");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(40, 40, 40));

        JLabel titulo = new JLabel("GESTION DE PRODUCTOS");
        titulo.setBounds(230, 20, 400, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);

        JLabel nombre = new JLabel("Nombre:");
        nombre.setBounds(50, 100, 100, 30);
        nombre.setForeground(Color.WHITE);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 100, 200, 30);

        JLabel precio = new JLabel("Precio:");
        precio.setBounds(50, 150, 100, 30);
        precio.setForeground(Color.WHITE);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(150, 150, 200, 30);

        JLabel categoria = new JLabel("Categoria:");
        categoria.setBounds(50, 200, 100, 30);
        categoria.setForeground(Color.WHITE);

        cbCategoria = new JComboBox<>(new String[]{
            "Hamburguesa",
            "Pizza",
            "Perro Caliente",
            "Salchipapa",
            "Mazorcada",
            "Arepa",
            "Bebida"
        });

        cbCategoria.setBounds(150, 200, 200, 30);

        JButton agregar = new JButton("Agregar Producto");
        agregar.setBounds(150, 250, 200, 35);

        modelo = new DefaultTableModel(
            new String[]{"Nombre", "Precio", "Categoria"}, 0
        );

        JTable tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(400, 100, 350, 300);

        agregar.addActionListener(e -> agregarProducto());

        add(titulo);
        add(nombre);
        add(txtNombre);
        add(precio);
        add(txtPrecio);
        add(categoria);
        add(cbCategoria);
        add(agregar);
        add(scroll);

    }

    private void agregarProducto() {

        String nombre = txtNombre.getText();
        String precioTexto = txtPrecio.getText();
        String categoria = cbCategoria.getSelectedItem().toString();

        try {

            double precio = Double.parseDouble(precioTexto);

            boolean resultado = controlador.agregarProducto(
                nombre, precio, categoria
            );

            if (resultado) {

                Producto producto = controlador.obtenerProductos()
                    .get(controlador.obtenerProductos().size() - 1);

                modelo.addRow(new Object[]{
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getCategoria()
                });

                txtNombre.setText("");
                txtPrecio.setText("");

                JOptionPane.showMessageDialog(
                    this, "Producto agregado correctamente"
                );

            } else {

                JOptionPane.showMessageDialog(
                    this, "Nombre o precio invalido"
                );

            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this, "Ingresa un precio valido"
            );

        }

    }

}
