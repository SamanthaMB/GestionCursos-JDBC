package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/database.properties"));

            String user = properties.getProperty("user");
            String pass = properties.getProperty("pass");
            String url = properties.getProperty("url");

            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión establecida correctamente");

        } catch (Exception e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }
}

