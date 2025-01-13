package BackEnd;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataApi {

    /**
     * Retrieves a list of classroom rooms from the database.
     * <p>
     * This method connects to the database using {@code DatabaseConnector.getConnection()},
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
        return rooms;
    }

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
        return rooms;
    }
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
        return rooms;
    }
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
        return rooms;
    }
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
        return rooms;
    }

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
        return rooms;
    }

}