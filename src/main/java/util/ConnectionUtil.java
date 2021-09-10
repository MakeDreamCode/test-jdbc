package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    static {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can`t load DB driver " + DB_DRIVER);
        }
    }

    public Connection getConnection() {
        Connection connection;
        try {
            Properties dbProperties = new Properties();
            dbProperties.put("user", DB_USER);
            dbProperties.put("password", DB_PASSWORD);
            connection = DriverManager.getConnection(DB_URL,dbProperties);
            System.out.println("Connection was created!");
        } catch (SQLException e) {
            throw new RuntimeException("Can`t connect to DB " + DB_URL);
        }
        return connection;
    }
}
