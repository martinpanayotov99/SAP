package DataBase;

import java.sql.*;

public class Connection {

    private java.sql.Connection connection;

    public Connection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sap_test", "root", "Martin99");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public java.sql.Connection getConnection() {
        return connection;
    }
}
