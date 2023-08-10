package src.IsocelesTriangle;
import javax.swing.*;
import java.awt.*;

public class IsocelesTrianglePanel extends JPanel {

    //declare private isocelesTriangleController variable
    private IsocelesTriangleController isocelesTriangleController;

    //set the default colour in isocelesTriangleController shape colour as black
    private Color color = Color.BLACK;

    //create a constructor called IsocelesTrianglePanel by accessing the current context

    public IsocelesTrianglePanel(IsocelesTriangleController isocelesTriangleController){
        this.isocelesTriangleController =  isocelesTriangleController;
    }

    //create a setter method for IsocelesTriangle shape

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    //create a getter method for IsocelesTriangle shape
    public Color getColor() {

        return color;
    }

    //overridden class from JPanel used to handle the custom drawings on the panel

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (isocelesTriangleController.getShape() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(isocelesTriangleController.getShape());
        }
    }
}
