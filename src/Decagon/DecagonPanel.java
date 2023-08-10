package src.Decagon;

import javax.swing.*;
import java.awt.*;

public class DecagonPanel extends JPanel{

    //declare DecagonController variable
    private DecagonController decagonController;

    //set the default decagon color as black
    private Color color = Color.BLACK;

    //Declare parameterized constructor called DecagonPanel by accessing the current context
    public DecagonPanel(DecagonController decagonController){
        this.decagonController = decagonController;
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (decagonController.getShape() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(decagonController.getShape());
        }
    }
}
