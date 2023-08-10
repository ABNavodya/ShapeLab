package src.Parallelogram;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
public class ParallelogramTransferable implements Transferable{

    private static final long serialVersionUID = 1L; // A unique identifier to enable transfer of the data between applications
    public static final DataFlavor SHAPE_DATA_FLAVOR = new DataFlavor(Shape.class, "Shape");     // A DataFlavor object that represents the type of data being transferred

    private Shape shape;// The shape to be transferred

    public ParallelogramTransferable(Shape shape) { // Constructor to set the shape

        this.shape = shape;
    }

    // Returns an array of DataFlavor objects indicating the flavors the data can be provided in
    @Override
    public DataFlavor[] getTransferDataFlavors() {

        return new DataFlavor[]{SHAPE_DATA_FLAVOR};
    }

    // Returns whether the specified data flavor is supported for this object
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {

        return flavor.equals(SHAPE_DATA_FLAVOR);
    }

    // Returns an object which represents the data to be transferred
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(SHAPE_DATA_FLAVOR)) {
            return shape;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
