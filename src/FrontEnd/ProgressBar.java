package FrontEnd;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class ProgressBar {
    JProgressBar progressBar = new JProgressBar(0,100);

    public ProgressBar(int completedReports, int totalReports){
        progressBar.setValue((int)((double) completedReports / totalReports * 100));
        progressBar.setStringPainted(true);
        progressBar.setVisible(true);

        // Customize progress bar UI for dynamic colors
        progressBar.setUI(new BasicProgressBarUI() {
            @Override
            protected Color getSelectionBackground() {
                return Color.BLACK; // Text selection background
            }

            @Override
            protected Color getSelectionForeground() {
                return Color.WHITE; // Text selection foreground
            }

            @Override
            public void paintDeterminate(Graphics g, JComponent c) {
                int value = progressBar.getValue();
                if (value <= 80) {
                    progressBar.setForeground(Color.RED);
                } else if (value <= 99) {
                    progressBar.setForeground(Color.YELLOW);
                } else {
                    progressBar.setForeground(Color.GREEN);
                }
                super.paintDeterminate(g, c);
            }
        });
    }

    public ProgressBar(int value){
        progressBar.setValue(value);
        progressBar.setStringPainted(true);
        progressBar.setVisible(true);

        // Customize progress bar UI for dynamic colors
        progressBar.setUI(new BasicProgressBarUI() {
            @Override
            protected Color getSelectionBackground() {
                return Color.BLACK; // Text selection background
            }

            @Override
            protected Color getSelectionForeground() {
                return Color.WHITE; // Text selection foreground
            }

            @Override
            public void paintDeterminate(Graphics g, JComponent c) {
                int value = progressBar.getValue();
                if (value <= 80) {
                    progressBar.setForeground(Color.RED);
                } else if (value <= 99) {
                    progressBar.setForeground(Color.YELLOW);
                } else {
                    progressBar.setForeground(Color.GREEN);
                }
                super.paintDeterminate(g, c);
            }
        });
    }

    public JProgressBar getProgressBar(){
        return progressBar;
    }
}
