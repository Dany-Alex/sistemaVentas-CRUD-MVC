/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.model.consultas;

import Backend.model.dataAccess.managerDB;

import Backend.model.tiempoTiendas;
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
public class consultaTiempo {

    private managerDB baseDatosManager = new managerDB();
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String nombreTabla = "tiempotiendas";
    private DefaultTableModel modelo;

    public DefaultTableModel ListarDatos() throws SQLException {
        modelo = new DefaultTableModel();
        modelo.addColumn("Tienda Origen");
        modelo.addColumn("Tienda Destino");
        modelo.addColumn("Tiempo");

        Object[] fila = new Object[3];

        try {
            connection = baseDatosManager.getConexion();
            String query = "SELECT * FROM " + nombreTabla + ";";

            resultSet = baseDatosManager.getResultSet(query);

            while (resultSet.next()) {

                fila[0] = (resultSet.getString(1));
                fila[1] = (resultSet.getString(2));
                fila[2] = (resultSet.getInt(3));

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
        List<tiempoTiendas> datos = new ArrayList<>();

        try {
            connection = baseDatosManager.getConexion();
            String query = "SELECT * FROM " + nombreTabla + ";";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tiempoTiendas tiempo = new tiempoTiendas();
                tiempo.setTiendaOrigen(resultSet.getString(1));
                tiempo.setTiendaDestino(resultSet.getString(2));
                tiempo.setTiempo(resultSet.getInt(3));

                datos.add(tiempo);

            }
        } catch (Exception e) {
            System.err.print("Ha ocurrido un error: " + e.getMessage());
        } finally {
            preparedStatement = null;
            // baseDatosManager.cerrar();
        }
        return datos;

    }

    public String crear(tiempoTiendas tiempo) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "INSERT INTO " + nombreTabla
                    + " (tiendaOrigen,tiendaDestino, tiempo) "
                    + "VALUES(?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, tiempo.getTiendaOrigen());
            preparedStatement.setString(2, tiempo.getTiendaDestino());
            preparedStatement.setInt(3, tiempo.getTiempo());

            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            // baseDatosManager.cerrar();
        }
        return "Tiempo tienda: " + tiempo.getTiendaOrigen() + "a tienda: " + tiempo.getTiendaDestino() + " registrado exitosamente";
    }

    public String modificar(tiempoTiendas tiempo) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "UPDATE " + nombreTabla
                    + " "
                    + "SET tiempo=? "
                    + "WHERE tiendaOrigen = '" + tiempo.getTiendaOrigen() + "' "
                    + "AND tiendaDestino = '" + tiempo.getTiendaDestino() + "';";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, tiempo.getTiempo());

            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            // baseDatosManager.cerrar();
        }
        return "Tiempo tienda: " + tiempo.getTiendaOrigen() + "a tienda: " + tiempo.getTiendaDestino() + " modificado exitosamente";
    }

    public String eliminar(String tiendaOrigen, String tiendaDestino) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "DELETE FROM " + nombreTabla
                    + " "
                    + "WHERE tiendaOrigen = ? "
                    + "AND tiendaDestino = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tiendaOrigen);
            preparedStatement.setString(2, tiendaDestino);

            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            // baseDatosManager.cerrar();
        }
        return "Tiempo tienda: " + tiendaOrigen + "a tienda: " + tiendaDestino + " eliminado exitosamente";
    }
}
