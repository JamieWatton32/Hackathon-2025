package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Utility class for connecting to the DB
 * <p>
 * Connects to the database using {@code DatabaseConnector.getConnection()}
 * Allows for connection closure using {@code DatabaseConnector.closeConnection()}
 * </p>
 */
public class DatabaseConnector {
    private final static String PATH = "jdbc:sqlite:sqlite/database.db";
    private static Connection connection;
    /**
     * Method for database connection
     * <p>
     *  Use in try-catch allows for automatic closure of connection.
     * </p>
     * Usage Example:
     * <PRE>
     *     try(var conn = DatabaseConnector.getConnection()){
     *         ..
     *     }catch(SQlException e){
     *         ..
     *     }
     * </PRE>
     * @return {$code Connection} on success {@code null} on failure.
     */
    public static Connection getConnection(){
        try {
            // Check if the connection is null or closed
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(PATH);
            }
        } catch (SQLException e) {
            System.err.println("Error establishing connection: " + e.getMessage());
            connection = null; // Reset connection if something went wrong
        }
        return connection;
    }
    /**
     * Method for closing DB connection
     * <p>
     *  Only used if connection is established outside a try-catch. Caution should be taken to ensure
     *  connection is closed.
     * </p>
     * Usage Example:
     * <PRE>
     *    var conn = DatabaseConnector.getConnection();
     *     .. do things
     *     if(DatabaseConnector.closeConnection()){
     *         ...
     *     }
     * </PRE>
     * @return {$code true} on connection closure, {@code false} on failure due to DB error
     * or because connection is null.
     */
    public static boolean closeConnection(){
        try{
            if(connection != null){
                connection.close();
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());

        }
        return false;
    }
}
