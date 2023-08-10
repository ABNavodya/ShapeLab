package src.Octagon;

import javax.swing.*;

public class Octagon {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new OctagonFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}