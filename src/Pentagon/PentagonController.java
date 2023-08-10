package src.Pentagon;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

//Declare Shape Variable
public class PentagonController {
     //Declare Shape Variable
    private Shape shape;

    //set shape method
    public void setShape(Shape shape){

        this.shape = shape;
    }

    //get shape method
    public Shape getShape() {

        return shape;
    }

    //create pentagon shape
    public void createShape() {
        Path2D.Double pentagon = new Path2D.Double();
        pentagon.moveTo(100, 50);
        pentagon.lineTo(150, 100);
        pentagon.lineTo(130, 170);
        pentagon.lineTo(70, 170);
        pentagon.lineTo(50, 100);
        pentagon.closePath();
        shape = pentagon;
    }
    //delete shape
    public void deleteShape() {

        shape = null;
    }

    //copy shape
    public Shape copyShape() {
        if (shape != null) {
            AffineTransform identity = new AffineTransform();
            return identity.createTransformedShape(shape);
        }
        return null;
    }
    //scale the pentagon
    public void scaleShape(double scale) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.scale(scale, scale);
            shape = at.createTransformedShape(shape);

        }
    }

    //rotate the pentagon
    public void rotateShape(double angle) {
        if (shape != null) {
            Rectangle bounds = shape.getBounds();
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angle), bounds.getCenterX(), bounds.getCenterY());
            shape = at.createTransformedShape(shape);
        }
    }
    //move the pentagon left,right,up and down
    public void translateShape(int dx, int dy) {
        if (shape != null) {
            AffineTransform at = new AffineTransform();
            at.translate(dx, dy);
            shape = at.createTransformedShape(shape);
        }
    }
    //resize the pentagon
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
