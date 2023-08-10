package src.Nonagon;

import javax.swing.*;
public class Nonagon {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new NonagonFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
