package ru.aston.mineev_ia.task4.sql.services;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void createTables() {
        try(Connection connection = getConnection()) {
            File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\sql\\add_new_tables.sql");

            // создание
            try (Statement statement = connection.createStatement()) {
                statement.execute(ExecuteSqlService.fromFile(file));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
