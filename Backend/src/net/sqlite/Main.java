package net.sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private final static String path = "jdbc:sqlite:sqlite/database.db";


    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(path)) {
            if (connection != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }

    }
}

