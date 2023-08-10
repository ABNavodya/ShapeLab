package src.Decagon;

import javax.swing.*;
public class Decagon {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new DecagonFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
