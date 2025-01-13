package BackEnd;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataApi {

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

}