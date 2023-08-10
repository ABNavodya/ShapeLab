package src.Square;

import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel {


    //Declare squareController variable
    private SquareController squareController;

    //Declare the default as color
    private Color color = Color.BLACK;

    //Declare parameterized constructor by accessing the current context
    public SquarePanel(SquareController squareController){

        this.squareController = squareController;

    }

    // set the color
    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    //get the color
    public Color getColor() {

        return color;
    }

    //overridden class from JPanel used to handle the custom drawings on the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (squareController.getShape() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(squareController.getShape());
        }
    }


}
