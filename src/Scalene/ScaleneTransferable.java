package src.Scalene;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ScaleneTransferable implements Transferable {

    private static final long serialVersionUID = 1L;
    public static final DataFlavor SHAPE_DATA_FLAVOR = new DataFlavor(Shape.class, "Shape");

    private Shape shape;

    public ScaleneTransferable(Shape shape) {

        this.shape = shape;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {

        return new DataFlavor[]{SHAPE_DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {

        return flavor.equals(SHAPE_DATA_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(SHAPE_DATA_FLAVOR)) {
            return shape;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

}
