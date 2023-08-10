package src.Oval;

import javax.swing.*;
import java.awt.*;
public class OvalPanel extends JPanel {

    //declare private Oval controller variable
    private OvalController ovalController;

    //declare private Oval Panel variable
    private OvalPanel ovalPanel;

    //set the default colour in Oval shape colour as black
    private Color color = Color.BLACK;

    //create a constructor called Oval panel by accessing the current context
    public OvalPanel(OvalController ovalController){
        this.ovalController = ovalController;
    }

    //create a setter method for Oval shape
    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    //create a getter method for Oval shape
    public Color getColor() {

        return color;
    }

    //overridden class from JPanel used to handle the custom drawings on the panel
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (ovalController.getShape() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(ovalController.getShape());
        }
    }
}
