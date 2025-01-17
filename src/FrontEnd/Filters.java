package FrontEnd;

import javax.swing.*;

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
}
