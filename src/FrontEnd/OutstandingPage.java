package FrontEnd;
import javax.swing.*;
import java.awt.*;

public class OutstandingPage {
    private JPanel panel;
    private String[] outstandingRooms = {"D127", "D227"};

    public OutstandingPage() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically
        panel.setBackground(Color.decode("#004477"));

        for (String roomNumber : outstandingRooms) {
            JPanel report = new Report(roomNumber).createOutstanding();
            panel.add(report);

            panel.add(Box.createVerticalStrut(10));  // Adjust the vertical gap as needed
        }

    }

    public JPanel getPanel() {
        return panel;
    }

}
