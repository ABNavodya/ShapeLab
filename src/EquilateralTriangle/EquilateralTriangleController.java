package src.EquilateralTriangle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class EquilateralTriangleController {
    private Shape shape;

    public void setShape(Shape shape){
        this.shape = shape;
    }

    public Shape getShape() {

        return shape;
    }

    public void createShape() {
        Path2D.Double triangle = new Path2D.Double();
        double baseLength = 100;
        double x = 50;
        double y = 150;
        triangle.moveTo(x, y);
        triangle.lineTo(x + baseLength, y);
        triangle.lineTo(x + baseLength / 2, y - (baseLength * Math.sqrt(3) / 2));
        triangle.closePath();
        shape = triangle;
    }

    public Shape copyShape() {
        if (shape != null) {
            AffineTransform identity = new AffineTransform();
            return identity.createTransformedShape(shape);
        }
        return null;
    }

    public void deleteShape() {

        shape = null;
    }

    public void scaleShape(double scale) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.scale(scale, scale);
            shape = at.createTransformedShape(shape);

        }
    }

    public void rotateShape(double angle) {
        if (shape != null) {
            Rectangle bounds = shape.getBounds();
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angle), bounds.getCenterX(), bounds.getCenterY());
            shape = at.createTransformedShape(shape);
        }
    }

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

    public void translateShape(int dx, int dy) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.translate(dx, dy);
            shape = at.createTransformedShape(shape);
        }
    }
}
