package src.Parallelogram;

/*import src.Pentagon.PentagonFrame;
import src.Pentagon.PentagonController;
import src.Pentagon.PentagonPanel;
import src.Pentagon.PentagonTransferable;*/
import src.Polygons;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
public class ParallelogramFrame extends JFrame{
    private ParallelogramController paralleogramController;
    private ParallelogramPanel parallelogramPanel;

    private boolean newScaleInputProvided = false; // Flag to indicate whether a new scale input has been provided or not

    private Stack<Shape> undoStack = new Stack<>(); // Stack to hold previous shapes
    private Stack<Shape> redoStack = new Stack<>(); // Stack to hold shapes that were undone

    public ParallelogramFrame(){
        setTitle("Parallelogram Polygon Screen"); // Set the title of the frame
        setSize(1000, 700); // Set the size of the frame
        setLocationRelativeTo(null);

        paralleogramController = new ParallelogramController(); // Create a new ParallelogramController object
        parallelogramPanel = new ParallelogramPanel(paralleogramController); // Create a new ParallelogramPanel object and pass the ParallelogramController object to it
        parallelogramPanel.setPreferredSize(new Dimension(600,600)); // Set the preferred size of the ParallelogramPanel object
        add(parallelogramPanel, BorderLayout.CENTER); // Add the ParallelogramPanel object to the center of the frame

        //Menu Bar***
        JMenuBar menuBar = new JMenuBar(); // Create a new menu bar
        menuBar.setBackground(new Color(48, 57, 82)); // Set the background color of the menu bar
        setJMenuBar(menuBar); // Set the menu bar of the frame


        // Create topPanel with BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(255, 255, 255));

        // Add backToPolygonsBtn to the left (west) of the topPanel
        JButton backToPolygonsBtn = new JButton("Back");

        //Set style for the rotate button
        backToPolygonsBtn.setForeground(Color.BLACK);
        backToPolygonsBtn.setBackground(new Color(255, 255, 255));
        backToPolygonsBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        // Remove the border of the button
        backToPolygonsBtn.setBorderPainted(false);

        backToPolygonsBtn.setToolTipText("Back to Polygons"); // Set tool tip text which will display on hover
        //set to the Polygons Screen
        backToPolygonsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polygons objPolygons = new Polygons();
                objPolygons.setVisible(true);
            }
        });
        topPanel.add(backToPolygonsBtn, BorderLayout.WEST);

        // Add pageTitle to the center of the topPanel
        JLabel pageTitle = new JLabel("Parallelogram", SwingConstants.CENTER); // Center the text in the JLabel
        pageTitle.setFont(new Font("Times New Roman", Font.BOLD, 30)); // Add styles to the title
        pageTitle.setForeground(Color.BLACK); // Set title color
        topPanel.add(pageTitle, BorderLayout.CENTER); // Add the label to the center of the top panel

        add(topPanel, BorderLayout.NORTH); // Add topPanel to the north of the frame


        // Create a new JButton to be used as the Save button
        JButton saveBtn = new JButton("Save");

        // Set the font color and style of the button
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setBackground(new Color(48, 57, 82));
        saveBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        // Remove the border of the button
        saveBtn.setBorderPainted(false);

        menuBar.add(saveBtn); // Add the Save button to the menu bar

        // Add an ActionListener to the Save button to perform an action when it is clicked
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shape shape = paralleogramController.getShape(); // Get the current shape from the controller

                // Create a new BufferedImage with the same size as the parallelogramPanel
                BufferedImage image = new BufferedImage(parallelogramPanel.getWidth(), parallelogramPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
                // Create a new Graphics2D object to draw on the image
                Graphics2D g2 = image.createGraphics();

                // Translate the Graphics2D object to the upper-left corner of the shape's bounding box
                g2.translate(-shape.getBounds().x, -shape.getBounds().y);
                parallelogramPanel.paint(g2); // Draw the parallelogramPanel on the Graphics2D object
                g2.dispose(); // Dispose the Graphics2D object

                // Create a new JFileChooser to allow the user to choose where to save the file
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");
                int userSelection = fileChooser.showSaveDialog(ParallelogramFrame.this); // Show the save dialog and get the user's selection

                // If the user selected a file, save the image to that file as a PNG
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    try {
                        ImageIO.write(image, "PNG", fileToSave);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        //Edit Menu
        JMenu editMenu = new JMenu("Edit"); // Create a new menu

        //Set styles to the edit menu
        editMenu.setForeground(Color.WHITE);
        editMenu.setBackground(new Color(48, 57, 82));
        editMenu.setFont(new Font("Times New Roman", Font.BOLD, 16));

        menuBar.add(editMenu); // Add the menu to the menu bar

        //Copy
        JMenuItem copy = new JMenuItem("Copy"); // Create a new menu item

        //Set styles to the copy button inside the menu
        copy.setForeground(Color.WHITE);
        copy.setBackground(new Color(48, 57, 82));
        copy.setFont(new Font("Times New Roman", Font.BOLD, 16));

        editMenu.add(copy); // Add the menu item to the Edit menu
        copy.addActionListener(new ActionListener() { // Add an action listener to the Copy menu item
            @Override
            public void actionPerformed(ActionEvent e) { // Handle the Copy action event
                if (paralleogramController.getShape() != null) { // If a shape exists in the ParallelogramController object

                    Shape copiedShape = (Shape) paralleogramController.copyShape(); // Copy the shape

                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); // Get the system clipboard
                    Transferable transferable = new ParallelogramTransferable(copiedShape); // Create a new ParallelogramTransferable object and pass the copied shape to it
                    clipboard.setContents(transferable, null); // Set the contents of the clipboard to the ParallelogramTransferable object
                }
            }
        });

        JMenuItem cut = new JMenuItem("Cut"); // Create a JMenuItem object for Cut operation and add it to the Edit menu

        //Set styles to the cut button inside the menu
        cut.setForeground(Color.WHITE);
        cut.setBackground(new Color(48, 57, 82));
        cut.setFont(new Font("Times New Roman", Font.BOLD, 16));

        editMenu.add(cut);
        cut.addActionListener(new ActionListener() { // Register an ActionListener for the Cut operation
            @Override
            public void actionPerformed(ActionEvent e) {

                // Check if there is a shape to cut
                if (paralleogramController.getShape() != null) {
                    // Push the current shape to the undo stack and redo stack
                    undoStack.push(paralleogramController.getShape());
                    redoStack.push(paralleogramController.getShape());

                    // Get the current shape to be cut
                    Shape cutShape = paralleogramController.getShape();

                    // Delete the current shape
                    paralleogramController.deleteShape();
                    parallelogramPanel.repaint();

                    // Copy the cut shape to the system clipboard
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    Transferable transferable = new ParallelogramTransferable(cutShape);
                    clipboard.setContents(transferable, null);
                }
            }
        });

        // Create a JMenuItem object for Paste operation and add it to the Edit menu
        JMenuItem paste = new JMenuItem("Paste");

        //Set styles to the paste button inside the menu
        paste.setForeground(Color.WHITE);
        paste.setBackground(new Color(48, 57, 82));
        paste.setFont(new Font("Times New Roman", Font.BOLD, 16));

        editMenu.add(paste);
        // Register an ActionListener for the Paste operation
        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Get the contents of the system clipboard
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable transferable = clipboard.getContents(this);

                // Check if the contents of the clipboard is a supported data flavor
                if (transferable != null && transferable.isDataFlavorSupported(ParallelogramTransferable.SHAPE_DATA_FLAVOR)) {
                    try {

                        // Get the shape data from the clipboard and paste it to the panel
                        Shape pastedShape = (Shape) transferable.getTransferData(ParallelogramTransferable.SHAPE_DATA_FLAVOR);

                        // Translate the pasted shape to a desired location
                        AffineTransform at = new AffineTransform();
                        Rectangle bounds = pastedShape.getBounds();
                        int x = 100; // Set the desired x position for the paste operation
                        int y = 100; // Set the desired y position for the paste operation
                        at.translate(x - bounds.x, y - bounds.y);
                        Shape translatedShape = at.createTransformedShape(pastedShape);

                        // Set the translated copy as the current shape
                        paralleogramController.setShape(translatedShape);
                        parallelogramPanel.repaint();
                    } catch (UnsupportedFlavorException | IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Create a new JButton to be used as the Undo button
        JButton undoBtn = new JButton("Undo");

        // Set the font color and style of the button
        undoBtn.setForeground(Color.WHITE);
        undoBtn.setBackground(new Color(48, 57, 82));
        undoBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        // Remove the border of the button
        undoBtn.setBorderPainted(false);

        menuBar.add(undoBtn); // Add the Undo button to the menu bar

        undoBtn.addActionListener(e -> { // Add an ActionListener to the Undo button to perform an action when it is clicked
            // If the undo stack is not empty
            if (!undoStack.isEmpty()) {
                // Push the current shape onto the redo stack
                redoStack.push(paralleogramController.getShape());
                // Pop the top shape off the undo stack and set it as the current shape in the controller
                undoStack.pop();
                if (!undoStack.isEmpty()) {
                    paralleogramController.setShape(undoStack.peek());
                } else {
                    paralleogramController.deleteShape();
                }
                // Repaint the parallelogramPanel
                parallelogramPanel.repaint();
            }

        });

        // Create a "Redo" button
        JButton redoBtn = new JButton("Redo");

        // Set the font color and style of the button
        redoBtn.setForeground(Color.WHITE);
        redoBtn.setBackground(new Color(48, 57, 82));
        redoBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        // Remove the border of the button
        redoBtn.setBorderPainted(false);

        menuBar.add(redoBtn); // Add the button to the menu bar

        redoBtn.addActionListener(e -> { // Add an action listener for the button
            if (!redoStack.isEmpty()) { // If the redo stack is not empty
                undoStack.push(paralleogramController.getShape()); // Push the current shape onto the undo stack
                paralleogramController.setShape(redoStack.pop()); // Set the shape to the top shape on the redo stack
                parallelogramPanel.repaint(); // Repaint the parallelogram panel
            }
        });

        class RoundedPanel extends JPanel {
            private int arcRadius;

            public RoundedPanel(int arcRadius) {
                this.arcRadius = arcRadius;
            }

            @Override
            protected void paintComponent(Graphics g) {
                Shape roundedRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcRadius, arcRadius);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fill(roundedRect);
                g2d.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 100); // Adjust the width and height as per your requirement
            }
        }

        // Wrapper Panel
        RoundedPanel wrapperPanel = new RoundedPanel(20);
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.setBackground(Color.LIGHT_GRAY);

        // Create an invisible component to push the detailLabel down
        wrapperPanel.add(Box.createRigidArea(new Dimension(0, 60)));

        // Detail Panel
        JPanel parallelogramDetailPanel = new JPanel();
        parallelogramDetailPanel.setLayout(new BorderLayout());
        parallelogramDetailPanel.setOpaque(false);

        // Add detail label
        JLabel detailLabel = new JLabel("<html>Parallelogram Details:<br>"
                + "1. A parallelogram is a four-sided <br> polygon with opposite sides parallel.<br>"
                + "2. Opposite sides of a parallelogram are <br> equal in length.<br></html>");
        // Set font size
        detailLabel.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Font name, style, and size
        parallelogramDetailPanel.add(detailLabel, BorderLayout.CENTER);

        wrapperPanel.add(parallelogramDetailPanel);
        wrapperPanel.add(Box.createVerticalGlue());

        add(wrapperPanel, BorderLayout.EAST);

        // Create a new panel for control buttons
        JPanel controlPanel = new JPanel();

        controlPanel.setBackground(new Color(255, 255, 255));//Set the background color for the control Panel

        controlPanel.setLayout(new GridLayout(0, 2));// Set the layout of the panel to a grid with 2 columns
        add(controlPanel, BorderLayout.SOUTH); // Add the control panel to the south of the frame

        // Create a "Create" button
        JButton createBtn = new JButton("Create the parallelogram");

        //Set style for the create button
        createBtn.setForeground(Color.WHITE);
        createBtn.setBackground(new Color(48, 57, 82));
        createBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(createBtn); // Add the button to the control panel
        createBtn.addActionListener(e -> { // Add an action listener for the button
            redoStack.push(paralleogramController.getShape()); // Push the current shape onto the redo stack
            undoStack.push(paralleogramController.getShape()); // Push the current shape onto the undo stack
            paralleogramController.createShape(); // Call the createShape method on the parallelogram controller to create a new shape
            parallelogramPanel.repaint(); // Repaint the parallelogram panel
        });
        // Create a "Delete" button
        JButton deleteBtn = new JButton("Delete the parallelogram");

        //Set style for the create button
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setBackground(new Color(48, 57, 82));
        deleteBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(deleteBtn); // Add the button to the control panel
        deleteBtn.addActionListener(e -> { // Add an action listener for the button
            redoStack.push(paralleogramController.getShape()); // Push the current shape onto the redo stack
            undoStack.push(paralleogramController.getShape()); // Push the current shape onto the undo stack
            paralleogramController.deleteShape(); // Call the deleteShape method on the parallelogram controller to delete the current shape
            parallelogramPanel.repaint(); // Repaint the parallelogram panel
        });

        // Create a text field for scale input
        JTextField scaleTf = new JTextField();//***
        controlPanel.add(scaleTf); // Add the text field to the control panel
        // Create a "Scale" button
        JButton scaleBtn = new JButton("Scale the parallelogram");

        //Set style for the create button
        scaleBtn.setForeground(Color.WHITE);
        scaleBtn.setBackground(new Color(48, 57, 82));
        scaleBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(scaleBtn); // Add the button to the control panel

        scaleTf.getDocument().addDocumentListener(new DocumentListener() { // Add a document listener to the text field
            @Override
            public void insertUpdate(DocumentEvent e) { // When text is inserted

                newScaleInputProvided = true; // Set a flag indicating new input is provided
            }

            @Override
            public void removeUpdate(DocumentEvent e) { // When text is removed

                newScaleInputProvided = true; // Set a flag indicating new input is provided
            }

            @Override
            public void changedUpdate(DocumentEvent e) { // When the document is changed

                newScaleInputProvided = true; // Set a flag indicating new input is provided
            }
        });

        // Create a text field and a button for scaling the parallelogram
        scaleBtn.addActionListener(e -> {
            // Check if a new scale input has been provided
            if (newScaleInputProvided) {
                try {
                    // Parse the scale input from the text field
                    double scale = Double.parseDouble(scaleTf.getText());
                    // Check if the scale value is greater than 3
                    if (scale >= 3) {
                        // Display an error message if the scale value is invalid
                        JOptionPane.showMessageDialog(this, "Maximum scale value is 3. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Push the current shape onto the undo and redo stacks
                        redoStack.push(paralleogramController.getShape());
                        undoStack.push(paralleogramController.getShape());
                        // Scale the shape by the given factor
                        paralleogramController.scaleShape(scale);
                        // Repaint the parallelogram panel with the scaled shape
                        parallelogramPanel.repaint();
                        // Reset the flag for new scale input provided
                        newScaleInputProvided = false;
                    }
                } catch (NumberFormatException ex) {
                    // Display an error message if the scale input is invalid
                    JOptionPane.showMessageDialog(this, "Invalid scale input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Create a text field and a button for rotating the parallelogram
        JTextField rotateTf = new JTextField();//***
        controlPanel.add(rotateTf);
        JButton rotateBtn = new JButton("Rotate the parallelogram");

        //Set style for the rotate button
        rotateBtn.setForeground(Color.WHITE);
        rotateBtn.setBackground(new Color(48, 57, 82));
        rotateBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(rotateBtn);
        rotateBtn.addActionListener(e -> {
            try {
                // Parse the rotation angle from the text field
                double angle = Double.parseDouble(rotateTf.getText());
                // Push the current shape onto the undo and redo stacks
                redoStack.push(paralleogramController.getShape());
                undoStack.push(paralleogramController.getShape());
                // Rotate the shape by the given angle
                paralleogramController.rotateShape(angle);
                // Repaint the parallelogram panel with the rotated shape
                parallelogramPanel.repaint();
            } catch (NumberFormatException ex) {
                // Display an error message if the rotation input is invalid
                JOptionPane.showMessageDialog(this, "Invalid rotation input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Create a text field and a button for setting the width of the parallelogram
        JTextField widthTf = new JTextField();//***
        controlPanel.add(widthTf);
        JButton widthBtn = new JButton("Give Width to the parallelogram");

        //Set style for the width button
        widthBtn.setForeground(Color.WHITE);
        widthBtn.setBackground(new Color(48, 57, 82));
        widthBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(widthBtn);
        widthBtn.addActionListener(e -> {
            try {
                // Parse the width input from the text field
                int width = Integer.parseInt(widthTf.getText());
                // Check if the width value is greater than the maximum allowed value
                if (width > 340) {
                    // Display an error message if the width value is invalid
                    JOptionPane.showMessageDialog(this, "Maximum width is 340. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Push the current shape onto the undo and redo stacks
                undoStack.push(paralleogramController.getShape());
                redoStack.push(paralleogramController.getShape());
                // Resize the shape to the given width
                paralleogramController.resizeShape(width, paralleogramController.getShape().getBounds().height);
                // Repaint the parallelogram panel with the resized shape
                parallelogramPanel.repaint();
            } catch (NumberFormatException ex) {
                // If the input value is not a valid integer, show an error message
                JOptionPane.showMessageDialog(this, "Invalid width input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Create a new text field for height input
        JTextField heightTf = new JTextField();//***
        // Add the height text field to the control panel
        controlPanel.add(heightTf);
        // Create a new button for setting the height
        JButton heightBtn = new JButton("Give Height to the parallelogram");
        //Set style for the height button
        heightBtn.setForeground(Color.WHITE);
        heightBtn.setBackground(new Color(48, 57, 82));
        heightBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        // Add the height button to the control panel
        controlPanel.add(heightBtn);
        // Add an ActionListener to the height button to handle setting the height
        heightBtn.addActionListener(e -> {
            try {
                // Parse the input value from the height text field
                int height = Integer.parseInt(heightTf.getText());
                // If the input value is greater than 340, show an error message and return
                if (height > 340) {
                    JOptionPane.showMessageDialog(this, "Maximum height is 340. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Push the current shape to the undo and redo stacks
                undoStack.push(paralleogramController.getShape());
                redoStack.push(paralleogramController.getShape());
                // Resize the shape to the specified height and keep the width the same
                paralleogramController.resizeShape(paralleogramController.getShape().getBounds().width, height);
                // Repaint the parallelogram panel
                parallelogramPanel.repaint();
            } catch (NumberFormatException ex) {
                // If the input value is not a valid integer, show an error message
                JOptionPane.showMessageDialog(this, "Invalid height input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // create a new JTextField for inputting X translation value
        JTextField translateXTf = new JTextField();//***
        // add the X translation JTextField to the control panel
        controlPanel.add(translateXTf);
        // create a new JButton for triggering X translation
        JButton translateXBtn = new JButton("Move right or left");
        //Set style for the translateX button
        translateXBtn.setForeground(Color.WHITE);
        translateXBtn.setBackground(new Color(48, 57, 82));
        translateXBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        // add the X translation JButton to the control panel
        controlPanel.add(translateXBtn);
        // add an ActionListener to the X translation JButton to handle translation
        translateXBtn.addActionListener(e -> {
            try {
                // parse the input X translation value as an integer
                int dx = Integer.parseInt(translateXTf.getText());
                // check if the input value is greater than the maximum allowed value
                if(dx > 45) {
                    // show an error message if the input value is too large
                    JOptionPane.showMessageDialog(this, "Couldn't Go beyond the 45");
                    return;
                }
                // push the current shape state onto the redo stack
                redoStack.push(paralleogramController.getShape());
                // push the current shape state onto the undo stack
                undoStack.push(paralleogramController.getShape());
                // translate the shape by the specified X and Y amounts
                paralleogramController.translateShape(dx, 0);
                // repaint the parallelogram panel to update the displayed shape
                parallelogramPanel.repaint();
                // show an error message if the input value cannot be parsed as an integer
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid translation input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // create a new JTextField for inputting Y translation value
        JTextField translateYTf = new JTextField();//***
        // add the Y translation JTextField to the control panel
        controlPanel.add(translateYTf);
        // create a new JButton for triggering Y translation
        JButton translateYBtn = new JButton("Move up or down");
        //Set style for the translateY button
        translateYBtn.setForeground(Color.WHITE);
        translateYBtn.setBackground(new Color(48, 57, 82));
        translateYBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        // add the Y translation JButton to the control panel
        controlPanel.add(translateYBtn);
        // add an ActionListener to the Y translation JButton to handle translation
        translateYBtn.addActionListener(e -> {
            try {
                // parse the input Y translation value as an integer
                int dy = Integer.parseInt(translateYTf.getText());
                // check if the input value is greater than the maximum allowed value
                if(dy > 45) {
                    // show an error message if the input value is too large
                    JOptionPane.showMessageDialog(this, "Couldn't Go beyond the 45");
                    return;
                }
                // translate the shape by the specified X and Y amounts
                paralleogramController.translateShape(0, dy);
                // push the current shape state onto the redo stack
                redoStack.push(paralleogramController.getShape());
                // push the current shape state onto the undo stack
                undoStack.push(paralleogramController.getShape());
                // repaint the parallelogram panel to update the displayed shape
                parallelogramPanel.repaint();
            } catch (NumberFormatException ex) {
                // show an error message if the input value cannot be parsed as an integer
                JOptionPane.showMessageDialog(this, "Invalid translation input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // create a new JButton for navigate to the polygons
        JButton VisualizeBtn = new JButton("Visualize parallelogram in 3D ");
        //Set style for the color button
        VisualizeBtn.setForeground(Color.WHITE);
        VisualizeBtn.setBackground(new Color(48, 57, 82));
        VisualizeBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        // add the color chooser JButton to the control panel
        controlPanel.add(VisualizeBtn);

        VisualizeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // create a new JButton for choosing a new color for the parallelogram
        JButton colorBtn = new JButton("Choose Color to the Parallelogram");
        //Set style for the color button
        colorBtn.setForeground(Color.WHITE);
        colorBtn.setBackground(new Color(48, 57, 82));
        colorBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        // add the color chooser JButton to the control panel
        controlPanel.add(colorBtn);

        // add an ActionListener to the color chooser JButton to handle color selection
        colorBtn.addActionListener(e -> {
            // open a color chooser dialog and get the selected color
            Color newColor = JColorChooser.showDialog(this, "Choose a color", parallelogramPanel.getColor());
            // check if a new color was selected
            if (newColor != null) {
                redoStack.push(paralleogramController.getShape());
                undoStack.push(paralleogramController.getShape());
                parallelogramPanel.setColor(newColor);
                parallelogramPanel.repaint();
            }
        });
    }
}
