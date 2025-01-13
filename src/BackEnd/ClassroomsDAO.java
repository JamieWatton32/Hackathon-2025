package BackEnd;
import java.sql.SQLException;

public class ClassroomsDAO {
    // Inserts new row into Classrooms table
    public static void insertClassrooms(int id, String complete){
        String sql = "INSERT INTO classrooms(id, complete) VALUES(?, ?)";

        try(var conn = BackEnd.DatabaseConnector.getConnection()){
            var pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, complete);

            pstmt.executeUpdate();
            System.out.println("Inserted into classrooms.");
        }catch (SQLException e) {
            System.err.println("Error inserting into classroom table");
        }
    }
    public static void getClassrooms() {
        String sql = "SELECT * FROM Classrooms";

        try (var conn = DatabaseConnector.getConnection()) {
            if (conn == null) {
                System.err.println("Database connection is null or closed.");
                return;
            }

            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                "Room: " + rs.getString("room"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving classrooms: " + e.getMessage());
        }
    }

}