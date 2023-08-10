package src.Rhombus;

import javax.swing.*;

public class Rhombus {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new RhombusFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    }

}
