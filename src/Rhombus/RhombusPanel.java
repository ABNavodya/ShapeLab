package src.Rhombus;

import javax.swing.*;
import java.awt.*;

public class RhombusPanel extends JPanel {

    private RhombusController rhombusController;
    private RhombusPanel rhombusPanel;

    private Color color = Color.BLACK;

    public RhombusPanel(RhombusController rhombusController){
        this.rhombusController = rhombusController;

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
        if (rhombusController.getShape() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(rhombusController.getShape());
        }
    }

}
