package src.Pentagon;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class PentagonTransferable implements Transferable {

    //declare a unique identifier for each serializable method
    private static final long serialVersionUID = 1L;

    //create a java class that provides the metadata representation of a particular object that is being transferred in a clipboard
    public static final DataFlavor SHAPE_DATA_FLAVOR = new DataFlavor(Shape.class, "Shape");

    //declare a private shape variable
    private Shape shape;

    //create a parameterized constructor by accessing the current context
    public PentagonTransferable(Shape shape) {

        this.shape = shape;
    }
    //this is used for data transfer operations like copy and paste
    @Override
    public DataFlavor[] getTransferDataFlavors() {

        return new DataFlavor[]{SHAPE_DATA_FLAVOR};
    }
    //this checks whether the specified data flavour is supported by the transferable object
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {

        return flavor.equals(SHAPE_DATA_FLAVOR);
    }
    // method to get the data of the transferable object
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(SHAPE_DATA_FLAVOR)) {
            return shape;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
