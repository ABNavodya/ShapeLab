package src.Scalene;

import javax.swing.*;

public class Scalene {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new ScaleneFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        });

    }
}
