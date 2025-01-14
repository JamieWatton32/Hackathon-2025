package FrontEnd;
import BackEnd.DataApi;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class OutstandingPage {
    private JPanel panel;
    private String[][] roomList = RoomList.getRoomList();

    private LocalDate currentDate;
    private int currentQuarter;

    private HashMap<String,String> rooms = new HashMap<>();
    private ArrayList<String> outstandingRooms = new ArrayList<>();


    public OutstandingPage() {
        // Set current date & quarter
        currentDate = LocalDate.now();
        currentQuarter = findQuarter(currentDate);

        // GUI
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically
        panel.setBackground(Color.decode("#004477"));

        findOutstanding(roomList); // Create a list of outstanding rooms

        // Create a report on screen for each outstanding room
        for (String roomNumber : outstandingRooms) {
            JPanel report = new Report(roomNumber).createOutstanding();
            panel.add(report);

            panel.add(Box.createVerticalStrut(10));  // Adjust the vertical gap as needed
        }

    }

    public void findOutstanding(String[][] roomList) {
        for (String[] room : roomList){
            boolean flag = false;

            switch (room[1]) {
                case "office" -> {
                    rooms = DataApi.getRoomDates("Offices");
                }
                case "classroom" -> {
                    rooms = DataApi.getRoomDates("Classrooms");
                    monthlyCheck(room[0], rooms, currentDate);
                }
                case "shop" -> {
                    rooms = DataApi.getRoomDates("Shops");
                }
                case "laboratory" -> {
                    rooms = DataApi.getRoomDates("Laboratory");
                }
                case "maintenance" -> {
                    rooms = DataApi.getRoomDates("Maintenance");
                }
                case "culinary" -> {
                    rooms = DataApi.getRoomDates("Culinary");
                }
                case null, default -> {
                    //placeholder for error handling
                    continue;
                }
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public int findQuarter(LocalDate currentDate) {
        int quarter = 0;
        int month = currentDate.getMonthValue();

        if (month < 4) {
            quarter = 1;
        } else if (month < 7) {
            quarter = 2;
        } else if (month < 10) {
            quarter = 3;
        } else {
            quarter = 4;
        }

        return quarter;
    }

    public int findQuarter(int month) {
        int quarter = 0;

        if (month < 4) {
            quarter = 1;
        } else if (month < 7) {
            quarter = 2;
        } else if (month < 10) {
            quarter = 3;
        } else {
            quarter = 4;
        }

        return quarter;
    }

    public void monthlyCheck (String roomNumber, HashMap<String,String> reports, LocalDate currentDate) {
        boolean flag = false;

        for (Map.Entry<String,String> report : reports.entrySet()) {
            String reportRoomNumber = report.getKey();
            int[] reportDate = Arrays.stream(report.getValue().replaceFirst("^(\\d{1,2})/(\\d{1,2})/(\\d{4}).*", "$1/$3").split("/"))
                    .mapToInt(Integer::parseInt)
                    .toArray();


            if ((Objects.equals(roomNumber, reportRoomNumber)) && // if room numbers match
                    (reportDate[0] == currentDate.getMonthValue()) && // & months match
                    (reportDate[1] == currentDate.getYear())) { // & years match
                flag = true;
                break;
            }
        }

        if (flag == false) {
            outstandingRooms.add(roomNumber);
        }
    }

    public void quarterlyCheck (String roomNumber, HashMap<String,String> reports, LocalDate currentDate, int currentQuarter){
        boolean flag = false;

        for (Map.Entry<String,String> report : reports.entrySet()) {
            String reportRoomNumber = report.getKey();
            int[] reportDate = Arrays.stream(report.getValue().replaceFirst("^(\\d{1,2})/(\\d{1,2})/(\\d{4}).*", "$1/$3").split("/"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int reportQuarter = findQuarter(reportDate[0]);

            if ((Objects.equals(roomNumber, reportRoomNumber)) && // if room numbers match
                    (reportQuarter == currentQuarter) && // & months match
                    (reportDate[1] == currentDate.getYear())) { // & years match
                flag = true;
                break;
            }
        }



        if (flag == false) {
            outstandingRooms.add(roomNumber);
        }
    }

}
