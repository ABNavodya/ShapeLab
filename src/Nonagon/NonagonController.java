package src.Nonagon;

import java.awt.*;
import java.awt.geom.AffineTransform;


public class NonagonController {

    //declare private shape variable

    private Shape shape;

    //set Nonagon shape
    public void setShape(Shape shape){

        this.shape = shape;
    }

    //get Nonagon shape

    public Shape getShape() {

        return shape;
    }

    //create the Nonagon shape
    public void createShape() {
        int n = 9;
        int[] xPoints = new int[n];
        int[] yPoints = new int[n];
        int centerX = 100;
        int centerY = 100;
        int radius = 50;

        for (int i = 0; i < n; i++) {
            xPoints[i] = centerX + (int) (radius * Math.cos(2 * Math.PI * i / n));
            yPoints[i] = centerY + (int) (radius * Math.sin(2 * Math.PI * i / n));
        }

        shape = new Polygon(xPoints, yPoints, n);

    }

    //copy the Nonagon shape

    public Shape copyShape() {
        if (shape != null) {
            AffineTransform identity = new AffineTransform();
            return identity.createTransformedShape(shape);
        }
        return null;
    }

    //delete the Nonagon shape

    public void deleteShape() {

        shape = null;
    }

    //scale the Nonagon shape
    public void scaleShape(double scale) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.scale(scale, scale);
            shape = at.createTransformedShape(shape);

        }
    }
    //rotate the Nonagon shape
    public void rotateShape(double angle) {
        if (shape != null) {
            Rectangle bounds = shape.getBounds();
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angle), bounds.getCenterX(), bounds.getCenterY());
            shape = at.createTransformedShape(shape);
        }
    }
    //resize the Nonagon shape
    public void resizeShape(int width, int height) {
        if (getShape() != null) {
            Rectangle bounds = getShape().getBounds();
            double scaleX = width / (double)bounds.width;
            double scaleY = height / (double)bounds.height;

            AffineTransform at = new AffineTransform();
            at.scale(scaleX, scaleY);
            setShape(at.createTransformedShape(getShape()));
        }
    }
    //translate the Nonagon shape
    public void translateShape(int dx, int dy) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.translate(dx, dy);
            shape = at.createTransformedShape(shape);
        }
    }
}
