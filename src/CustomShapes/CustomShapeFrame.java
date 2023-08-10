package src.CustomShapes;

import javax.swing.*;
import java.awt.*;

public class CustomShapeFrame extends JFrame{

    //create a constructor
    public CustomShapeFrame(){
        // set the title
        setTitle("Custom Shapes Drawing Frame");
        setLayout(new BorderLayout());

        // create a new instance of the class called CustomShapeCanvas
        CustomShapeCanvas customShapeCanvas = new CustomShapeCanvas();
        //passing the toolbar panel to the instance of the CustomShapeCanvas
        JPanel customShapeToolBarPanel = new CustomShapeToolBar(customShapeCanvas);
        //set the toolbar to the north
        add(customShapeToolBarPanel, BorderLayout.NORTH);
        //set the CustomShapeCanvas to the center
        add(customShapeCanvas, BorderLayout.CENTER);
        //set the size
        setSize(new Dimension(1000,700));
        //display the frame to the center when the custom shapes screen runs.
        setLocationRelativeTo(null);
    }
}
