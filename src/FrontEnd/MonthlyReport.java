package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MonthlyReport {
    private String month;
    private int completed;
    private int total;

    public MonthlyReport(String monthName, ArrayList<String[]> roomList, ArrayList<String> outstandingRooms) {
        this.month = monthName;
        this.completed = roomList.size()-outstandingRooms.size();
        this.total = roomList.size();
    }

    // Method to return a JPanel for displaying the report
    public JPanel createMonthlyReport() {
        JPanel bar = new JPanel();
        bar.setLayout(new BorderLayout());
        bar.setBackground(Color.decode("#EDF5FA")); // Bar color

        // Set preferred size for the bar
        bar.setMaximumSize(new Dimension(800, 40)); // Restrict the height

        // Left label (month)
        JLabel roomNumberLabel = new JLabel(month);
        roomNumberLabel.setFont(new Font(roomNumberLabel.getFont().getName(), Font.BOLD, 16));
        roomNumberLabel.setForeground(Color.BLACK);
        roomNumberLabel.setBorder(BorderFactory.createEmptyBorder(2, 40, 3, 0));
        roomNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Right label (progress)
        ProgressBar progressBar = new ProgressBar(completed,total);

        // Add labels to the bar
        bar.add(roomNumberLabel, BorderLayout.WEST);
        bar.add(progressBar.getProgressBar(), BorderLayout.EAST);

        return bar;
    }
}
