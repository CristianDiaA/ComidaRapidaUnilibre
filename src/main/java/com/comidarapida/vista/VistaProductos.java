
package com.comidarapida.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.comidarapida.controlador.ProductoController;
import com.comidarapida.modelo.Producto;

public class VistaProductos extends JFrame {

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JComboBox<String> cbCategoria;

    private DefaultTableModel modelo;

    private ProductoController controlador;
    private Producto productoSeleccionado;

    public VistaProductos(com.comidarapida.servicios.ServicioProductos servicio

    ) {

        controlador = new ProductoController(servicio);
        setTitle("Gestion de Productos");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
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
        tabla.setAutoCreateRowSorter(true);

        JPopupMenu menuContextual = new JPopupMenu();
        JMenuItem eliminar = new JMenuItem("Eliminar producto");
        menuContextual.add(eliminar);

        tabla.addMouseListener(new MouseAdapter() {
            private void mostrarMenu(MouseEvent evento) {
                if (!evento.isPopupTrigger()) {
                    return;
                }

                int fila = tabla.rowAtPoint(evento.getPoint());
                if (fila < 0) {
                    return;
                }

                tabla.setRowSelectionInterval(fila, fila);
                int filaModelo = tabla.convertRowIndexToModel(fila);
                productoSeleccionado = controlador.obtenerProductos()
                        .get(filaModelo);
                menuContextual.show(tabla, evento.getX(), evento.getY());
            }

            @Override
            public void mousePressed(MouseEvent evento) {
                mostrarMenu(evento);
            }

            @Override
            public void mouseReleased(MouseEvent evento) {
                mostrarMenu(evento);
            }
        });

        eliminar.addActionListener(e -> eliminarProducto());

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(400, 100, 350, 300);

        agregar.addActionListener(e -> agregarProducto());
        cargarProductos();

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

    private void cargarProductos() {
        modelo.setRowCount(0);

        for (Producto producto : controlador.obtenerProductos()) {
            modelo.addRow(new Object[]{
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getCategoria()
            });
        }
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
                cargarProductos();

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

    private void eliminarProducto() {
        if (productoSeleccionado == null) {
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas eliminar el producto "
                        + productoSeleccionado.getNombre() + "?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (controlador.eliminarProducto(productoSeleccionado)) {
                cargarProductos();
                productoSeleccionado = null;
            } else {
                JOptionPane.showMessageDialog(
                        this, "No se pudo eliminar el producto"
                );
            }
        }
    }

}
