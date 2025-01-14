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

        JScrollPane outstandingScrollPane = new JScrollPane(new OutstandingPage().getPanel());
        cardPanel.add(outstandingScrollPane, "Outstanding");

        JScrollPane reportsScrollPane = new JScrollPane(new ReportsPage().getPanel());
        cardPanel.add(reportsScrollPane, "Reports");

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
