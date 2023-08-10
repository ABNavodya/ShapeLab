package src.Oval;
import javax.swing.*;
public class Oval {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new OvalFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        });
    }
}
