package src.Heptagon;

import javax.swing.*;
import java.awt.*;

public class HeptagonPanel extends JPanel{
    private HeptagonController heptagonController;

    private Color color = Color.BLACK;

    public HeptagonPanel(HeptagonController heptagonController){

        this.heptagonController = heptagonController;
    }
    public void setColor(Color color) {
        this.color = color;
        repaint();
    }
    public Color getColor() {

        return color;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (heptagonController.getShape() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(heptagonController.getShape());
        }
    }


}
