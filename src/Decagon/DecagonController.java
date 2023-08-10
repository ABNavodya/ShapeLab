package src.Decagon;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
public class DecagonController {

    //Deaclare private shape variable
    private Shape shape;

    //setshape method to Decagon
    public void setShape(Shape shape){

        this.shape = shape;
    }
    //getshape method to Decagon
    public Shape getShape() {

        return shape;
    }

    //create decagon shape
    public void createShape() {
        Path2D.Double decagon = new Path2D.Double();
        int numberOfSides = 10;
        double centerX = 100;
        double centerY = 100;
        double radius = 50;  // Adjust as needed
        double angleStep = 2 * Math.PI / numberOfSides;

        double startingAngle = Math.PI / 2; // Start from the top point
        for (int i = 0; i < numberOfSides; i++) {
            double angle = startingAngle - angleStep * i;
            double newX = centerX + radius * Math.cos(angle);
            double newY = centerY - radius * Math.sin(angle);

            if (i == 0) {
                decagon.moveTo(newX, newY);
            } else {
                decagon.lineTo(newX, newY);
            }
        }
        decagon.closePath();
        shape = decagon;
    }
    //delete decagon
    public void deleteShape() {

        shape = null;
    }

    //scale the shape decagon
    public void scaleShape(double scale) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.scale(scale, scale);
            shape = at.createTransformedShape(shape);

        }
    }
    //copy the shape decagon
    public Shape copyShape() {
        if (shape != null) {
            AffineTransform identity = new AffineTransform();
            return identity.createTransformedShape(shape);
        }
        return null;
    }

    //rotate the shape decagon
    public void rotateShape(double angle) {
        if (shape != null) {
            Rectangle bounds = shape.getBounds();
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angle), bounds.getCenterX(), bounds.getCenterY());
            shape = at.createTransformedShape(shape);
        }
    }

    //resize the shape decagon
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

    //translate the shape decagon
    public void translateShape(int dx, int dy) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.translate(dx, dy);
            shape = at.createTransformedShape(shape);
        }
    }
}
