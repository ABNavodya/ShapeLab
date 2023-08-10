package src.Octagon;

import javax.swing.*;
import java.awt.*;

public class OctagonPanel extends JPanel {

    private OcatgonController ocatgonController;
    private OctagonPanel octagonPanel;

    private Color color = Color.BLACK;

    public OctagonPanel(OcatgonController ocatgonController){
        this.ocatgonController = ocatgonController;

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
        if (ocatgonController.getShape() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(ocatgonController.getShape());
        }
    }

}
