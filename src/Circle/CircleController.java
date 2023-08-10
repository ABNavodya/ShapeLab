package src.Circle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
public class CircleController {
    //Declare shape variable
    private Shape shape;

    //set shape
    public void setShape(Shape shape){
        this.shape = shape;
    }
    //get shape
    public Shape getShape() {

        return shape;
    }
    //create circle
    public void createShape() {
        int radius = 50;
        shape = new Ellipse2D.Double(50, 50, 2*radius, 2*radius);
    }
    //delete circle
    public void deleteShape() {

        shape = null;
    }
    //copy circle
    public Shape copyShape() {
        if (shape != null) {
            AffineTransform identity = new AffineTransform();
            return identity.createTransformedShape(shape);
        }
        return null;
    }

    //scale circle
    public void scaleShape(double scale) {
        // Scale the shape
        this.shape = new Ellipse2D.Double(shape.getBounds().getX(), shape.getBounds().getY(), shape.getBounds().getWidth() * scale, shape.getBounds().getHeight() * scale);
    }
    //resize circle
    public void resizeShape(int width, int height) {
        if (getShape() != null) {
            int diameter = Math.min(width, height);
            Rectangle2D bounds = getShape().getBounds2D();
            double scale = diameter / bounds.getWidth();

            AffineTransform at = new AffineTransform();
            at.scale(scale, scale);
            setShape(at.createTransformedShape(getShape()));
        }
    }
    //translate circle
    public void translateShape(int dx, int dy) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.translate(dx, dy);
            shape = at.createTransformedShape(shape);
        }
    }
}
