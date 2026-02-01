package main.java.zone_fit.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public static Connection getConnection() {
        Connection connection = null;
        String dataBase = "zonefit_db";
        String host = "localhost";
        int port = 3306;
        String user = "root";
        String pass = "root";
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dataBase;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Error connecting to DB: " + e.getMessage());
        }
        return connection;
    }

    static void main() {
        Connection connection = DbConnection.getConnection();
        if (connection != null) {
            System.out.println("Connection to DB established: " + connection);
        } else {
            System.out.println("Error connecting to DB");
        }
    }
}
