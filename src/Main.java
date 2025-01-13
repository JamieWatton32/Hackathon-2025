import javax.swing.*;

import BackEnd.DataApi;
import BackEnd.DatabaseConnector;
import FrontEnd.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try(var conn = DatabaseConnector.getConnection()){

            System.out.println(DataApi.getClassrooms());
            DatabaseConnector.closeConnection();
        }catch(SQLException e){
            System.err.println("Error: " + e);
        }
        JFrame ProgramWindow = new ProgramWindow();
    }
}