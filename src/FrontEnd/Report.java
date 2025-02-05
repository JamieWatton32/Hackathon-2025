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
        roomNumberLabel.setFont(new Font(roomNumberLabel.getFont().getName(), Font.BOLD, 16));
        roomNumberLabel.setForeground(Color.BLACK);
        roomNumberLabel.setBorder(BorderFactory.createEmptyBorder(2, 40, 3, 0));
        roomNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Right label (status)
        JLabel statusLabel = new JLabel(status);
        statusLabel.setFont(new Font(statusLabel.getFont().getName(), Font.BOLD, 16));
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(2, 0, 3, 40));
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Add labels to the bar
        bar.add(roomNumberLabel, BorderLayout.WEST);
        bar.add(statusLabel, BorderLayout.EAST);

        return bar;
    }
}
