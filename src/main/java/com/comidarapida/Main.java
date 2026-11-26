
package com.comidarapida;
import javax.swing.SwingUtilities;
import com.comidarapida.vista.VistaPrincipal;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            VistaPrincipal ventana = new VistaPrincipal();
            ventana.setVisible(true);
        });

    }

}
