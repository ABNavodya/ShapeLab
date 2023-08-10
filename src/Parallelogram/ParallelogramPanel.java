package src.Parallelogram;

import javax.swing.*;
import java.awt.*;
public class ParallelogramPanel extends JPanel{
    // reference to ParallelogramController object to communicate with its model
    private ParallelogramController paralleogramController;
    private ParallelogramPanel parallelogramPanel;

    // current color of the parallelogram
    private Color color = Color.BLACK;

    // constructor to initialize the panel with its controller
    public ParallelogramPanel(ParallelogramController paralleogramController){
        this.paralleogramController = paralleogramController;
    }
    // method to set the color of the parallelogram
    public void setColor(Color color) {
        this.color = color;
        // repaint the panel to update the color of the parallelogram
        repaint();
    }

    // method to get the current color of the parallelogram
    public Color getColor() {

        return color;
    }

    // overridden method to paint the parallelogram on the panel
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (paralleogramController.getShape() != null) {
            // setting the rendering hints for better quality graphics
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            // filling the parallelogram with the current color
            g2.fill(paralleogramController.getShape());
        }
    }
}
