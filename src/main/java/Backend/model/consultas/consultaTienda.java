/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.model.consultas;

import Backend.model.dataAccess.managerDB;

import Backend.model.tienda;
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
public class consultaTienda {

    private managerDB baseDatosManager = new managerDB();
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String nombreTabla = "tienda";
    private DefaultTableModel modelo;

    public DefaultTableModel ListarDatos() throws SQLException {
        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Direccion");
        modelo.addColumn("Codigo");
        modelo.addColumn("Telefono 1");
        modelo.addColumn("Telefono 2");
        modelo.addColumn("Correo Electronico");
        modelo.addColumn("Horario");
        Object[] fila = new Object[7];

        try {
            connection = baseDatosManager.getConexion();
            String query = "SELECT * FROM " + nombreTabla + ";";

            resultSet = baseDatosManager.getResultSet(query);

            while (resultSet.next()) {
                fila[0] = (resultSet.getString(1));
                fila[1] = (resultSet.getString(2));
                fila[2] = (resultSet.getString(3));
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

    public List Listar() throws SQLException {
        List<tienda> datos = new ArrayList<>();

        try {
            connection = baseDatosManager.getConexion();
            String query = "SELECT * FROM " + nombreTabla + ";";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tienda tienda = new tienda();
                tienda.setNombre(resultSet.getString(1));
                tienda.setDireccion(resultSet.getString(2));
                tienda.setCodigo(resultSet.getString(3));
                tienda.setTelefono1(resultSet.getInt(4));
                tienda.setTelefono2(resultSet.getInt(5));
                tienda.setCorreoElectronico(resultSet.getString(6));
                tienda.setHorario(resultSet.getString(7));
                datos.add(tienda);

            }
        } catch (Exception e) {
            System.err.print("Ha ocurrido un error: " + e.getMessage());
        } finally {
            preparedStatement = null;
            //  baseDatosManager.cerrar();
        }
        return datos;

    }

    public String[] listaIdTiendas() {
        List<String> lista = new ArrayList<String>();
        String[] string = null;
        try {
            connection = baseDatosManager.getConexion();

            String query = "SELECT codigo FROM " + nombreTabla + ";";

            resultSet = baseDatosManager.getResultSet(query);

            while (resultSet.next()) {
                lista.add(resultSet.getString(1));
            }
            string = new String[lista.size()];
            for (int i = 0; i < lista.size(); i++) {
                string[i] = lista.get(i);
            }

        } catch (Exception e) {
            //System.err.print("Ha ocurrido un error: " + e.getMessage());
            System.out.println("consulta " + nombreTabla + " dice: Ha ocurrido un error: " + e.getMessage());
        } finally {
            baseDatosManager.finalizarConexion(connection);
            preparedStatement = null;
        }

        return string;
    }

    public String crear(tienda tienda) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "INSERT INTO " + nombreTabla
                    + " (nombre,direccion, codigo, telefono1, telefono2, correoElectronico,horario) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, tienda.getNombre());
            preparedStatement.setString(2, tienda.getDireccion());
            preparedStatement.setString(3, tienda.getCodigo());
            preparedStatement.setInt(4, tienda.getTelefono1());
            preparedStatement.setInt(5, tienda.getTelefono2());
            preparedStatement.setString(6, tienda.getCorreoElectronico());
            preparedStatement.setString(7, tienda.getHorario());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            //  baseDatosManager.cerrar();
        }
        return nombreTabla + " Codigo: " + tienda.getCodigo() + " registrado exitosamente";
    }

    public String modificar(tienda tienda) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "UPDATE " + nombreTabla
                    + " SET nombre=?, direccion=?, telefono1=?, telefono2=?, correoElectronico=?, horario=? "
                    + "WHERE codigo = '" + tienda.getCodigo() + "';";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, tienda.getNombre());
            preparedStatement.setString(2, tienda.getDireccion());
            preparedStatement.setString(3, tienda.getCodigo());
            preparedStatement.setInt(4, tienda.getTelefono1());
            preparedStatement.setInt(5, tienda.getTelefono2());
            preparedStatement.setString(6, tienda.getCorreoElectronico());
            preparedStatement.setString(7, tienda.getHorario());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            //baseDatosManager.cerrar();
        }
        return nombreTabla + " Codigo: " + tienda.getCodigo() + " modificado exitosamente";
    }

    public String eliminar(String codigo) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "DELETE FROM " + nombreTabla
                    + " WHERE codigo = ? ;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, codigo);
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            //baseDatosManager.cerrar();
        }
        return nombreTabla + " Codigo: " + codigo + " eliminado exitosamente";
    }
}
