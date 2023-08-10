package src.RightAngledTriangle;

import javax.swing.*;
import java.awt.*;

public class RightAngledTrianglePanel extends JPanel{
    // declare RightAngledTriangleController variable
    private RightAngledTriangleController rightAngledTriangleController;
    private RightAngledTrianglePanel rightAngledTrianglePanel;
    //set the default color to black
    private Color color = Color.BLACK;
    //create a parameterized constructor by accessing the current context
    public RightAngledTrianglePanel(RightAngledTriangleController rightAngledTriangleController){

        this.rightAngledTriangleController = rightAngledTriangleController;

    }
    //set color
    public void setColor(Color color) {
        this.color = color;
        repaint();
    }
    //get color
    public Color getColor() {

        return color;
    }
    //overridden class from Jpanel used  to handle custom drawing on the panel
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (rightAngledTriangleController.getShape() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(rightAngledTriangleController.getShape());
        }
    }
}
