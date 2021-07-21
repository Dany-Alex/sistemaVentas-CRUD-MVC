/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.controlador;

import Backend.model.cliente;
import Backend.model.consultas.consultaCliente;
import Fonted.empleado.menuEmpleado;
import Fonted.empleado.vistaCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Artist
 */
public class controladorCliente implements ActionListener, ChangeListener, MouseListener, KeyListener {

    String nombreClase = "Controlador Cliente";
    cliente cliente;
    vistaCliente vistaCliente;
    menuEmpleado menuEmpleado;

    private consultaCliente consultaCliente = new consultaCliente();

    public String codigoTienda;

    public controladorCliente(cliente cliente, vistaCliente vistaCliente, menuEmpleado menuEmpleado, String codigoTienda) {
        this.vistaCliente = vistaCliente;
        this.menuEmpleado = menuEmpleado;
        this.codigoTienda = codigoTienda;
        this.cliente = cliente;
        agregarEventos();
        llenarComobox();
        mostrarLista();
        ocultarLabelObligatorioCrear();
        validarCamposObligatoriosCrear();

    }

    public void agregarEventos() {
        this.vistaCliente.jTabbedPaneAdministrar.addChangeListener(this);
        this.vistaCliente.jRadioButtonEliminarAccion.addMouseListener(this);
        this.vistaCliente.jRadioButtonModificarAccion.addMouseListener(this);
        this.vistaCliente.jTableCliente.addMouseListener(this);
        this.vistaCliente.jButtonCrear.addActionListener(this);
        this.vistaCliente.jButtonRealizarAccion.addActionListener(this);
        this.vistaCliente.jTextFieldTelefonoCrear.addKeyListener(this);
        this.vistaCliente.jTextFieldNitCrear.addKeyListener(this);
        this.vistaCliente.jTextFieldNombreCrear.addKeyListener(this);
        this.vistaCliente.jTextFieldTelefonoModificarEliminar.addKeyListener(this);
        this.vistaCliente.jTextFieldNitModificarEliminar.addKeyListener(this);
        this.vistaCliente.jTextFieldNombreModificarEliminar.addKeyListener(this);
    }

    public void limpiarTextModificarEliminar() {
        vistaCliente.jTextFieldNombreModificarEliminar.setText(null);
        vistaCliente.jTextFieldNitModificarEliminar.setText(null);
        vistaCliente.jTextFieldTelefonoModificarEliminar.setText(null);
        vistaCliente.jTextFieldCreditoModificarEliminar.setText(null);
        vistaCliente.jTextFieldDpiModificarEliminar.setText(null);
        vistaCliente.jTextFieldCorreoModificarEliminar.setText(null);
        vistaCliente.jTextFieldDireccionModificarEliminar.setText(null);

    }

    public void limpiarTextCrear() {
        vistaCliente.jTextFieldNombreCrear.setText(null);
        vistaCliente.jTextFieldNitCrear.setText(null);
        vistaCliente.jTextFieldTelefonoCrear.setText(null);
        vistaCliente.jTextFieldCreditoCrear.setText(null);
        vistaCliente.jTextFieldDpiCrear.setText(null);
        vistaCliente.jTextFieldCorreoCrear.setText(null);
        vistaCliente.jTextFieldDireccionCrear.setText(null);

    }

    public void mostrarLista() {
        try {
            this.vistaCliente.jTableCliente.setModel(consultaCliente.ListarDatos());
        } catch (Exception ex) {
            mostrarError(nombreClase + " dice: Ha ocurrido un error: " + ex.toString());

        }
    }

    public String getTituloCompuesto() {
        return "Intelaf - Tienda codigo: " + codigoTienda;
    }

    public void mostrarError(String mensaje) {
        System.out.println(mensaje);
        JOptionPane.showMessageDialog(null, mensaje, nombreClase, JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarInformacion(String mensaje) {
        System.out.println(mensaje);
        JOptionPane.showMessageDialog(null, mensaje, "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean validarCamposObligatoriosCrear() {
        setMensajeLabelObligatorioCrear();

        boolean banderaVacio = true;
        if (vistaCliente.jTextFieldNombreCrear.getText().isEmpty()) {
            this.vistaCliente.jLabelObligatorioCrear.setVisible(true);
            banderaVacio = true;
        } else {
            this.vistaCliente.jLabelObligatorioCrear.setVisible(false);
            banderaVacio = false;
        }
        if (vistaCliente.jTextFieldNitCrear.getText().isEmpty()) {
            this.vistaCliente.jLabelObligatorioCrear1.setVisible(true);
            banderaVacio = true;
        } else {
            this.vistaCliente.jLabelObligatorioCrear1.setVisible(false);
            banderaVacio = false;
        }
        if (vistaCliente.jTextFieldTelefonoCrear.getText().isEmpty()) {
            this.vistaCliente.jLabelObligatorioCrear2.setVisible(true);
            banderaVacio = true;
        } else {
            this.vistaCliente.jLabelObligatorioCrear2.setVisible(false);
            banderaVacio = false;

        }
        return banderaVacio;
    }

    public boolean validarCamposObligatoriosModificarEliminar() {
        setMensajeLabelObligatorioModificarEliminar();

        boolean banderaVacio = true;
        if (vistaCliente.jTextFieldNombreModificarEliminar.getText().isEmpty()) {
            this.vistaCliente.jLabelObligatorioModificarEliminar.setVisible(true);
            banderaVacio = true;
        } else {
            this.vistaCliente.jLabelObligatorioModificarEliminar.setVisible(false);
            banderaVacio = false;
        }
        if (vistaCliente.jTextFieldNitModificarEliminar.getText().isEmpty()) {
            this.vistaCliente.jLabelObligatorioModificarEliminar2.setVisible(true);
            banderaVacio = true;
        } else {
            this.vistaCliente.jLabelObligatorioModificarEliminar2.setVisible(false);
            banderaVacio = false;
        }
        if (vistaCliente.jTextFieldTelefonoModificarEliminar.getText().isEmpty()) {
            this.vistaCliente.jLabelObligatorioModificarEliminar3.setVisible(true);
            banderaVacio = true;
        } else {
            this.vistaCliente.jLabelObligatorioModificarEliminar3.setVisible(false);
            banderaVacio = false;

        }
        return banderaVacio;
    }

    public void llenarComobox() {
        char[] letrasNit = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'};
        for (char c : letrasNit) {
            vistaCliente.jComboBoxNitLetraCrear.addItem(c + "");
        }
    }

    public void ocultarLabelObligatorioCrear() {
        this.vistaCliente.jLabelObligatorioCrear.setVisible(false);
        this.vistaCliente.jLabelObligatorioCrear1.setVisible(false);
        this.vistaCliente.jLabelObligatorioCrear2.setVisible(false);

    }

    public void setMensajeLabelObligatorioCrear() {
        String mensaje = "* Campo Obligatorio";
        this.vistaCliente.jLabelObligatorioCrear.setText(mensaje);
        this.vistaCliente.jLabelObligatorioCrear1.setText(mensaje);
        this.vistaCliente.jLabelObligatorioCrear2.setText(mensaje);
    }

    public void setMensajeLabelObligatorioModificarEliminar() {
        String mensaje = "* Campo Obligatorio";
        this.vistaCliente.jLabelObligatorioModificarEliminar.setText(mensaje);
        this.vistaCliente.jLabelObligatorioModificarEliminar2.setText(mensaje);
        this.vistaCliente.jLabelObligatorioModificarEliminar3.setText(mensaje);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == vistaCliente.jTabbedPaneAdministrar) {
            switch (vistaCliente.jTabbedPaneAdministrar.getSelectedIndex()) {
                case 0:
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado - Administrar - Cliente - Crear");
                    validarCamposObligatoriosCrear();
                    mostrarLista();

                    break;

                case 1:
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado - Administrar - Cliente - Modificar / Eliminar");
                    limpiarTextModificarEliminar();
                    validarCamposObligatoriosModificarEliminar();
                    this.vistaCliente.jRadioButtonModificarAccion.setSelected(true);
                    mostrarLista();

                    break;

                default:
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado - Administrar");
                    break;

            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == vistaCliente.jTableCliente) {

            if (e.getClickCount() == 1) {

                vistaCliente.jTextFieldNombreModificarEliminar.setText(vistaCliente.jTableCliente.getValueAt(vistaCliente.jTableCliente.getSelectedRow(), 0).toString());
                vistaCliente.jTextFieldNitModificarEliminar.setText(vistaCliente.jTableCliente.getValueAt(vistaCliente.jTableCliente.getSelectedRow(), 1).toString());
                vistaCliente.jTextFieldTelefonoModificarEliminar.setText(vistaCliente.jTableCliente.getValueAt(vistaCliente.jTableCliente.getSelectedRow(), 2).toString());
                vistaCliente.jTextFieldCreditoModificarEliminar.setText(vistaCliente.jTableCliente.getValueAt(vistaCliente.jTableCliente.getSelectedRow(), 3).toString());
                vistaCliente.jTextFieldDpiModificarEliminar.setText(vistaCliente.jTableCliente.getValueAt(vistaCliente.jTableCliente.getSelectedRow(), 4).toString());
                vistaCliente.jTextFieldCorreoModificarEliminar.setText((vistaCliente.jTableCliente.getValueAt(vistaCliente.jTableCliente.getSelectedRow(), 5) == null) ? "" : vistaCliente.jTableCliente.getValueAt(vistaCliente.jTableCliente.getSelectedRow(), 5).toString());
                vistaCliente.jTextFieldDireccionModificarEliminar.setText((vistaCliente.jTableCliente.getValueAt(vistaCliente.jTableCliente.getSelectedRow(), 6) == null) ? "" : vistaCliente.jTableCliente.getValueAt(vistaCliente.jTableCliente.getSelectedRow(), 6).toString());
                validarCamposObligatoriosModificarEliminar();

            }
        } else if (e.getSource() == vistaCliente.jRadioButtonEliminarAccion) {

            if (e.getClickCount() == 1) {
                System.out.println("raio eleiminar");
                limpiarJTextModificarEliminar();
                bloquearJTextModificarEliminar();
            }
        } else if (e.getSource() == vistaCliente.jRadioButtonModificarAccion) {

            if (e.getClickCount() == 1) {
                System.out.println("raio modificar");
                limpiarJTextModificarEliminar();
                desbloquearJTextModificarEliminar();

            }
        }
    }

    public void bloquearJTextModificarEliminar() {
        vistaCliente.jTextFieldCorreoModificarEliminar.setEnabled(false);
        vistaCliente.jTextFieldCreditoModificarEliminar.setEnabled(false);
        vistaCliente.jTextFieldDireccionModificarEliminar.setEnabled(false);
        vistaCliente.jTextFieldDpiModificarEliminar.setEnabled(false);
        vistaCliente.jTextFieldNombreModificarEliminar.setEnabled(false);
        vistaCliente.jTextFieldTelefonoModificarEliminar.setEnabled(false);
    }

    public void limpiarJTextModificarEliminar() {
        vistaCliente.jTextFieldCorreoModificarEliminar.setText(null);
        vistaCliente.jTextFieldCreditoModificarEliminar.setText(null);
        vistaCliente.jTextFieldDireccionModificarEliminar.setText(null);
        vistaCliente.jTextFieldDpiModificarEliminar.setText(null);
        vistaCliente.jTextFieldNombreModificarEliminar.setText(null);
        vistaCliente.jTextFieldTelefonoModificarEliminar.setText(null);
        vistaCliente.jTextFieldNitModificarEliminar.setText(null);
    }

    public void desbloquearJTextModificarEliminar() {
        vistaCliente.jTextFieldCorreoModificarEliminar.setEnabled(true);
        vistaCliente.jTextFieldCreditoModificarEliminar.setEnabled(true);
        vistaCliente.jTextFieldDireccionModificarEliminar.setEnabled(true);
        vistaCliente.jTextFieldDpiModificarEliminar.setEnabled(true);
        vistaCliente.jTextFieldNombreModificarEliminar.setEnabled(true);
        vistaCliente.jTextFieldTelefonoModificarEliminar.setEnabled(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaCliente.jButtonCrear) {
            try {
                if (!validarCamposObligatoriosCrear()) {
                    String nit = (String) vistaCliente.jComboBoxNitLetraCrear.getSelectedItem() + "-" + vistaCliente.jTextFieldNitCrear.getText();
                    int telefono = Integer.parseInt(vistaCliente.jTextFieldTelefonoCrear.getText()),
                            credito = (vistaCliente.jTextFieldCreditoCrear.getText().length() > 0) ? Integer.parseInt(vistaCliente.jTextFieldCreditoCrear.getText()) : 0,
                            dpi = (vistaCliente.jTextFieldDpiCrear.getText().length() > 0) ? Integer.parseInt(vistaCliente.jTextFieldDpiCrear.getText()) : 0;

                    cliente.setNombre(vistaCliente.jTextFieldNombreCrear.getText());
                    cliente.setNit(nit);
                    cliente.setTelefono(telefono);
                    cliente.setCreditoCompra(credito);
                    cliente.setDpi(dpi);
                    cliente.setCorreoElectronico(vistaCliente.jTextFieldCorreoCrear.getText());
                    cliente.setDireccion(vistaCliente.jTextFieldDireccionCrear.getText());

                    String mensajeConsulta = consultaCliente.crear(cliente);
                    mostrarInformacion(mensajeConsulta);

                    mostrarLista();
                    limpiarTextCrear();
                    validarCamposObligatoriosCrear();
                } else {
                    mostrarInformacion("Los campos obligatorios estan vacios");
                }

            } catch (Exception ex) {
                mostrarError(nombreClase + " dice: Ha ocurrido un error: " + ex.toString());

            } finally {
                // ocultarLabelObligatorio();
            }
        } else if (e.getSource() == vistaCliente.jButtonRealizarAccion) {

            if (vistaCliente.jRadioButtonEliminarAccion.isSelected()) {
                if ((JOptionPane.showConfirmDialog(null, "Este registro sera eliminado permanetemente, desea continuar?", "Intelaf - Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) == 0) {
                    if (!validarCamposObligatoriosModificarEliminar()) {
                        String nit = vistaCliente.jTextFieldNitModificarEliminar.getText();
                        String mensajeConsulta;
                        try {
                            mensajeConsulta = consultaCliente.eliminar(nit);
                            mostrarInformacion(mensajeConsulta);
                        } catch (Exception ex) {
                            mostrarError(ex.toString());
                        }
                        mostrarLista();
                        limpiarJTextModificarEliminar();
                        validarCamposObligatoriosModificarEliminar();
                    } else {
                        mostrarInformacion("Los campos obligatorios estan vacios");
                    }
                    limpiarJTextModificarEliminar();
                } else {
                    limpiarJTextModificarEliminar();
                }

            } else if (vistaCliente.jRadioButtonModificarAccion.isSelected()) {
                try {
                    if ((JOptionPane.showConfirmDialog(null, "Este registro sera modificado, desea continuar?", "Intelaf - Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) == 0) {
                        if (!validarCamposObligatoriosModificarEliminar()) {

                            String nit = vistaCliente.jTextFieldNitModificarEliminar.getText();
                            int telefono = Integer.parseInt(vistaCliente.jTextFieldTelefonoModificarEliminar.getText()),
                                    credito = (vistaCliente.jTextFieldCreditoModificarEliminar.getText().length() > 0) ? Integer.parseInt(vistaCliente.jTextFieldCreditoModificarEliminar.getText()) : 0,
                                    dpi = (vistaCliente.jTextFieldDpiModificarEliminar.getText().length() > 0) ? Integer.parseInt(vistaCliente.jTextFieldDpiModificarEliminar.getText()) : 0;

                            cliente.setNombre(vistaCliente.jTextFieldNombreModificarEliminar.getText());
                            cliente.setNit(nit);
                            cliente.setTelefono(telefono);
                            cliente.setCreditoCompra(credito);
                            cliente.setDpi(dpi);
                            cliente.setCorreoElectronico(vistaCliente.jTextFieldCorreoModificarEliminar.getText());
                            cliente.setDireccion(vistaCliente.jTextFieldDireccionModificarEliminar.getText());

                            String mensajeConsulta = consultaCliente.modificar(cliente);
                            mostrarInformacion(mensajeConsulta);
                            mostrarLista();
                            limpiarJTextModificarEliminar();
                            validarCamposObligatoriosModificarEliminar();
                        } else {
                            mostrarInformacion("Los campos obligatorios estan vacios");
                        }
                        limpiarJTextModificarEliminar();
                    } else {
                        limpiarJTextModificarEliminar();
                    }
                } catch (Exception ex) {
                    mostrarError(ex.toString());
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vistaCliente.jTextFieldNombreCrear) {
            validarCamposObligatoriosCrear();
            if (!(Character.isLetter(e.getKeyChar()))) {
                e.consume();
            }
        } else if (e.getSource() == vistaCliente.jTextFieldNitCrear) {
            validarCamposObligatoriosCrear();
            if (!(Character.isDigit(e.getKeyChar()) && vistaCliente.jTextFieldNitCrear.getText().length() <= 3)) {
                e.consume();
            }
        } else if (e.getSource() == vistaCliente.jTextFieldTelefonoCrear) {
            validarCamposObligatoriosCrear();
            if (!(Character.isDigit(e.getKeyChar()) && (vistaCliente.jTextFieldTelefonoCrear.getText().length() <= 7))) {//Valida que el usuario escriba solo letras y que no supere los 8 digitos
                e.consume();
            }

        } else if (e.getSource() == vistaCliente.jTextFieldNombreModificarEliminar) {
            validarCamposObligatoriosModificarEliminar();
            if (!(Character.isLetter(e.getKeyChar()))) {
                e.consume();
            }
        } else if (e.getSource() == vistaCliente.jTextFieldNitModificarEliminar) {
            validarCamposObligatoriosModificarEliminar();
            if (!(Character.isDigit(e.getKeyChar()) && vistaCliente.jTextFieldNitModificarEliminar.getText().length() <= 3)) {
                e.consume();
            }
        } else if (e.getSource() == vistaCliente.jTextFieldTelefonoModificarEliminar) {
            validarCamposObligatoriosModificarEliminar();
            if (!(Character.isDigit(e.getKeyChar()) && (vistaCliente.jTextFieldTelefonoModificarEliminar.getText().length() <= 7))) {//Valida que el usuario escriba solo letras y que no supere los 8 digitos
                e.consume();
            }
        }
    }

    @Override

    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
