package FrontEnd;

import javax.swing.*;

public class ProgressBar {
    JProgressBar progressBar = new JProgressBar(0,100);

    public ProgressBar(int completedReports, int totalReports){
        progressBar.setValue((int)((double) completedReports / totalReports * 100));
        progressBar.setStringPainted(true);
        progressBar.setVisible(true);
    }

    public JProgressBar getProgressBar(){
        return progressBar;
    }
}
