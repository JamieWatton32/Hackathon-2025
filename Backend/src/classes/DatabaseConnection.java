package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final static String PATH = "jdbc:sqlite:sqlite/database.db";
    private static Connection connection;

    public static Connection getConnection(){
        if(connection == null){
            try{
                connection = DriverManager.getConnection(PATH);
            } catch (SQLException e) {
                System.err.println("Error establishing connection: " + e.getMessage());
            }

        }
        return connection;
    }

    public static void closeConnection(){
        try{
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
