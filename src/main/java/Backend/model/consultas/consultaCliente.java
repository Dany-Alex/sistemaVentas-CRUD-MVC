/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.model.consultas;

import Backend.model.dataAccess.managerDB;
import Backend.model.cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Artist
 */
public class consultaCliente {

    private managerDB baseDatosManager = new managerDB();
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String nombreTabla = "cliente";
    private DefaultTableModel modelo;

    public DefaultTableModel ListarDatos() throws SQLException {
        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Nit");
        modelo.addColumn("Telefono");
        modelo.addColumn("CreditoCompra");
        modelo.addColumn("DPI");
        modelo.addColumn("Correo Electronico");
        modelo.addColumn("Direccion");
        Object[] fila = new Object[7];

        try {
            connection = baseDatosManager.getConexion();
            String query = "SELECT * FROM " + nombreTabla + ";";

            resultSet = baseDatosManager.getResultSet(query);

            while (resultSet.next()) {
                fila[0] = (resultSet.getString(1));
                fila[1] = (resultSet.getString(2));
                fila[2] = (resultSet.getInt(3));
                fila[3] = (resultSet.getInt(4));
                fila[4] = (resultSet.getInt(5));
                fila[5] = (resultSet.getString(6));
                fila[6] = (resultSet.getString(7));
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            //System.err.print("Ha ocurrido un error: " + e.getMessage());
            System.out.println("consulta " + nombreTabla + " dice: Ha ocurrido un error: " + e.getMessage());
        } finally {
            baseDatosManager.finalizarConexion(connection);
            preparedStatement = null;
        }
        return modelo;

    }

    public String crear(cliente cliente) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "INSERT INTO " + nombreTabla
                    + " (nombre,nit, telefono, creditoCompra, dpi, correoElectronico,direccion) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getNit());
            preparedStatement.setInt(3, cliente.getTelefono());
            preparedStatement.setInt(4, cliente.getCreditoCompra());
            preparedStatement.setInt(5, cliente.getDpi());
            preparedStatement.setString(6, cliente.getCorreoElectronico());
            preparedStatement.setString(7, cliente.getDireccion());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            baseDatosManager.finalizarConexion(connection);
            preparedStatement = null;
        }
        return nombreTabla + " Codigo: " + cliente.getNit() + " registrado exitosamente";
    }

    public String modificar(cliente cliente) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "UPDATE " + nombreTabla
                    + " SET nombre=?, telefono=?, creditoCompra=?, dpi=?, correoElectronico=?,direccion=?"
                    + " WHERE nit = '" + cliente.getNit() + "';";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setInt(2, cliente.getTelefono());
            preparedStatement.setInt(3, cliente.getCreditoCompra());
            preparedStatement.setInt(4, cliente.getDpi());
            preparedStatement.setString(5, cliente.getCorreoElectronico());
            preparedStatement.setString(6, cliente.getDireccion());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            baseDatosManager.finalizarConexion(connection);
            preparedStatement = null;
        }
        return "cliente Codigo: " + cliente.getNit() + " modificado exitosamente";
    }

    public String eliminar(String codigo) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "DELETE FROM " + nombreTabla
                    + " WHERE nit = ? ;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, codigo);
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            baseDatosManager.finalizarConexion(connection);
            preparedStatement = null;
        }
        return nombreTabla + " Codigo: " + codigo + " eliminado exitosamente";
    }
}
