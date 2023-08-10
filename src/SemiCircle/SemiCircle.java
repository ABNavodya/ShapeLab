package src.SemiCircle;

import javax.swing.*;
public class SemiCircle {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new SemiCircleFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
