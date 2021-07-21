/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.model.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Artist
 */
public class managerDB {
    // Librería de MySQL

    final public static String driver = "com.mysql.jdbc.Driver";

    // Nombre de la base de datos
    public static String database = "intelaf";

    // Host
    public static String hostname = "localhost";

    // Puerto
    public static String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;

    // Nombre de usuario
    public static String username = "root";

    // Clave de usuario
    public static String password = "#T00r123456789";

    //Variables
    Connection connection = null;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Connection getConexion() {

        try {
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    url,
                    username,
                    password);
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "Estado de conexion con " + connection.getCatalog() + " BD: OK" : "Estado de conexion con la BD: FALLO");
            return connection;
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error al conectar con la base de datos de MySQL (" + url + "): " + sqle);
        }
        return connection;
    }

    public Statement getStatement() {

        return statement;
    }

    public PreparedStatement getPreparedStatement() {

        return preparedStatement;
    }

    public ResultSet getResultSet(String MySQLCodigo) throws SQLException {
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(MySQLCodigo);
        } catch (SQLException ex) {
            System.out.println("managerDB diece: Error de conexion con la base de datos: " + ex.toString());

        } finally {
            preparedStatement = null;
        }

        return resultSet;
    }

    public void cerrar() throws SQLException {

        if (connection != null) {
            connection.close();
            System.out.println("Conexion cerrada exitosamente");
        }
    }

    public void finalizarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage()
                        + ". >>> Error de Desconexion!!");
            }
        }
    }
}
