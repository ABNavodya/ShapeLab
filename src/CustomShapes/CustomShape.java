package src.CustomShapes;

import javax.swing.*;
public class CustomShape {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new CustomShapeFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
