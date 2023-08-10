package src.RightAngledTriangle;

import javax.swing.*;

public class RightAngledTriangle {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new RightAngledTriangleFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        });
    }
}
