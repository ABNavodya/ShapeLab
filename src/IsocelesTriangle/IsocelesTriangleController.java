package src.IsocelesTriangle;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
public class IsocelesTriangleController {

    //declare private shape variable
    private Shape shape;

    //set IsocelesTriangle shape
    public void setShape(Shape shape){
        this.shape = shape;
    }

    //get IsocelesTriangle shape

    public Shape getShape() {

        return shape;
    }
     //create the IsocelesTriangle shape
    public void createShape() {
        Path2D.Double triangle = new Path2D.Double();
        triangle.moveTo(50, 100);
        triangle.lineTo(150, 100);
        triangle.lineTo(100, 50);
        triangle.closePath();
        shape = triangle;
    }
    //copy the IsocelesTriangle shape
    public Shape copyShape() {
        if (shape != null) {
            AffineTransform identity = new AffineTransform();
            return identity.createTransformedShape(shape);
        }
        return null;
    }
    //delete the IsocelesTriangle shape
    public void deleteShape() {

        shape = null;
    }
    //scale the IsocelesTriangle shape
    public void scaleShape(double scale) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.scale(scale, scale);
            shape = at.createTransformedShape(shape);

        }
    }
    //rotate the IsocelesTriangle shape
    public void rotateShape(double angle) {
        if (shape != null) {
            Rectangle bounds = shape.getBounds();
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angle), bounds.getCenterX(), bounds.getCenterY());
            shape = at.createTransformedShape(shape);
        }
    }
    //resize the IsocelesTriangle shape
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
    //translate the IsocelesTriangle shape
    public void translateShape(int dx, int dy) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.translate(dx, dy);
            shape = at.createTransformedShape(shape);
        }
    }
}
