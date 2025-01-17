package FrontEnd;

import javax.swing.*;
import java.awt.*;

public class Filters {

    public static class OutstandingFilters {
        JPanel filterContainer;

        public OutstandingFilters() {
            filterContainer = new JPanel();

            // Monthly View Selector
            JComboBox<String> monthDropDown = new JComboBox<>(new String[]{
                    "Year to Date", "September", "October", "November", "December", "January",
                    "February", "March", "April", "May", "June", "July", "August"
            });
            filterContainer.add(monthDropDown);
        }

        public JPanel getPanel() {
            return filterContainer;
        }

    }

    public static class ReportFilters {
        JPanel filterContainer;

        public ReportFilters() {
            filterContainer = new JPanel();
            filterContainer.setLayout(new FlowLayout());
            filterContainer.setBackground(Color.decode("#004477"));

            // Monthly View Selector
            JComboBox<String> monthDropDown = new JComboBox<>(new String[]{
                    "Year to Date", "September", "October", "November", "December", "January",
                    "February", "March", "April", "May", "June", "July", "August"
            });
            filterContainer.add(monthDropDown);

            JButton button = new JButton("Generate Report");
            button.setBackground(Color.decode("#1B5C8C"));
            button.setFont(new Font(button.getFont().getName(), Font.BOLD, 16));
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding around text
            button.setFocusPainted(false); // Remove focus border
            button.setPreferredSize(new Dimension(220, 40));
            button.setVisible(true);

            filterContainer.add(button);
        }

        public JPanel getPanel() {
            return filterContainer;
        }

    }
}
