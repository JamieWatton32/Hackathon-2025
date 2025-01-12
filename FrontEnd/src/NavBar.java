import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class NavBar {
    private JPanel panel;
    private JButton outstandingButton;
    private JButton reportsButton;

    public NavBar(ProgramWindow window) {
        panel = new JPanel(new GridLayout(1, 2, 10, 10)); // 1 row, 2 columns, with horizontal and vertical gap between buttons
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 25, 5)); // Outer padding (top, left, bottom, right)
        panel.setBackground(Color.decode("#004477")); // Background color of the nav bar

        // Outstanding button (selected by default)
        outstandingButton = createNavButton("Outstanding", true);
        outstandingButton.addActionListener(e -> {
            window.showPage("Outstanding");
            updateButtonSelection(outstandingButton, reportsButton);
        });

        // Reports button (unselected initially)
        reportsButton = createNavButton("Reports", false);
        reportsButton.addActionListener(e -> {
            window.showPage("Reports");
            updateButtonSelection(reportsButton, outstandingButton);
        });

        panel.add(outstandingButton);
        panel.add(reportsButton);
    }

    private JButton createNavButton(String text, boolean isSelected) {
        JButton button = new JButton(text);
        button.setBackground(isSelected ? Color.decode("#2A6B9B") : Color.decode("#1B5C8C"));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding around text
        button.setFocusPainted(false); // Remove focus border
        button.setPreferredSize(new Dimension(0, 40)); // Allow buttons to expand to fill space

        // Add focus listener to change border when the button is focused
        button.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); // White focus border
            }

            @Override
            public void focusLost(FocusEvent e) {
                button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Reset to original border
            }
        });

        return button;
    }

    private void updateButtonSelection(JButton selected, JButton unselected) {
        selected.setBackground(Color.decode("#2A6B9B"));
        unselected.setBackground(Color.decode("#1B5C8C"));
    }

    public JPanel getPanel() {
        return panel;
    }
}
