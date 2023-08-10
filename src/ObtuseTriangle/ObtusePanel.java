package src.ObtuseTriangle;

import javax.swing.*;
import java.awt.*;

public class ObtusePanel extends JPanel{

    private ObtuseTriangleController obtuseTriangleController;
    private ObtusePanel obtusePanel;

    // Initialize default color as black
    private Color color = Color.BLACK;

    // Constructor to set the ObtuseTriangleController
    public ObtusePanel(ObtuseTriangleController obtuseTriangleController){
        this.obtuseTriangleController = obtuseTriangleController;
    }

    // Set the color of the panel
    public void setColor(Color color) {
        this.color = color;
        repaint();
    }
    // Get the current color of the panel
    public Color getColor() {

        return color;
    }

    // Override the paintComponent method to draw the shape on the panel
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Check if the shape exists and then draw it
        if(obtuseTriangleController.getShape() != null){
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(obtuseTriangleController.getShape());
        }
    }
}
