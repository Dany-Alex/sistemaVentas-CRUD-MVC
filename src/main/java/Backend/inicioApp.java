/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Backend.controlador.controladorInicioEmpleado;
import Backend.model.dataAccess.managerFilesTxt;
import Fonted.log;
import java.nio.channels.NetworkChannel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author Artist
 */
public class inicioApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //  managerFilesTxt filesTxt = new managerFilesTxt(new JFileChooser());

        log log = new log();
        controladorInicioEmpleado controladorInicioEmpleado = new controladorInicioEmpleado(log);
        log.setVisible(true);

        // System.out.println("jfdassafd");
    }

    public inicioApp() {
    }

}
