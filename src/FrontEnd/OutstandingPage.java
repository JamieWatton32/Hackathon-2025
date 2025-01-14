package FrontEnd;
import BackEnd.DataApi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class OutstandingPage {
    private JPanel panel;
    private String[][] roomList = RoomList.getRoomList();

    private ArrayList<String> rooms = new ArrayList<>();
    private ArrayList<String> outstandingRooms = new ArrayList<>();


    public OutstandingPage() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically
        panel.setBackground(Color.decode("#004477"));

        findOutstanding(roomList);

        for (String roomNumber : outstandingRooms) {
            JPanel report = new Report(roomNumber).createOutstanding();
            panel.add(report);

            panel.add(Box.createVerticalStrut(10));  // Adjust the vertical gap as needed
        }

    }

    public void findOutstanding(String[][] roomList){
        for (String[] i : roomList){
            boolean flag = false;

            if (Objects.equals(i[1], "office")) {
                continue;
            } else if (Objects.equals(i[1], "classroom")) {
                rooms = DataApi.getClassrooms();

                for (String room : rooms) {

                    if (Objects.equals(i[0], room)){
                        flag = true;
                        break;
                    }
                }

                if (flag == false) {
                    outstandingRooms.add(i[0]);
                }
            } else if (Objects.equals(i[1], "shop")) {
                continue;
            } else if (Objects.equals(i[1], "laboratory")) {
                continue;
            } else if (Objects.equals(i[1], "maintenance")) {
                continue;
            } else if (Objects.equals(i[1], "culinary")) {
                continue;
            } else {
                //placeholder for error handling
                continue;
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }

}
