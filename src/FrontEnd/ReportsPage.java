package FrontEnd;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReportsPage {
    private JPanel panel;
    private String[][] roomList = RoomList.getRoomList2();
    private LocalDate currentDate = LocalDate.now();

    private ArrayList<String> outstandingRooms;
    private ArrayList<String[]> roomList2 = new ArrayList<>();
    private ArrayList<Integer> progressList = new ArrayList<>();

    public ReportsPage() {
        // Find current academic year
        int[] currentYear = HelperFunctions.findAcademicYear(currentDate);

        // GUI
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically
        panel.setBackground(Color.decode("#004477"));

        Object[][] monthList = {
                {"September", 9, currentYear[0]},
                {"October", 10, currentYear[0]},
                {"November", 11, currentYear[0]},
                {"December", 12, currentYear[0]},
                {"January", 1, currentYear[1]},
                {"February", 2, currentYear[1]},
                {"March", 3, currentYear[1]},
                {"April", 4, currentYear[1]},
                {"May", 5, currentYear[1]},
                {"June", 6, currentYear[1]},
                {"July", 7, currentYear[1]},
                {"August", 8, currentYear[1]}
        };

        for (Object[] month : monthList){
            if ((int) month[2] == currentDate.getYear() &&
                (int) month[1] > currentDate.getMonthValue()){
                break;
            }

            roomList2 = new ArrayList<>();
            outstandingRooms = new ArrayList<>();

            if (((int) month[1]) % 3 != 0) {
                for (String[] room : roomList) {
                    switch (room[1].toUpperCase()){
                        case "OFFICE":
                        case "CLASSROOM":
                        case "MAINTENANCE":
                            break;

                        case "SHOP":
                        case "LABORATORY":
                        case "CULINARY":
                            roomList2.add(room);
                            break;
                    }
                }
            } else {
                for (String[] room : roomList) {
                    roomList2.add(room);
                }
            }

            HelperFunctions.findOutstanding(roomList2, outstandingRooms, (int) month[1], (int) month[2]); // Create a list of outstanding rooms

            // Create a report for the month
            MonthlyReport monthlyReport = new MonthlyReport(month[0].toString(),roomList2,outstandingRooms);
            JPanel monthlyReportPanel = monthlyReport.createMonthlyReport();
            panel.add(monthlyReportPanel);

            progressList.add(monthlyReport.getProgress());

            panel.add(Box.createVerticalStrut(10));  // Adjust the vertical gap as needed

        }
    }

    public ArrayList<Integer> getProgressList() {
        return progressList;
    }

    public JPanel getPanel() {
        return panel;
    }
}
