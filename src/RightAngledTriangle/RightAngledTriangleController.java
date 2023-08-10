package src.RightAngledTriangle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

//Declare Shape Variable
public class RightAngledTriangleController {

    private Shape shape;
    //set shape method

    public void setShape(Shape shape){
        this.shape = shape;
    }
    //get shape method
    public Shape getShape() {

        return shape;
    }
    //create RightAngled Triangle shape
    public void createShape() {
        Path2D.Double triangle = new Path2D.Double();
        triangle.moveTo(50, 100);
        triangle.lineTo(50, 50);
        triangle.lineTo(100, 100);
        triangle.closePath();
        shape = triangle;
    }
    //copy RightAngled Triangle shape
    public Shape copyShape() {
        if (shape != null) {
            AffineTransform identity = new AffineTransform();
            return identity.createTransformedShape(shape);
        }
        return null;
    }
    // delete RightAngled Triangle shape
    public void deleteShape() {

        shape = null;
    }

    //scale the RightAngled Triangle shape
    public void scaleShape(double scale) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.scale(scale, scale);
            shape = at.createTransformedShape(shape);

        }
    }
    //rotate the  RightAngled Triangle shape
    public void rotateShape(double angle) {
        if (shape != null) {
            Rectangle bounds = shape.getBounds();
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angle), bounds.getCenterX(), bounds.getCenterY());
            shape = at.createTransformedShape(shape);
        }
    }
    //move the RightAngled Triangle shape  left,right,up and down
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
    //resize the RightAngled Triangle shape
    public void translateShape(int dx, int dy) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.translate(dx, dy);
            shape = at.createTransformedShape(shape);
        }
    }
}

