package net.sqlite;

import classes.ClassroomsDAO;
import classes.Complete;
import classes.DatabaseConnection;
import classes.DatabaseManager;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static String path = "jdbc:sqlite:sqlite/database.db";


    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "data/Classroom Procedure.csv";

       List beans =new CsvToBeanBuilder(new FileReader(fileName))
                .withType(Complete.class)
                .build()
                .parse();

        beans.forEach(System.out::println);


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

