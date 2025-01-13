pckage net.sqlite;

import classes.ClassroomsDAO;
import classes.DatabaseConnection;
import classes.DatabaseManager;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private final static String path = "jdbc:sqlite:sqlite/database.db";


    public static void main(String[] args) {



        try (Connection connection = DriverManager.getConnection(path)) {
            if (connection != null) {
                DatabaseManager.setupDatabase();
                System.out.println("Database setup complete.");
                ClassroomsDAO.insertClassrooms(1,"Yes");
                ClassroomsDAO.getClassrooms();

            }
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }


    }
}

