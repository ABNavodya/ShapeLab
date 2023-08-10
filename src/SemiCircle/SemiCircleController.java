package src.SemiCircle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;

public class SemiCircleController {

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

    //create semi circle
    public void createShape() {
        shape = new Arc2D.Double(50, 50, 100, 100, 0, 180, Arc2D.PIE);

    }

    //delete semicircle
    public void deleteShape() {

        shape = null;
    }
    //copy semi circle
    public Shape copyShape(){
        if(shape != null){
            AffineTransform identity = new AffineTransform();
            return identity.createTransformedShape(shape);
        }
        return null;
    }

    //scale semi circle
    public void scaleShape(double scale) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.scale(scale, scale);
            shape = at.createTransformedShape(shape);

        }
    }
    //rotate semi circle
    public void rotateShape(double angle) {
        if (shape != null) {
            Rectangle bounds = shape.getBounds();
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angle), bounds.getCenterX(), bounds.getCenterY());
            shape = at.createTransformedShape(shape);
        }
    }
    //resize semi circle
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
    //translate semi circle
    public void translateShape(int dx, int dy) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.translate(dx, dy);
            shape = at.createTransformedShape(shape);
        }
    }
}
