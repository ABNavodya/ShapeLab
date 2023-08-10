package src.CustomShapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class CustomShapeCanvas extends JPanel{

    //Declare a private variable for tool which is existTool
    private Tool existTool;
    //Declare a private variable for color which is existColor
    private Color existColor;
    //Declare a private variable called drawingPoints Store the point for drawing on the canvas which are x and y co-ordinate values
    private List<Point> drawingPoints;

    //Declare a private variable called pointPaths this contains all the points and paths of drawings temporary holds the x and y coordinate values
    private List<List<Point>> pointPaths;

    //create a constructor
    public CustomShapeCanvas(){
        //set existTool to pencil
        existTool = Tool.PENCIL;
        //set exist or the current color to the black
        existColor = Color.BLACK;

        pointPaths = new ArrayList<>();

        setPreferredSize(new Dimension(1000, 700));
        setBackground(Color.WHITE);

        // Add mouse listeners to handle drawing
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                startDrawing(e.getX(), e.getY());
            }

            public void mouseReleased(MouseEvent e) {

                stopDrawing(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                continueDrawing(e.getX(), e.getY());
            }
        });
    }
    public void setCurrentTool(Tool tool) {

        existTool = tool;
    }

    public void setCurrentColor(Color color) {

        existColor = color;
    }

    //create a new list of points and it added to the initial point
    private void startDrawing(int x, int y) {
        drawingPoints = new ArrayList<>();
        drawingPoints.add(new Point(x, y));
    }
    //create the final point and stores the completed path to the pointPaths
    private void stopDrawing(int x, int y) {
        drawingPoints.add(new Point(x, y));
        // Add the completed path to the list of paths
        pointPaths.add(drawingPoints);
        drawingPoints = null;
    }
    //creates point to the current path as the mouse is dragged over
    private void continueDrawing(int x, int y) {
        if (drawingPoints != null) {
            drawingPoints.add(new Point(x, y));
            repaint(); // Request a repaint to update the drawing
        }
    }

    //It draws all the completed paths and the paths that the user is currently drawing.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw all paths
        for (List<Point> path : pointPaths){
            if (drawingPoints != null) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(existColor);
                g2d.setStroke(new BasicStroke(4));// Set the stroke width
                // Draw all paths
                for (int i = 1; i < drawingPoints.size(); i++) {
                    Point p1 = drawingPoints.get(i - 1);
                    Point p2 = drawingPoints.get(i);
                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
            // If currently drawing, also draw the current points
            if (drawingPoints != null) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(existColor);
                g2d.setStroke(new BasicStroke(4));// Set the stroke width
                for (int i = 1; i < drawingPoints.size(); i++) {
                    Point p1 = drawingPoints.get(i - 1);
                    Point p2 = drawingPoints.get(i);
                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }
    //this holds a contstnts
    public enum Tool {
        PENCIL
    }
}
