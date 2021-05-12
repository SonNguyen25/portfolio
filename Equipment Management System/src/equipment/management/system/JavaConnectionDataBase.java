/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipment.management.system;

/**
 *
 * @author Bao Son
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class JavaConnectionDataBase {
    
    public static Connection connectToDatabase() {
        Connection connection = null;
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        try { 
            //a configuration string to store information of database
            String dbUrl = "jdbc:sqlserver://BAOSON-LAPTOP\\BAOSONSQL;databaseName=Equipment Management;user=sa;password=123456";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//a driver to help with the connection to database
            connection = DriverManager.getConnection(dbUrl); 
            //create a connection from the information from string dbUrl
            if (connection != null) {
                DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
            return connection;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
