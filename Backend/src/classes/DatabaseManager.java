package classes;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private final static String PATH = "jdbc:sqlite:sqlite/database.db";
    private static void createDB(){
        File file = new File("sqlite/database.db");
        if(file.exists()){
            return;
        }
        try (var conn = DriverManager.getConnection(PATH)) {
            if (conn != null) {
                var meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void createTables(){
        // SQL statement for creating a new table
        var sql = "CREATE TABLE IF NOT EXISTS classrooms ("
                + "	id INTEGER PRIMARY KEY,"
                + "	complete text NOT NULL,"
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
        createDB();
        createTables();
    }
}
