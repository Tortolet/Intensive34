package ru.aston.mineev_ia.task4.sql.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class ExecuteSqlService {
    public static String fromFile(File file) throws SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder query = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                query.append(line).append("\n");
            }
            return query.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
