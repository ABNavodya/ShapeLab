package src.Pentagon;
import javax.swing.*;

public class Pentagon {
    public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> {
        JFrame frame = new PentagonFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      });
    }
}

