package src.ObtuseTriangle;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ObtuseTriangleTransferable implements Transferable{

    private static final long serialVersionUID = 1L;

    // DataFlavor object for Shape data transfer
    public static final DataFlavor SHAPE_DATA_FLAVOR = new DataFlavor(Shape.class, "Shape");

    private Shape shape;

    // Constructor to set the Shape to be transferred
    public ObtuseTriangleTransferable(Shape shape) {

        this.shape = shape;
    }

    // Returns an array of supported DataFlavor objects
    @Override
    public DataFlavor[] getTransferDataFlavors() {

        return new DataFlavor[]{SHAPE_DATA_FLAVOR};
    }

    // Returns true if the specified DataFlavor is supported
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {

        return flavor.equals(SHAPE_DATA_FLAVOR);
    }

    // Returns the Shape object that is being transferred
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(SHAPE_DATA_FLAVOR)) {
            return shape;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
