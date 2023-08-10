package src.EquilateralTriangle;

import javax.swing.*;
import java.awt.*;
public class EquilateralTrianglePanel extends JPanel{

    private EquilateralTriangleController equilateralTriangleController;
    private Color color = Color.BLACK;

    public EquilateralTrianglePanel(EquilateralTriangleController equilateralTriangleController){
        this.equilateralTriangleController = equilateralTriangleController;
    }
    public void setColor(Color color) {
        this.color = color;
        repaint();
    }
    public Color getColor() {

        return color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (equilateralTriangleController.getShape() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(equilateralTriangleController.getShape());
        }
    }

}
