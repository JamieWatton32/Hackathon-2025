package BackEnd;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Api class for the Database
 * <p>
 * Contains methods for getting data from the database
 * </p>
 * Usage Example:
 * <pre>
 *    ArrayList<String> classrooms = DataApi.getClassrooms();
 *    classrooms.forEach(System.out::println);
 * </pre>
 */
public class DataApi {

    /**
     * Retrieves a list of classroom rooms from the database.
     * <p>
     * Connects to the database using {@code DatabaseConnector.getConnection()},
     * executes a query to fetch all rows from the "Classrooms" table, and extracts the values
     * from the "room" column. The result is returned as an {@code ArrayList<String>}.
     * </p>
     *
     * @return an {@code ArrayList<String>}  containing the names of all classrooms
     *         retrieved from the database. If no classrooms are found or an error occurs,
     *         an empty list is returned.
     * =
     */
    public static ArrayList<String> getClassrooms() {
        String sql = "SELECT * FROM Classrooms";
        ArrayList<String> rooms = new ArrayList<>();
        try (var conn = DatabaseConnector.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            int columnIndex = 0;
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String columnName = "room";

            for (int col = 1; col <= columnCount; col++) {
                if (metaData.getColumnLabel(col).equalsIgnoreCase(columnName)) {
                     // Return the column index (1-based)
                    columnIndex = col;

                }
            }
            while (rs.next()) {
                rooms.add(rs.getString(columnIndex)); // Add the row to the table
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving classrooms: " + e.getMessage());
        }
        DatabaseConnector.closeConnection();
        return rooms;
    }
    /**
     * Retrieves a list of Culinary rooms from the database.
     * <p>
     * Connects to the database using {@code DatabaseConnector.getConnection()},
     * executes a query to fetch all rows from the "Culinary" table, and extracts the values
     * from the "room" column. The result is returned as an {@code ArrayList<String>}.
     * </p>
     *
     * @return an {@code ArrayList<String>}  containing the names of all culinary  rooms
     *         retrieved from the database. If no rooms are found or an error occurs,
     *         an empty list is returned.
     * =
     */
    public static ArrayList<String> getCulinary() {
        String sql = "SELECT * FROM Culinary";
        ArrayList<String> rooms = new ArrayList<>();
        try (var conn = DatabaseConnector.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            int columnIndex = 0;
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String columnName = "room";

            for (int col = 1; col <= columnCount; col++) {
                if (metaData.getColumnLabel(col).equalsIgnoreCase(columnName)) {
                    // Return the column index (1-based)
                    columnIndex = col;

                }
            }
            while (rs.next()) {
                rooms.add(rs.getString(columnIndex)); // Add the row to the table
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving Culinary rooms: " + e.getMessage());
        }
        DatabaseConnector.closeConnection();
        return rooms;
    }
    /**
     * Retrieves a list of Lab rooms from the database.
     * <p>
     * Connects to the database using {@code DatabaseConnector.getConnection()},
     * executes a query to fetch all rows from the "Laboratory" table, and extracts the values
     * from the "room" column. The result is returned as an {@code ArrayList<String>}.
     * </p>
     *
     * @return an {@code ArrayList<String>}  containing the names of all labs  rooms
     *         retrieved from the database. If no rooms are found or an error occurs,
     *         an empty list is returned.
     * =
     */
    public static ArrayList<String> getLaboratory() {
        String sql = "SELECT * FROM Laboratory";
        ArrayList<String> rooms = new ArrayList<>();
        try (var conn = DatabaseConnector.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            int columnIndex = 0;
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String columnName = "room";

            for (int col = 1; col <= columnCount; col++) {
                if (metaData.getColumnLabel(col).equalsIgnoreCase(columnName)) {
                    // Return the column index (1-based)
                    columnIndex = col;

                }
            }
            while (rs.next()) {
                rooms.add(rs.getString(columnIndex)); // Add the row to the table
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving Laboratories: " + e.getMessage());
        }
        DatabaseConnector.closeConnection();
        return rooms;
    }
    /**
     * Retrieves a list of Maintenance rooms from the database.
     * <p>
     * Connects to the database using {@code DatabaseConnector.getConnection()},
     * executes a query to fetch all rows from the "Maintenance" table, and extracts the values
     * from the "room" column. The result is returned as an {@code ArrayList<String>}.
     * </p>
     *
     * @return an {@code ArrayList<String>}  containing the names of all maintenance rooms
     *         retrieved from the database. If no rooms are found or an error occurs,
     *         an empty list is returned.
     * =
     */
    public static ArrayList<String> getMaintenance() {
        String sql = "SELECT * FROM Maintenance";
        ArrayList<String> rooms = new ArrayList<>();
        try (var conn = DatabaseConnector.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            int columnIndex = 0;
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String columnName = "room";

            for (int col = 1; col <= columnCount; col++) {
                if (metaData.getColumnLabel(col).equalsIgnoreCase(columnName)) {
                    // Return the column index (1-based)
                    columnIndex = col;

                }
            }
            while (rs.next()) {
                rooms.add(rs.getString(columnIndex)); // Add the row to the table
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving maintenance rooms: " + e.getMessage());
        }
        DatabaseConnector.closeConnection();
        return rooms;
    }
    /**
     * Retrieves a list of shops from the database.
     * <p>
     * Connects to the database using {@code DatabaseConnector.getConnection()},
     * executes a query to fetch all rows from the "Shops" table, and extracts the values
     * from the "room" column. The result is returned as an {@code ArrayList<String>}.
     * </p>
     *
     * @return an {@code ArrayList<String>}  containing the names of all shops
     *         retrieved from the database. If no rooms are found or an error occurs,
     *         an empty list is returned.
     * =
     */
    public static ArrayList<String> getShops() {
        String sql = "SELECT * FROM Shops";
        ArrayList<String> rooms = new ArrayList<>();
        try (var conn = DatabaseConnector.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            int columnIndex = 0;
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String columnName = "room";

            for (int col = 1; col <= columnCount; col++) {
                if (metaData.getColumnLabel(col).equalsIgnoreCase(columnName)) {
                    // Return the column index (1-based)
                    columnIndex = col;

                }
            }
            while (rs.next()) {
                rooms.add(rs.getString(columnIndex)); // Add the row to the table
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving shops: " + e.getMessage());
        }
        DatabaseConnector.closeConnection();
        return rooms;
    }
    /**
     * Retrieves a list of Officesfrom the database.
     * <p>
     * Connects to the database using {@code DatabaseConnector.getConnection()},
     * executes a query to fetch all rows from the "Offices" table, and extracts the values
     * from the "room" column. The result is returned as an {@code ArrayList<String>}.
     * </p>
     *
     * @return an {@code ArrayList<String>}  containing the names of all offices
     *         retrieved from the database. If no rooms are found or an error occurs,
     *         an empty list is returned.
     * =
     */
    public static ArrayList<String> getOffices() {
        String sql = "SELECT * FROM Offices";
        ArrayList<String> rooms = new ArrayList<>();
        try (var conn = DatabaseConnector.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            int columnIndex = 0;
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String columnName = "room";

            for (int col = 1; col <= columnCount; col++) {
                if (metaData.getColumnLabel(col).equalsIgnoreCase(columnName)) {
                    // Return the column index (1-based)
                    columnIndex = col;

                }
            }
            while (rs.next()) {
                rooms.add(rs.getString(columnIndex)); // Add the row to the table
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving offices: " + e.getMessage());
        }
        DatabaseConnector.closeConnection();
        return rooms;
    }

}