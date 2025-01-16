package FrontEnd;
import javax.swing.*;
import java.awt.*;

public class Report {
    private String roomNumber;
    private String status = "Outstanding";

    public Report(String roomNumber){
        this.roomNumber = roomNumber;
    }

    // Method to return a JPanel for displaying the report
    public JPanel createOutstanding() {
        JPanel bar = new JPanel();
        bar.setLayout(new BorderLayout());
        bar.setBackground(Color.decode("#EDF5FA")); // Bar color

        // Set preferred size for the bar
        bar.setMaximumSize(new Dimension(800, 40)); // Restrict the height

        // Left label (room number)
        JLabel roomNumberLabel = new JLabel(roomNumber);
        roomNumberLabel.setForeground(Color.BLACK);
        roomNumberLabel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        roomNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Right label (status)
        JLabel statusLabel = new JLabel(status);
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Add labels to the bar
        bar.add(roomNumberLabel, BorderLayout.WEST);
        bar.add(statusLabel, BorderLayout.EAST);

        return bar;
    }
}
