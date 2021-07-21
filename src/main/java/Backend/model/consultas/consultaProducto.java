/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.model.consultas;

import Backend.model.dataAccess.managerDB;

import Backend.model.producto;
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
public class consultaProducto {

    private managerDB baseDatosManager = new managerDB();
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String nombreTabla = "producto";
    private DefaultTableModel modelo;

    public List Listar() throws SQLException {
        List<producto> datos = new ArrayList<>();

        try {
            connection = baseDatosManager.getConexion();
            String query = "SELECT * FROM " + nombreTabla + ";";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                producto producto = new producto();
                producto.setNombre(resultSet.getString(1));
                producto.setFabricante(resultSet.getString(2));
                producto.setCodigoProducto(resultSet.getString(3));
                producto.setCantidad(resultSet.getInt(4));
                producto.setPrecio(resultSet.getDouble(5));
                producto.setTiendaCodigo(resultSet.getString(7));
                producto.setDescripcion(resultSet.getString(8));
                producto.setGarantia(resultSet.getInt(9));
                datos.add(producto);

            }
        } catch (Exception e) {
            System.err.print("Ha ocurrido un error: " + e.getMessage());
        } finally {
            preparedStatement = null;
            //  baseDatosManager.cerrar();
        }
        return datos;

    }

    public DefaultTableModel ListarDatos() throws SQLException {
        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Fabricante");
        modelo.addColumn("Codigo Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Tienda Codigo");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Garantia");
        Object[] fila = new Object[8];

        try {
            connection = baseDatosManager.getConexion();
            String query = "SELECT * FROM " + nombreTabla + ";";

            resultSet = baseDatosManager.getResultSet(query);

            while (resultSet.next()) {
                fila[0] = (resultSet.getString(1));
                fila[1] = (resultSet.getString(2));
                fila[2] = (resultSet.getString(3));
                fila[3] = (resultSet.getInt(4));
                fila[4] = (resultSet.getDouble(5));
                fila[5] = (resultSet.getString(6));
                fila[6] = (resultSet.getString(7));
                fila[7] = (resultSet.getInt(8));
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

    public String crear(producto producto) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "INSERT INTO " + nombreTabla
                    + " (nombre,fabricante, codigo, cantidad, precio,tienda_codigo, descripcion,garantia) "
                    + "VALUES(?, ?, ?, ?, ?, ?,?,?)"
                    + ";";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setString(2, producto.getFabricante());
            preparedStatement.setString(3, producto.getCodigoProducto());
            preparedStatement.setInt(4, producto.getCantidad());
            preparedStatement.setDouble(5, producto.getPrecio());
            preparedStatement.setString(6, producto.getTiendaCodigo());
            preparedStatement.setString(7, producto.getDescripcion());
            preparedStatement.setInt(8, producto.getGarantia());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            //  baseDatosManager.cerrar();
        }
        return nombreTabla + " Codigo: " + producto.getCodigoProducto() + " registrado exitosamente";
    }

    public String modificar(producto producto) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "UPDATE " + nombreTabla
                    + " SET nombre=?, fabricante=?, cantidad=?, precio=?,tienda_codigo=?, descripcion=?, garantia=? "
                    + "WHERE codigo = '" + producto.getCodigoProducto() + "';";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setString(2, producto.getFabricante());
            preparedStatement.setInt(3, producto.getCantidad());
            preparedStatement.setDouble(4, producto.getPrecio());
            preparedStatement.setString(5, producto.getTiendaCodigo());
            preparedStatement.setString(6, producto.getDescripcion());
            preparedStatement.setInt(7, producto.getGarantia());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            // baseDatosManager.cerrar();
        }
        return "cliente Codigo: " + producto.getCodigoProducto() + " modificado exitosamente";
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
