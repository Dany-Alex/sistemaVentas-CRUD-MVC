/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.controlador;

import Backend.model.cliente;
import Backend.model.consultas.*;
import Fonted.empleado.menuEmpleado;
import Fonted.empleado.vistaCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Artist
 */
public class controladorMenuEmpleado implements ActionListener, ChangeListener, MouseListener {

    private String titulo = "Intelaf";
    public String codigoTienda;
    private consultaEmpleado consultaEmpleado = new consultaEmpleado();
    private consultaProducto consultaProducto = new consultaProducto();
    private consultaTienda consultaTienda = new consultaTienda();
    private consultaPedido consultaPedido = new consultaPedido();
    private consultaTiempo consultaTiempo = new consultaTiempo();
    private menuEmpleado menuEmpleado = new menuEmpleado();
    vistaCliente vistaCliente;
    controladorCliente controladorCliente;

    public controladorMenuEmpleado(menuEmpleado menuEmpleado, String codigoTienda) {
        this.menuEmpleado = menuEmpleado;
        this.codigoTienda = codigoTienda;
        this.menuEmpleado.setLocationRelativeTo(null);
        //this.menuEmpleado.setExtendedState(this.menuEmpleado.MAXIMIZED_BOTH);
        this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado");
        this.menuEmpleado.jTabbedPaneMenuEmpleado.addChangeListener(this);
        this.menuEmpleado.jTabbedPaneAdministrar.addChangeListener(this);
    }

    public String getTituloCompuesto() {
        return "Intelaf - Tienda codigo: " + codigoTienda;
    }

    public String getCodigoTienda() {
        return codigoTienda;
    }

    public void setCodigoTienda(String codigoTienda) {
        this.codigoTienda = codigoTienda;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == menuEmpleado.jTabbedPaneMenuEmpleado) {
            switch (menuEmpleado.jTabbedPaneMenuEmpleado.getSelectedIndex()) {
                case 0:
                    controladorCliente.limpiarTextModificarEliminar();
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado - Ventas");
                    break;
                case 1:
                    controladorCliente.limpiarTextModificarEliminar();
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado - Productos");
                    break;
                case 2:
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado - Administrar - Cliente");
                    vistaCliente = new vistaCliente();
                    cliente cliente = new cliente();
                    controladorCliente = new controladorCliente(cliente, vistaCliente, menuEmpleado, codigoTienda);
                    this.menuEmpleado.jDesktopPaneCliente.add(vistaCliente);
                    vistaCliente.show();
                    break;
                default:
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado ");
                    break;
            }
        } else if (e.getSource() == menuEmpleado.jTabbedPaneAdministrar) {
            switch (menuEmpleado.jTabbedPaneAdministrar.getSelectedIndex()) {
                case 0:
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado - Administrar - Cliente");
                    controladorCliente.mostrarLista();
                    controladorCliente.limpiarTextModificarEliminar();
                    break;
                case 1:
                    controladorCliente.limpiarTextModificarEliminar();
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado - Administrar - Empleado");
                    try {
                        menuEmpleado.jTableEmpleado.setModel(consultaEmpleado.ListarDatos());
                    } catch (Exception ex) {
                        System.out.println("Menu empleado dice: Ha ocurrido un error: " + ex.toString());
                    }
                    break;
                case 2:
                    controladorCliente.limpiarTextModificarEliminar();
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado - Administrar - Producto");
                    try {
                        menuEmpleado.jTableProducto.setModel(consultaProducto.ListarDatos());
                    } catch (Exception ex) {
                        System.out.println("Menu empleado dice: Ha ocurrido un error: " + ex.toString());
                    }
                    break;
                case 3:
                    controladorCliente.limpiarTextModificarEliminar();
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado - Administrar - Tienda");
                    try {
                        menuEmpleado.jTableTienda.setModel(consultaTienda.ListarDatos());
                    } catch (Exception ex) {
                        System.out.println("Menu empleado dice: Ha ocurrido un error: " + ex.toString());
                    }
                    break;
                case 4:
                    controladorCliente.limpiarTextModificarEliminar();
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado - Administrar - Pedido");
                    try {
                        menuEmpleado.jTablePedido.setModel(consultaPedido.ListarDatos());
                    } catch (Exception ex) {
                        System.out.println("Menu empleado dice: Ha ocurrido un error: " + ex.toString());
                    }
                    break;
                case 5:
                    controladorCliente.limpiarTextModificarEliminar();
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado - Administrar - Tiempo Tiendas");
                    try {
                        menuEmpleado.jTableTiempoTiendas.setModel(consultaTiempo.ListarDatos());

                    } catch (Exception ex) {
                        System.out.println("Menu empleado dice: Ha ocurrido un error: " + ex.toString());
                    }
                    break;
                default:
                    this.menuEmpleado.setTitle(getTituloCompuesto() + " - Empleado - Administrar");
                    break;
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}
