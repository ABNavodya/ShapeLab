package src.Scalene;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class ScaleneController {

    //declare shape variable

    private Shape shape;

    //set shape

    public void setShape(Shape shape){
        this.shape = shape;
    }

    //get shape

    public Shape getShape() {

        return shape;
    }
    //create scalene triangle
    public void createShape() {
        Path2D.Double triangle = new Path2D.Double();
        triangle.moveTo(50, 100);
        triangle.lineTo(100, 100);
        triangle.lineTo(75, 50);
        triangle.closePath();
        shape = triangle;

    }

    //delete scalene triangle
    public void deleteShape() {

        shape = null;
    }

    //copy scalene triangle
    public Shape copyShape() {
        if (shape != null) {
            AffineTransform identity = new AffineTransform();
            return identity.createTransformedShape(shape);
        }
        return null;
    }


    //scale scalene triangle
    public void scaleShape(double scale) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.scale(scale, scale);
            shape = at.createTransformedShape(shape);

        }
    }

    //rotate scalene triangle
    public void rotateShape(double angle) {
        if (shape != null) {
            Rectangle bounds = shape.getBounds();
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angle), bounds.getCenterX(), bounds.getCenterY());
            shape = at.createTransformedShape(shape);
        }
    }

    //translate/move scalene triangle
    public void translateShape(int dx, int dy) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.translate(dx, dy);
            shape = at.createTransformedShape(shape);
        }
    }

    //resize scalene triangle
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
