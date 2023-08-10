package src.Square;

import javax.swing.*;
public class Square {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new SquareFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    }
}
