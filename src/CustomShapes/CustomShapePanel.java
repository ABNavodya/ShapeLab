package src.CustomShapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomShapePanel extends JPanel {
    //declare private Point Variable called startPoint as the initial point when the mouse dragged
    private Point startPoint;

    //declare private Point Variable called endPoint as the final point when the mouse dragged
    private Point endPoint;

    //create a constructor
    public CustomShapePanel(){

        //set the size
        setPreferredSize(new Dimension(1000, 700));
        //set background color to white
        setBackground(Color.WHITE);

        //set mouse listener to the CustomShapePanel
        addMouseListener(new MouseAdapter() {
            //records the mouse is pressed  at the start point
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }
            //records the mouse is released  at the end point
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
            }
        });

    }
}
