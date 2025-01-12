package classes;

import java.sql.SQLException;

public class ClassroomsDAO {
    // Inserts new row into Classrooms table
    public void insertClassrooms(int id, String name, double capacity){
        String sql = "INSERT INTO classrooms(id, name, capacity) VALUES(?, ?, ?)";

        try(var conn = DatabaseConnection.getConnection()){
            var pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setDouble(3, capacity);
            pstmt.executeUpdate();
            System.out.println("Inserted into classrooms.");
        }catch (SQLException e) {
            System.err.println("Error inserting into classroom table");
        }
    }

}
