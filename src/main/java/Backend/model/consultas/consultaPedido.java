/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.model.consultas;

import Backend.model.dataAccess.managerDB;

import Backend.model.pedido;
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
public class consultaPedido {

    private managerDB baseDatosManager = new managerDB();
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String nombreTabla = "pedido";
    private DefaultTableModel modelo;

    public DefaultTableModel ListarDatos() throws SQLException {
        modelo = new DefaultTableModel();
        modelo.addColumn("Codigo Pedido");
        modelo.addColumn("Tienda 1");
        modelo.addColumn("Tienda 2");
        modelo.addColumn("Fecha");
        modelo.addColumn("Cliente Nit");
        modelo.addColumn("Producto codigo");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
        modelo.addColumn("Anticipo");
        Object[] fila = new Object[9];

        try {
            connection = baseDatosManager.getConexion();
            String query = "SELECT * FROM " + nombreTabla + ";";

            resultSet = baseDatosManager.getResultSet(query);

            while (resultSet.next()) {
                fila[0] = (resultSet.getInt(1));
                fila[1] = (resultSet.getString(2));
                fila[2] = (resultSet.getString(3));
                fila[3] = (resultSet.getDate(4));
                fila[4] = (resultSet.getString(5));
                fila[5] = (resultSet.getString(6));
                fila[6] = (resultSet.getInt(7));
                fila[7] = (resultSet.getDouble(8));
                fila[8] = (resultSet.getDouble(9));
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
        List<pedido> datos = new ArrayList<>();

        try {
            connection = baseDatosManager.getConexion();
            String query = "SELECT * FROM " + nombreTabla + ";";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                pedido pedido = new pedido();
                pedido.setCodigoPedido(resultSet.getInt(1));
                pedido.setTienda1(resultSet.getString(2));
                pedido.setTienda2(resultSet.getString(3));
                pedido.setFecha(resultSet.getDate(4));
                pedido.setCliente_nit(resultSet.getString(5));
                pedido.setProducto_codigo(resultSet.getString(6));
                pedido.setCantidad(resultSet.getInt(7));
                pedido.setTotal(resultSet.getDouble(8));
                pedido.setAnticipo(resultSet.getDouble(9));
                datos.add(pedido);

            }
        } catch (Exception e) {
            System.err.print("Ha ocurrido un error: " + e.getMessage());
        } finally {
            preparedStatement = null;
            //baseDatosManager.cerrar();
        }
        return datos;

    }

    public String crear(pedido pedido) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "INSERT INTO " + nombreTabla
                    + " (codigoPedido,tienda1, tienda2, fecha, cliente_nit, producto_codigo,cantidad,total,anticipo) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, pedido.getCodigoPedido());
            preparedStatement.setString(2, pedido.getTienda1());
            preparedStatement.setString(3, pedido.getTienda2());
            preparedStatement.setDate(4, pedido.getFecha());
            preparedStatement.setString(5, pedido.getCliente_nit());
            preparedStatement.setString(6, pedido.getProducto_codigo());
            preparedStatement.setInt(7, pedido.getCantidad());
            preparedStatement.setDouble(8, pedido.getTotal());
            preparedStatement.setDouble(9, pedido.getAnticipo());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            // baseDatosManager.cerrar();
        }
        return nombreTabla + " Codigo: " + pedido.getCodigoPedido() + " registrado exitosamente";
    }

    public String modificar(pedido pedido) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "UPDATE " + nombreTabla
                    + " SET tienda1=?, tienda2=?, fecha=?, cliente_nit=?, producto_codigo=?,cantidad=?,total=?,anticipo=? "
                    + "WHERE codigoPedido = '" + pedido.getCodigoPedido() + "';";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, pedido.getTienda1());
            preparedStatement.setString(2, pedido.getTienda2());
            preparedStatement.setDate(3, pedido.getFecha());
            preparedStatement.setString(4, pedido.getCliente_nit());
            preparedStatement.setString(5, pedido.getProducto_codigo());
            preparedStatement.setInt(6, pedido.getCantidad());
            preparedStatement.setDouble(7, pedido.getTotal());
            preparedStatement.setDouble(8, pedido.getAnticipo());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            //baseDatosManager.cerrar();
        }
        return "cliente Codigo: " + pedido.getCodigoPedido() + " modificado exitosamente";
    }

    public String eliminar(String codigo) throws SQLException {
        try {
            connection = baseDatosManager.getConexion();
            String query = "DELETE FROM " + nombreTabla
                    + " WHERE codigoPedido = ? ;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, codigo);
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            return "Consulta " + nombreTabla + " Error: " + e.toString();

        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            preparedStatement = null;
            // baseDatosManager.cerrar();
        }
        return nombreTabla + " Codigo: " + codigo + " eliminado exitosamente";
    }
}
