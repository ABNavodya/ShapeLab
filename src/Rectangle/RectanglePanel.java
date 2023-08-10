package src.Rectangle;

import javax.swing.*;
import java.awt.*;

public class RectanglePanel extends JPanel {
    // declare RectangleController variable
    private  RectangleController rectangleController;

    //set the default color to black
    private Color color = Color.BLACK;

    //create a parameterized constructor by accessing the current context
    public RectanglePanel(RectangleController rectangleController){
        this.rectangleController = rectangleController;
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
    @Override
    //overridden class from Jpanel used  to handle custom drawing on the panel
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (rectangleController.getShape() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(rectangleController.getShape());
        }
    }




}


