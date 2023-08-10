package src.Hexagon;

import javax.swing.*;
import java.awt.*;
public class HexagonPanel extends JPanel{
    private HexagonController hexagonController;
    private Color color = Color.BLACK;

    public HexagonPanel(HexagonController hexagonController){

        this.hexagonController = hexagonController;
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
        if (hexagonController.getShape() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(hexagonController.getShape());
        }
    }

}
