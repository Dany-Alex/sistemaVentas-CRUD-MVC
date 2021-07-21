/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.model.consultas;

import Backend.model.dataAccess.managerDB;

import Backend.model.empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Artist
 */
public class consultaEmpleado {

    private managerDB baseDatosManager = new managerDB();
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String nombreTabla = "empleado";
    private DefaultTableModel modelo;

    public List Listar() throws SQLException {
        List<empleado> datos = new ArrayList<>();

        try {
            connection = baseDatosManager.getConexion();
            String query = "SELECT * FROM " + nombreTabla + ";";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                empleado empleado = new empleado();
                empleado.setCodigoEmpleado(resultSet.getInt(1));
                empleado.setNombre(resultSet.getString(2));
                empleado.setTelefono(resultSet.getInt(3));
                empleado.setDpi(resultSet.getString(4));
                empleado.setCorreoElectronico(resultSet.getString(5));
                empleado.setDireccion(resultSet.getString(6));

                datos.add(empleado);

            }
        } catch (Exception e) {
            System.err.print("Ha ocurrido un error: " + e.getMessage());
        } finally {
            preparedStatement = null;
            //  baseDatosManager.finalizarConexion(connection);

        }
        return datos;

    }

    public DefaultTableModel ListarDatos() throws SQLException {
        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Codigo Empleado");
        modelo.addColumn("Telefono");
        modelo.addColumn("DPI");
        modelo.addColumn("Correo Electronico");
        modelo.addColumn("Direccion");
        Object[] fila = new Object[6];

        try {
            connection = baseDatosManager.getConexion();
            String query = "SELECT * FROM " + nombreTabla + ";";

            resultSet = baseDatosManager.getResultSet(query);

            while (resultSet.next()) {
                fila[0] = (resultSet.getString(1));
                fila[1] = (resultSet.getInt(2));
                fila[2] = (resultSet.getInt(3));
                fila[3] = (resultSet.getString(4));
                fila[4] = (resultSet.getString(5));
                fila[5] = (resultSet.getString(6));

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

    public String crear(empleado empleado) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "INSERT INTO " + nombreTabla
                    + " (codigoEmpleado,nombre, telefono, dpi, correoElectronico,direccion)"
                    + " VALUES(?, ?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, empleado.getCodigoEmpleado());
            preparedStatement.setString(2, empleado.getNombre());
            preparedStatement.setInt(3, empleado.getTelefono());
            preparedStatement.setString(4, empleado.getDpi());
            preparedStatement.setString(5, empleado.getCorreoElectronico());
            preparedStatement.setString(6, empleado.getDireccion());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();
        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            //baseDatosManager.finalizarConexion(connection);

        }
        return nombreTabla + " Codigo: " + empleado.getCodigoEmpleado() + " registrado exitosamente";
    }

    public String modificar(empleado empleado) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "UPDATE " + nombreTabla
                    + " SET nombre=?, telefono=?, dpi=?, correoElectronico=?, direccion=?"
                    + " WHERE codigoEmpleado = '" + empleado.getCodigoEmpleado() + "';";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setInt(2, empleado.getTelefono());
            preparedStatement.setString(3, empleado.getDpi());
            preparedStatement.setString(4, empleado.getCorreoElectronico());
            preparedStatement.setString(5, empleado.getDireccion());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            //baseDatosManager.finalizarConexion(connection);

        }
        return nombreTabla + " Codigo: " + empleado.getCodigoEmpleado() + " modificado exitosamente";
    }

    public String eliminar(String codigo) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "DELETE FROM " + nombreTabla
                    + " WHERE codigoEmpleado = ? ;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, codigo);
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            // baseDatosManager.finalizarConexion(connection);

        }
        return nombreTabla + " Codigo: " + codigo + " eliminado exitosamente";
    }
}
