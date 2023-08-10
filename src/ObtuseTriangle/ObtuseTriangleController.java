package src.ObtuseTriangle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class ObtuseTriangleController {
    private Shape shape;

    //Sets the shape of the triangle.
    public void setShape(Shape shape){
        this.shape = shape;
    }

    //Gets the shape of the triangle.
    public Shape getShape() {

        return shape;
    }

    //Creates a new Path2D.Double triangle shape.
    public void createShape() {
        Path2D.Double triangle = new Path2D.Double();
        triangle.moveTo(50, 100);
        triangle.lineTo(150, 100);
        triangle.lineTo(75, 50);
        triangle.closePath();
        shape = triangle;
    }

    //Creates a copy of the current shape using an AffineTransform.
    public Shape copyShape() {
        if (shape != null) {
            AffineTransform identity = new AffineTransform();
            return identity.createTransformedShape(shape);
        }
        return null;
    }

    //Deletes the current shape.
    public void deleteShape() {

        shape = null;
    }

    //Scales the shape of the triangle by a given scale.
    public void scaleShape(double scale) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.scale(scale, scale);
            shape = at.createTransformedShape(shape);

        }
    }

    //Rotates the shape of the triangle by a given angle.
    public void rotateShape(double angle) {
        if (shape != null) {
            Rectangle bounds = shape.getBounds();
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angle), bounds.getCenterX(), bounds.getCenterY());
            shape = at.createTransformedShape(shape);
        }
    }

    //Resizes the shape of the triangle to a given width and height using an AffineTransform.
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

    //Translates the shape of the triangle by a given distance.
    public void translateShape(int dx, int dy) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.translate(dx, dy);
            shape = at.createTransformedShape(shape);
        }
    }
}
