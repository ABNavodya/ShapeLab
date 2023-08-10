package src.Heptagon;

import javax.swing.*;

public class Heptagon {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new HeptagonFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
