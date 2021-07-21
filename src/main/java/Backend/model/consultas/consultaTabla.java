/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.model.consultas;

import Backend.model.dataAccess.managerDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Artist
 */
public class consultaTabla {

    private managerDB baseDatosManager = new managerDB();
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public boolean verificarTablasVacias() throws SQLException {
        boolean vacio = false;
        try {

            connection = baseDatosManager.getConexion();
            String query = "SELECT count(*)"
                    + "    FROM  INFORMATION_SCHEMA.PARTITIONS"
                    + "    WHERE TABLE_SCHEMA = ?"
                    + "    AND   TABLE_ROWS=0;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "intelaf");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int resultadoConsulta = Integer.parseInt(resultSet.getString(1));
                System.out.println("resultset: " + resultadoConsulta);
                if (resultadoConsulta == 6) {
                    System.out.println("Todas las tablas de la BD estan vacias ");

                    vacio = true;
                } else {
                    System.out.println("Todas las tablas de la BD NO estan vacias ");
                    vacio = false;

                }
            }

        } catch (Exception e) {
            System.err.print("Consulta tabla dice: Ha ocurrido un error: " + e.getMessage());
            System.out.println("Consulta tabla dice: Ha ocurrido un error: " + e.getMessage());

        } finally {

            // preparedStatement.close();
            //resultSet.close();
            //baseDatosManager.finalizarConexion(connection);
        }
        return vacio;
    }
}
