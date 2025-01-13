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
        var sql = "CREATE TABLE IF NOT EXISTS classrooms ("
                + "	id INTEGER PRIMARY KEY,"
                + " complete TEXT"
                + ");";

        try (var conn = DriverManager.getConnection(PATH);
             var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public static void setupDatabase() {
        createDB();
        createTables();
    }
}
