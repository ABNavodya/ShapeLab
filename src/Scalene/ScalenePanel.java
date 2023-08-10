package src.Scalene;

import javax.swing.*;
import java.awt.*;

public class ScalenePanel extends JPanel {

    //Declare scaleneController variable
    private ScaleneController scaleneController;

    //Declare the default as color
    private Color color = Color.BLACK;

    //Declare parameterized constructor by accessing the current context
    public ScalenePanel(ScaleneController scaleneController){

        this.scaleneController = scaleneController;
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
        if (scaleneController.getShape() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(scaleneController.getShape());
        }
    }

}
