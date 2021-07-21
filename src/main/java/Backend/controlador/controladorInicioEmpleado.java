/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.controlador;

import Backend.model.consultas.consultaTienda;
import Fonted.empleado.menuEmpleado;
import Fonted.log;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Artist
 */
public class controladorInicioEmpleado implements ActionListener {

    log log = new log();

    consultaTienda consultaTienda = new consultaTienda();

    public controladorInicioEmpleado(log log) {
        this.log = log;
        this.log.entrarJButton.addActionListener(this);
        this.log.setLocationRelativeTo(null);
        this.log.setTitle("Intelaf - Loggin");
        llenarComobox();
    }

    public void llenarComobox() {
        for (String listaIdTienda : consultaTienda.listaIdTiendas()) {
            this.log.jComboBoxLogListaTiendas.addItem(listaIdTienda);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == log.entrarJButton) {

            String codigoTienda = (String) this.log.jComboBoxLogListaTiendas.getSelectedItem();

            // String resp = (String) JOptionPane.showInputDialog(null, "Seleccione el codigo de tienda donde esta iniciando sesion", "Intelaf", JOptionPane.DEFAULT_OPTION, null, consultaTienda.listaIdTiendas(), null);
            if (codigoTienda != null) {
                menuEmpleado menuEmpleado = new menuEmpleado();
                controladorMenuEmpleado controladorMenuEmpleado = new controladorMenuEmpleado(menuEmpleado, codigoTienda);
                menuEmpleado.setVisible(true);
                System.out.println("Selecciono la opcion: " + codigoTienda);

            }

        }

    }

}
