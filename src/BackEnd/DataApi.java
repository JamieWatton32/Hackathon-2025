package BackEnd;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

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
        String sql = "SELECT Location_of_inspection FROM " + tableName;

        ArrayList<String> rooms = new ArrayList<>();
        try (var conn = DatabaseConnector.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            ResultSetMetaData meta = rs.getMetaData();
            ArrayList<String> columnList = new ArrayList<>();
            int colNum = 1;
            while(rs.next()){
                columnList.add(meta.getColumnName(colNum));
                colNum++;

            }
            for(var test: columnList){
                System.out.println(test);
            }
            while(rs.next()){
                rooms.add(rs.getString("Location_of_inspection"));
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
        String sql = "SELECT Location_of_inspection, Date_of_inspection FROM " + tableName;

        HashMap<String,String> roomDates = new HashMap<>();
        try (var conn = DatabaseConnector.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            String columnLabel1 = "Location_of_inspection";
            String columnLabel2 = "Date_of_inspection";
            while(rs.next()){
                roomDates.put(rs.getString(columnLabel1), rs.getString(columnLabel2));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving data: " + e.getMessage());
        }

        return roomDates;
    }
    /**
     * Retrieves an Array of Maps of column:value pairs from table corresponding to {@code tableName}
     * <p>
     * Connects to the database using {@code DatabaseConnector.getConnection()},
     * executes a query to fetch all rows from the {@code tableName} table, and maps the questions
     * too their respective response
     * </p>
     * Usage example:
     * <PRE>
     *for (var map: DataApi.getAllResponses("Shops")){
     *    for(String question: map.keySet()){
     *        String response = map.get(question);
     *             ...
     *        }
     * }
     * </PRE>
     * @return an {@code ArrayList<LinkedHashMap<String,String>>} all column the response
     * and their corresponding column question.
     */
    public static ArrayList<LinkedHashMap<String,String>> getAllResponses(String tableName) {
        if(!VALID_TABLE_NAMES.contains(tableName)){
            return new ArrayList<>();
        }
        String sql = "SELECT * FROM " + tableName;

        ArrayList<LinkedHashMap<String, String>> responses = new ArrayList<>();
        try (var conn = DatabaseConnector.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            while (rs.next()) {
                LinkedHashMap<String, String> row = new LinkedHashMap<>();

                for (int colNum = 1; colNum <= columnCount; colNum++) {
                    String columnName = meta.getColumnName(colNum).replaceAll("_", " ");
                    String columnValue = rs.getString(colNum).replaceAll("[\\[\\]\"]", "");
                    row.put(columnName, columnValue);
                }

                responses.add(row);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving data: " + e.getMessage());
        }

        return responses;
    }
    /**
     * Retrieves an Array of Maps of column:value pairs from table corresponding to {@code tableName} that match {@code roomNumber}
     * <p>
     * Connects to the database using {@code DatabaseConnector.getConnection()},
     * executes a query to fetch all rows from the {@code tableName} table, and maps the questions
     * too their respective response for all rows with a room of {@code roomNumber}
     * </p>
     * Usage example:
     * <PRE>
     *for (var map: DataApi.getRoomResponses("Shops")){
     *    for(String question: map.keySet()){
     *        String response = map.get(question);
     *             ...
     *        }
     * }
     * </PRE>
     * @return an {@code ArrayList<LinkedHashMap<String,String>>} all column the response
     * and their corresponding column question for any row with a room number of {@code roomNumber}
     *
     */
    public static ArrayList<LinkedHashMap<String,String>> getRoomResponse(String tableName, String roomNumber) {
        if(!VALID_TABLE_NAMES.contains(tableName)){
            return new ArrayList<>();
        }
        String sql = "SELECT * FROM " + tableName + " WHERE Location_of_inspection = '" + roomNumber + "';";

        ArrayList<LinkedHashMap<String, String>> responses = new ArrayList<>();
        try (var conn = DatabaseConnector.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            while (rs.next()) {
                LinkedHashMap<String, String> row = new LinkedHashMap<>();

                for (int colNum = 1; colNum <= columnCount; colNum++) {
                    String columnName = meta.getColumnName(colNum).replaceAll("_", " ");
                    String columnValue = rs.getString(colNum).replaceAll("[\\[\\]\"]", "");
                    row.put(columnName, columnValue);
                }

                responses.add(row);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving data: " + e.getMessage());
        }

        return responses;
    }
    /**
     * Retrieves an Array of Maps of column:value pairs from table corresponding to {@code tableName} that match {@code date}
     * <p>
     * Connects to the database using {@code DatabaseConnector.getConnection()},
     * executes a query to fetch all rows from the {@code tableName} table, and maps the questions
     * too their respective response for all rows with a date of {@code date}. Date must match exactly for time being
     * </p>
     * Usage example:
     * <PRE>
     *for (var map: DataApi.getDateResponses("Shops")){
     *    for(String question: map.keySet()){
     *        String response = map.get(question);
     *             ...
     *        }
     * }
     * </PRE>
     * @return an {@code ArrayList<LinkedHashMap<String,String>>} all column the response
     * and their corresponding column question for any row with a room number of {@code date}
     *
     */
    public static ArrayList<LinkedHashMap<String,String>> getDateResponse(String tableName, String date) {
        if(!VALID_TABLE_NAMES.contains(tableName)){
            return new ArrayList<>();
        }
        String sql = "SELECT * FROM " + tableName + " WHERE Date_of_inspection = '" + date + "';";

        ArrayList<LinkedHashMap<String, String>> responses = new ArrayList<>();
        try (var conn = DatabaseConnector.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            while (rs.next()) {
                LinkedHashMap<String, String> row = new LinkedHashMap<>();

                for (int colNum = 1; colNum <= columnCount; colNum++) {
                    String columnName = meta.getColumnName(colNum).replaceAll("_", " ");
                    String columnValue = rs.getString(colNum);
                    row.put(columnName, columnValue);
                }

                responses.add(row);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving data: " + e.getMessage());
        }

        return responses;
    }




}