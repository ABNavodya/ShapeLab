package src.Nonagon;

import javax.swing.*;
import java.awt.*;
public class NonagonPanel extends  JPanel{

    //declare private nonagonController variable
    private NonagonController nonagonController;

    //declare private NonagonPanel variable
    private NonagonPanel nonagonPanel;

    //set the default colour in Nonagon shape colour as black

    private Color color = Color.BLACK;

    //create a constructor called Oval panel by accessing the current context

    public NonagonPanel(NonagonController nonagonController){

        this.nonagonController = nonagonController;
    }

    //create a setter method for nonagon shape

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }
    //create a getter method for nonagon shape
    public Color getColor() {

        return color;
    }

    //overridden class from JPanel used to handle the custom drawings on the panel
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (nonagonController.getShape() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(nonagonController.getShape());
        }
    }
}
