package FrontEnd;

import javax.swing.*;
import java.awt.*;

public class ProgramWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public ProgramWindow() {
        setTitle("Safe 6S");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // CardLayout container (in the center)
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        OutstandingPage outstandingPage = new OutstandingPage();
        JScrollPane outstandingScrollPane = new JScrollPane(outstandingPage.getPanel());
        outstandingScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        cardPanel.add(outstandingScrollPane, "Outstanding");

        JScrollPane reportsScrollPane = new JScrollPane(new ReportsPage().getPanel());
        reportsScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        cardPanel.add(reportsScrollPane, "Reports");

        add(cardPanel, BorderLayout.CENTER);

        // Container for nav bar + filters
        JPanel navContainer = new JPanel();
        navContainer.setLayout(new BoxLayout(navContainer, BoxLayout.Y_AXIS));

        // Add navbar
        NavBar navBar = new NavBar(this);
        navContainer.add(navBar.getPanel());

        // Add area for filter functionality
        JPanel filterContainer = new JPanel();
        filterContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        filterContainer.setPreferredSize(new Dimension(800, 40));
        filterContainer.setBackground(Color.red);

        Filters.OutstandingFilters outstandingFilters = new Filters.OutstandingFilters();
        filterContainer.add(outstandingFilters.getPanel(), "Outstanding Filters");

        ProgressBar progressBar = new ProgressBar(outstandingPage.getCompleted(), outstandingPage.getTotal());
        filterContainer.add(progressBar.getProgressBar(), "Outstanding Progress");

        navContainer.add(filterContainer);

        // Add container
        add(navContainer, BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void showPage(String pageName) {
        cardLayout.show(cardPanel, pageName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProgramWindow::new);
    }
}
