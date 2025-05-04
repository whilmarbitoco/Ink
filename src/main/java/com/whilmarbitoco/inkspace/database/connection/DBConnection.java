package com.whilmarbitoco.inkspace.database.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {


    private static Connection conn;

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(getPath())) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Error loading database properties", e);
        }
        return props;
    }

    public static Connection getConnection() {
        Properties props = loadProperties();
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try {

            if (conn == null) {
                conn = DriverManager.getConnection(url, user, password);
            }

            return conn;

        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    private static String getPath() {
        return (System.getProperty("user.dir") + "/src/main/java/com/whilmarbitoco/config/db.properties");
    }
}
