package FrontEnd;
import BackEnd.DataApi;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;

public class OutstandingPage {
    private JPanel panel;
    private String[][] roomList = RoomList.getRoomList2();

    private LocalDate currentDate;
    private int currentQuarter;

    private ArrayList<String> outstandingRooms = new ArrayList<>();


    public OutstandingPage() {
        // Set current date & quarter
        this.currentDate = LocalDate.now();
        this.currentQuarter = HelperFunctions.findQuarter(currentDate);

        // GUI
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically
        panel.setBackground(Color.decode("#004477"));

        HelperFunctions.findOutstanding(roomList, outstandingRooms, currentDate, currentQuarter); // Create a list of outstanding rooms

        // Create a report on screen for each outstanding room
        for (String roomNumber : outstandingRooms) {
            JPanel report = new Report(roomNumber).createOutstanding();
            panel.add(report);

            panel.add(Box.createVerticalStrut(10));  // Adjust the vertical gap as needed
        }
    }

    // Getters

    public JPanel getPanel() {
        return panel;
    }

    public int getTotal() {
        return roomList.length;
    }

    public int getCompleted() {
        return roomList.length - outstandingRooms.size();
    }

}
