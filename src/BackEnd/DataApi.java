package BackEnd;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Api class for the Database
 * <p>
 * Contains methods for getting data from the database
 * </p>
 * Usage Example:
 * <pre>
 *    ArrayList<String> classrooms = DataApi.getRooms();
 *    classrooms.forEach(System.out::println);
 * </pre>
 */
public class DataApi {
    private static final Set<String> VALID_TABLE_NAMES = Set.of("Classrooms", "Culinary", "Shops", "Maintenance","Offices", "Laboratory");

    /**
     * Retrieves a list of rooms from table corresponding to {@code tableName}
     * <p>
     * Connects to the database using {@code DatabaseConnector.getConnection()},
     * executes a query to fetch all rows from the {@code tableName} table, and extracts the values
     * from the "room" column. The result is returned as an {@code ArrayList<String>}.
     * </p>
     *
     * @return an {@code ArrayList<String>}  containing the names of all culinary  rooms
     *         retrieved from the database. If no rooms are found or an error occurs,
     *         an empty list is returned. If table not in approved list of
     *         tables an empty list is returned.
     *
     */
    public static ArrayList<String> getRooms(String tableName) {
        if(!VALID_TABLE_NAMES.contains(tableName)){
            return new ArrayList<>();
        }
        String sql = "SELECT room FROM " + tableName;

        ArrayList<String> rooms = new ArrayList<>();
        try (var conn = DatabaseConnector.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            String columnLabel = "room";
            while(rs.next()){
                rooms.add(rs.getString(columnLabel));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving rooms: " + e.getMessage());
        }

        return rooms;
    }

    /**
     * Retrieves a HashMap of room:date pairs from table corresponding to {@code tableName}
     * <p>
     * Connects to the database using {@code DatabaseConnector.getConnection()},
     * executes a query to fetch all rows from the {@code tableName} table, and extracts the values
     * from the "room" and "date" columns.
     * </p>
     *
     * @return an {@code HashMap<String,String>} containing the names of all room with their entered date
     *         retrieved from the database. If no rooms are found or an error occurs,
     *         an empty map is returned. If table not in approved list of
     *         tables an empty map is returned.
     *
     */
    public static HashMap<String,String> getRoomDates(String tableName) {
        if(!VALID_TABLE_NAMES.contains(tableName)){
            return new HashMap<>();
        }
        String sql = "SELECT room, date FROM " + tableName;

        HashMap<String,String> roomDates = new HashMap<>();
        try (var conn = DatabaseConnector.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            String columnLabel1 = "room";
            String columnLabel2 = "date";

            while(rs.next()){
                roomDates.put(rs.getString(columnLabel1), rs.getString(columnLabel2));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving data: " + e.getMessage());
        }

        return roomDates;
    }





}