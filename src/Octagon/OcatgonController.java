package src.Octagon;

import java.awt.*;
import java.awt.geom.AffineTransform;


public class OcatgonController {
    private Shape shape;
    public void setShape(Shape shape){

        this.shape = shape;
    }

    public Shape getShape() {

        return shape;
    }
    public void createShape() {
        int n = 8;
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
    public void deleteShape() {

        shape = null;
    }

    public Shape copyShape() {
        if (shape != null) {
            AffineTransform identity = new AffineTransform();
            return identity.createTransformedShape(shape);
        }
        return null;
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