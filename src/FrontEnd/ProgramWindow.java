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

        // Add navbar
        NavBar navBar = new NavBar(this);
        add(navBar.getPanel(), BorderLayout.NORTH);

        // CardLayout container (in the center)
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Container and components for outstanding page
        JPanel outstandingContainer = new JPanel();
        outstandingContainer.setLayout(new BorderLayout());

        OutstandingPage outstandingPage = new OutstandingPage();

        JProgressBar outstandingProgress = new ProgressBar(outstandingPage.getCompleted(), outstandingPage.getTotal()).getProgressBar();
        outstandingContainer.add(outstandingProgress, BorderLayout.NORTH);

        JScrollPane outstandingScrollPane = new JScrollPane(outstandingPage.getPanel());
        outstandingScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        outstandingContainer.add(outstandingScrollPane);

        cardPanel.add(outstandingContainer, "Outstanding");

        // Container and components for reports page
        JPanel reportsContainer = new JPanel();
        reportsContainer.setLayout(new BorderLayout());

        JPanel reportsComponentContainer = new JPanel();
        reportsComponentContainer.setLayout(new BorderLayout());

        ReportsPage reportsPage = new ReportsPage();

        // Get a single value for YTD progress
        int progress = 0;

        for (int i : reportsPage.getProgressList()){
            progress += i;
        }

        progress /= reportsPage.getProgressList().size();

        ProgressBar reportsProgress = new ProgressBar(progress);
        reportsComponentContainer.add(reportsProgress.getProgressBar(), BorderLayout.SOUTH);

        reportsComponentContainer.add(new Filters.ReportFilters().getPanel(), BorderLayout.NORTH);

        JScrollPane reportsScrollPane = new JScrollPane(reportsPage.getPanel());
        reportsScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        reportsContainer.add(reportsScrollPane, BorderLayout.CENTER);

        reportsContainer.add(reportsComponentContainer, BorderLayout.NORTH);
        cardPanel.add(reportsContainer, "Reports");

        add(cardPanel, BorderLayout.CENTER);

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
