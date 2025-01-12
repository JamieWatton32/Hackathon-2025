package classes;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static void createTables(){
        // SQL statement for creating a new table
        var sql = "CREATE TABLE IF NOT EXISTS classrooms ("
                + "	id INTEGER PRIMARY KEY,"
                + "	name text NOT NULL,"
                + "	capacity REAL"
                + ");";


        try(var conn = DatabaseConnection.getConnection()) {
                var stmt = conn.createStatement();
                stmt.execute(sql);
            System.out.println("Tables created or already exist.");
        }catch(SQLException e){
            System.err.println("Error executing statement.");
        }

    }

    public static void setupDatabase() {
        createTables();
    }
}
