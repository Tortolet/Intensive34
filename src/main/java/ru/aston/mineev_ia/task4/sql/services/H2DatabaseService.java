package ru.aston.mineev_ia.task4.sql.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class H2DatabaseService {
    private final ResourceBundle resourceBundle;

    public H2DatabaseService() {
        this.resourceBundle = ResourceBundle.getBundle("application");
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String jdbcUrl = resourceBundle.getString("jdbc.url");
        String username = resourceBundle.getString("jdbc.username");
        String password = resourceBundle.getString("jdbc.password");

        Class.forName(resourceBundle.getString("jdbc.driverClassName"));
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}
