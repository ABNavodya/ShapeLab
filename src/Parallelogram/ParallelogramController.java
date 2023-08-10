package src.Parallelogram;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class ParallelogramController {
    private Shape shape; //Declared private variable named "shape"

    public void setShape(Shape shape){ //Set the shape

        this.shape = shape; //Assign the parameter "shape" to the private variable "shape"
    }

    public Shape getShape() { // Method to return the value of "shape"

        return shape; //Return the value
    }
    public void createShape() { // Method to return the value of "shape"
        int[] xPoints = {50, 150, 200, 100}; //X-coordinates of the parallelogram vertices
        int[] yPoints = {50, 50, 150, 150}; //Y-coordinates of the parallelogram vertices
        shape = new Polygon(xPoints, yPoints, 4); //Create the Polygon shape with the specified vertices

    }
    public void deleteShape() { //Methode creation for delete operation

        shape = null; //Polygon shape with the specified vertices
    }

    public void scaleShape(double scale) { //Methode creation for scale the current shape by a specified factor
        if (shape != null) { //Check if the shape is not null
            AffineTransform at = new AffineTransform(); //New AffineTransform object creation
            at.scale(scale, scale); //Scale the AffineTransform by the specified factor
            shape = at.createTransformedShape(shape); //Apply the AffineTransform to the shape

        }
    }

    public Shape copyShape() { //Method to create a copy of the current shape
        if (shape != null) { //Check if the shape is not null
            AffineTransform identity = new AffineTransform(); // Create a new AffineTransform object
            return identity.createTransformedShape(shape); // Apply the AffineTransform to the shape and return the resulting shape
        }
        return null; //Return null
    }

    public void rotateShape(double angle) { // Method to rotate the current shape by a specified angle
        if (shape != null) { //Check if the shape is not null
            Rectangle bounds = shape.getBounds(); //Get the bounding rectangle of the shape
            AffineTransform at = new AffineTransform();//Create a new AffineTransform object
            at.rotate(Math.toRadians(angle), bounds.getCenterX(), bounds.getCenterY()); // Rotate the AffineTransform by the specified angle around the center of the shape
            shape = at.createTransformedShape(shape); // Apply the AffineTransform to the shape
        }
    }

    public void translateShape(int dx, int dy) { // Method to translate the current shape by a specified distance
        if (shape != null) { //Check if the shape is not null
            AffineTransform at = new AffineTransform(); //Create a new AffineTransform object
            at.translate(dx, dy); //Translate the AffineTransform by the specified distance
            shape = at.createTransformedShape(shape); //Apply the AffineTransform
        }
    }

    public void resizeShape(int width, int height) { //Method to resize the current shape to a specified width and height
        if (getShape() != null) { //Check if the shape is not null
            Rectangle bounds = getShape().getBounds(); //Get the bounding rectangle of the shape
            double scaleX = width / (double)bounds.width; //Calculate the ratio of the desired width to the current width of the shape
            double scaleY = height / (double)bounds.height; //Calculate the ratio of the desired height to the current height of the shape

            AffineTransform at = new AffineTransform(); //Create a new AffineTransform object
            at.scale(scaleX, scaleY); //Scale the transform by the calculated ratios
            setShape(at.createTransformedShape(getShape())); //Apply the transform to the shape and set it as the new shape
        }
    }
}
