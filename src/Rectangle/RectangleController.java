package src.Rectangle;

import java.awt.Rectangle;
import java.awt.*;
import java.awt.geom.AffineTransform;

//Declare Shape Variable
public class RectangleController {
    private Shape shape;

    //set shape method
    public void setShape(Shape shape){
        this.shape = shape;
    }
    //get shape method
    public Shape getShape() {

        return shape;
    }
    //create Rectangle shape
    public void createShape() {

        shape = new Rectangle(50, 50, 100, 50);
    }
    //copy Rectangle shape
    public Shape copyShape() {
        if (shape != null) {
            AffineTransform identity = new AffineTransform();
            return identity.createTransformedShape(shape);
        }
        return null;
    }
    //delete Rectangle shape
    public void deleteShape() {

        shape = null;
    }
    //scale the Rectangle
    public void scaleShape(double scale) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.scale(scale, scale);
            shape = at.createTransformedShape(shape);

        }
    }
    //rotate the Rectangle
    public void rotateShape(double angle) {
        if (shape != null) {
            Rectangle bounds = shape.getBounds();
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angle), bounds.getCenterX(), bounds.getCenterY());
            shape = at.createTransformedShape(shape);
        }
    }
    //move the rectangle left,right,up and down
    public void translateShape(int dx, int dy) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.translate(dx, dy);
            shape = at.createTransformedShape(shape);
        }
    }
    //resize the Rectangle
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
}
