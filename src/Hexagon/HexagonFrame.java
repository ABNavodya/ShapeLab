package src.Hexagon;

import src.HexagonalPrism;
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

public class HexagonFrame extends JFrame{
    private HexagonController hexagonController;
    private HexagonPanel hexagonPanel;
    private Stack<Shape> undoStack = new Stack<>();
    private Stack<Shape> redoStack = new Stack<>();
    private boolean newScaleInputProvided = false;

    public HexagonFrame(){
        setTitle("Hexagon Polygon Screen");
        setSize(1000, 700); //Setting title and size
        setLocationRelativeTo(null);

        hexagonController = new HexagonController(); //Initializing controller and panel
        hexagonPanel = new HexagonPanel(hexagonController);
        hexagonPanel.setPreferredSize(new Dimension(600, 600)); //Setting preferred size
        add(hexagonPanel, BorderLayout.CENTER); //Adding panel to layout

        //Menu Bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(48, 57, 82)); // Set the background color
        setJMenuBar(menuBar); //Creating and setting menu bar

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
        JLabel pageTitle = new JLabel("Hexagon", SwingConstants.CENTER); // Center the text in the JLabel
        pageTitle.setFont(new Font("Times New Roman", Font.BOLD, 30)); // Add styles to the title
        pageTitle.setForeground(Color.BLACK); // Set title color
        topPanel.add(pageTitle, BorderLayout.CENTER); // Add the label to the center of the top panel

        add(topPanel, BorderLayout.NORTH); // Add topPanel to the north of the frame

        //menubar content
        JButton saveBtn = new JButton("Save");
        // Set the font color and style of the button
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setBackground(new Color(48, 57, 82));
        saveBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        pageTitle.setFont(new Font("Times New Roman", Font.BOLD, 30)); // Add styles
        pageTitle.setForeground(Color.BLACK);//set title color
        // Remove the border of the button
        saveBtn.setBorderPainted(false);
        menuBar.add(saveBtn); //Creating a JButton named saveBtn with the label "Save"
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shape shape = hexagonController.getShape();
                // Create a new buffered image with the size of the shape
                BufferedImage image = new BufferedImage(hexagonPanel.getWidth(), hexagonPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = image.createGraphics();
                // Make sure the transformation is set to the top left corner of the shape
                g2.translate(-shape.getBounds().x, -shape.getBounds().y);
                hexagonPanel.paint(g2);
                g2.dispose();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");
                int userSelection = fileChooser.showSaveDialog(HexagonFrame.this); //Creating a JFileChooser named fileChooser, Setting the dialog title to "Specify a file to save", Showing the save dialog and storing the user's selection in userSelection

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    try {
                        ImageIO.write(image, "PNG", fileToSave);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } //Checking if the user selected the "Approve" option in the file chooser dialog, Getting the selected file using getSelectedFile(), Catching and printing any IOException that may occur
            }
        });

        //Edit Menu
        JMenu editMenu = new JMenu("Edit");
        //Set styles to the edit menu
        editMenu.setForeground(Color.WHITE);
        editMenu.setBackground(new Color(48, 57, 82));
        editMenu.setFont(new Font("Times New Roman", Font.BOLD, 16));

        menuBar.add(editMenu); //Creating and edit "Edit" menu

        //COpy
        JMenuItem copy = new JMenuItem("Copy");
        //Set styles to the copy button inside the menu
        copy.setForeground(Color.WHITE);
        copy.setBackground(new Color(48, 57, 82));
        copy.setFont(new Font("Times New Roman", Font.BOLD, 16));
        editMenu.add(copy); //Creating "Copy" menu item
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //copy operation
                if (hexagonController.getShape() != null) {
                    // Create a copy of the current shape
                    Shape copiedShape = (Shape) hexagonController.copyShape();
                    // Add the copy to the system clipboard
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    Transferable transferable = new HexagonTransferable(copiedShape);
                    clipboard.setContents(transferable, null);
                }
            }
        });

        //CUt
        JMenuItem cut = new JMenuItem("Cut");
        //Set styles to the copy button inside the menu
        cut.setForeground(Color.WHITE);
        cut.setBackground(new Color(48, 57, 82));
        cut.setFont(new Font("Times New Roman", Font.BOLD, 16));
        editMenu.add(cut); //Creating "Cut" menu item
        cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cut operation
                if (hexagonController.getShape() != null) {
                    undoStack.push(hexagonController.getShape());
                    redoStack.push(hexagonController.getShape());

                    // Store the cut shape temporarily
                    Shape cutShape = hexagonController.getShape();

                    hexagonController.deleteShape();
                    hexagonPanel.repaint();

                    // Set the cut shape as the clipboard content
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    Transferable transferable = new HexagonTransferable(cutShape);
                    clipboard.setContents(transferable, null);
                }
            }
        });

        //paste
        JMenuItem paste = new JMenuItem("Paste");
        //Set styles to the copy button inside the menu
        paste.setForeground(Color.WHITE);
        paste.setBackground(new Color(48, 57, 82));
        paste.setFont(new Font("Times New Roman", Font.BOLD, 16));
        editMenu.add(paste);
        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //paste operation
                // Retrieve the shape from the clipboard
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable transferable = clipboard.getContents(this);

                if (transferable != null && transferable.isDataFlavorSupported(HexagonTransferable.SHAPE_DATA_FLAVOR)) {
                    try {
                        // Retrieve the shape from the clipboard content
                        Shape pastedShape = (Shape) transferable.getTransferData(HexagonTransferable.SHAPE_DATA_FLAVOR);

                        // Create a new copy of the pasted shape
                        AffineTransform at = new AffineTransform();
                        Rectangle bounds = pastedShape.getBounds();
                        int x = 100; // Set the desired x position for the paste operation
                        int y = 100; // Set the desired y position for the paste operation
                        at.translate(x - bounds.x, y - bounds.y);
                        Shape translatedShape = at.createTransformedShape(pastedShape);

                        // Set the translated copy as the current shape
                        hexagonController.setShape(translatedShape);
                        hexagonPanel.repaint();
                    } catch (UnsupportedFlavorException | IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JButton undoBtn = new JButton("Undo");
        // Set the font color and style of the button
        undoBtn.setForeground(Color.WHITE);
        undoBtn.setBackground(new Color(48, 57, 82));
        undoBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        // Remove the border of the button
        undoBtn.setBorderPainted(false);
        menuBar.add(undoBtn);
        undoBtn.addActionListener(e -> {
            if (!undoStack.isEmpty()) {
                redoStack.push(hexagonController.getShape());
                undoStack.pop();
                if (!undoStack.isEmpty()) {
                    hexagonController.setShape(undoStack.peek());
                } else {
                    hexagonController.deleteShape();
                }
                hexagonPanel.repaint();
            } //Performing undo operation on shape.

        });

        JButton redoBtn = new JButton("Redo");
        // Set the font color and style of the button
        redoBtn.setForeground(Color.WHITE);
        redoBtn.setBackground(new Color(48, 57, 82));
        redoBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        // Remove the border of the button
        redoBtn.setBorderPainted(false);
        menuBar.add(redoBtn); //Creating a JButton named redoBtn with the label "Redo", Adding redoBtn to the menu bar
        redoBtn.addActionListener(e -> {
            if (!redoStack.isEmpty()) {
                undoStack.push(hexagonController.getShape());
                hexagonController.setShape(redoStack.pop());
                hexagonPanel.repaint(); // Checking if the redo stack is not empty, Pushing the current shape to the undo stack, Setting the shape to the top of the redo stack
            }
        });

        class RoundedPanel extends JPanel {
            private int arcRadius; // Border radius

            public RoundedPanel(int arcRadius) {
                this.arcRadius = arcRadius;
                setOpaque(false);
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
        // Detail Panel
        RoundedPanel hexagonDetailsPanel = new RoundedPanel(20);
        hexagonDetailsPanel.setLayout(new BoxLayout(hexagonDetailsPanel, BoxLayout.Y_AXIS));
        // Set the background color and border radius of the detailPanel
        hexagonDetailsPanel.setBackground(Color.LIGHT_GRAY);
        hexagonDetailsPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0)); // Adjust the top margin as per your requirement
        add(hexagonDetailsPanel, BorderLayout.EAST);

        // Create an invisible component to push the detailLabel down
        hexagonDetailsPanel.add(Box.createRigidArea(new Dimension(0, 100)));

        // Add detail label
        JLabel detailLabel = new JLabel("<html>Hexagon Details:<br>"
                + "1. A hexagon polygon has 6 sides.<br>"
                + "2. A hexagon polygon has 6 vertices.<br>"
                + "3. All the sides or the edges are equal in length.</html>");
        // Set font size
        detailLabel.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Font name, style, and size
        hexagonDetailsPanel.add(detailLabel);


        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(255, 255, 255));//Set the background color
        controlPanel.setLayout(new GridLayout(0, 2));
        add(controlPanel, BorderLayout.SOUTH); //Creating, setting and adding a JPanel named controlPanel

        JButton createBtn = new JButton("Create the Hexagon");
        //Set style for the create button
        createBtn.setForeground(Color.WHITE);
        createBtn.setBackground(new Color(48, 57, 82));
        createBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel.add(createBtn);
        createBtn.addActionListener(e -> {
            undoStack.push(hexagonController.getShape());
            redoStack.push(hexagonController.getShape());
            hexagonController.createShape();
            hexagonPanel.repaint();
        });

        //Creating, adding a JButton named createBtn with the label "Create", Repainting the hexagonPanel
        JButton deleteBtn = new JButton("Delete the Hexagon");
        //Set style for the create button
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setBackground(new Color(48, 57, 82));
        deleteBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel.add(deleteBtn);
        deleteBtn.addActionListener(e -> {
            undoStack.push(hexagonController.getShape());
            redoStack.push(hexagonController.getShape());
            hexagonController.deleteShape();
            hexagonPanel.repaint();
        }); //Creating and adding a JButton named deleteBtn with the label "Delete",Calling deleteShape method on hexagonController,Repainting the hexagonPanel

        JTextField scaleTf = new JTextField();
        controlPanel.add(scaleTf);
        JButton scaleBtn = new JButton("Scale the Hexagon");
        //Set style for the create button
        scaleBtn.setForeground(Color.WHITE);
        scaleBtn.setBackground(new Color(48, 57, 82));
        scaleBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel.add(scaleBtn); //Creating and adding a JTextField named scaleTf, Creating and adding a JButton named scaleBtn with the label "Scale"

        scaleTf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                newScaleInputProvided = true;
            } //Overriding the insertUpdate method from the DocumentListener interface, Setting the newScaleInputProvided flag to true when an insert update event occurs in the associated document

            @Override
            public void removeUpdate(DocumentEvent e) {

                newScaleInputProvided = true;
            } //Setting the newScaleInputProvided flag to true when a remove update event occurs in the associated document

            @Override
            public void changedUpdate(DocumentEvent e) {

                newScaleInputProvided = true;
            } //Setting the newScaleInputProvided flag to true when a change update event occurs in the associated document
        });


        scaleBtn.addActionListener(e -> {   //Adding an action listener to scaleBtn using a lambda expression
            if (newScaleInputProvided) { //Checking if a new scale input was provided
                try {
                    double scale = Double.parseDouble(scaleTf.getText()); //Trying to parse the text from scaleTf into a double
                    if (scale >= 3) {
                        JOptionPane.showMessageDialog(this, "Maximum scale value is 3. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        undoStack.push(hexagonController.getShape());
                        redoStack.push(hexagonController.getShape());
                        hexagonController.scaleShape(scale);
                        hexagonPanel.repaint();
                        newScaleInputProvided = false; //Displaying an error message using JOptionPane if the scale value is invalid
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid scale input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE); //Displaying an error message using JOptionPane if the scale value is invalid
                }//Catching a NumberFormatException and displaying an error message if the scale input is invalid
            }

        });
        // Rotate text field and button
        JTextField rotateTf = new JTextField();
        controlPanel.add(rotateTf); //Creating and adding a JTextField named rotateTf
        JButton rotateBtn = new JButton("Rotate the Hexagon");
        //Set style for the create button
        rotateBtn.setForeground(Color.WHITE);
        rotateBtn.setBackground(new Color(48, 57, 82));
        rotateBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel.add(rotateBtn); //Creating and adding a JButton named rotateBtn with the label "Rotate"
        rotateBtn.addActionListener(e -> { //Adding an action listener to rotateBtn using a lambda expression
            try {
                double angle = Double.parseDouble(rotateTf.getText()); //Trying to parse the text from rotateTf into a double
                undoStack.push(hexagonController.getShape());
                redoStack.push(hexagonController.getShape());
                hexagonController.rotateShape(angle); //Calling rotateShape method on hexagonController with the valid angle value
                hexagonPanel.repaint(); //Repainting the hexagonPanel
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid rotation input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE); //Catching a NumberFormatException and displaying an error message if the rotation input is invalid
            }
        });

        // Width text field and button
        JTextField widthTf = new JTextField();
        controlPanel.add(widthTf); //Creating and adding a JTextField named widthTf
        JButton widthBtn = new JButton("Give Width to Hexagon");
        //Set style for the width button
        widthBtn.setForeground(Color.WHITE);
        widthBtn.setBackground(new Color(48, 57, 82));
        widthBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel.add(widthBtn); //Creating and adding a JButton named widthBtn with the label "Set Width"
        widthBtn.addActionListener(e -> {
            try {
                int width = Integer.parseInt(widthTf.getText());
                if (width > 340) {
                    JOptionPane.showMessageDialog(this, "Maximum width is 340. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; //Displaying an error message using JOptionPane if the width is invalid, Returning from the method if the width is invalid
                }
                undoStack.push(hexagonController.getShape()); //Pushing the current shape from the hexagonController onto the undoStack
                redoStack.push(hexagonController.getShape()); //Pushing the current shape from the hexagonController onto the redoStack
                hexagonController.resizeShape(width, hexagonController.getShape().getBounds().height); //Resizing the shape in the hexagonController to the specified width while maintaining the current height of the shape.
                hexagonPanel.repaint(); //Repainting the hexagonPanel to update the displayed graphics.
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid width input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            } //Catching a NumberFormatException and displaying an error message using JOptionPane if the width input is invalid.
        });

        // Height text field and button
        JTextField heightTf = new JTextField();
        controlPanel.add(heightTf); //Creating and adding a JTextField named heightTf
        JButton heightBtn = new JButton("Give Height to Hexagon");
        //Set style for the height button
        heightBtn.setForeground(Color.WHITE);
        heightBtn.setBackground(new Color(48, 57, 82));
        heightBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel.add(heightBtn); //Creating and adding a JButton named heightBtn with the label "Set Height
        heightBtn.addActionListener(e -> {
            try {
                int height = Integer.parseInt(heightTf.getText());
                if (height > 340) {
                    JOptionPane.showMessageDialog(this, "Maximum height is 340. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; //Displaying an error message using JOptionPane if the height is invalid, Returning from the method if the height is invalid
                }
                undoStack.push(hexagonController.getShape());//Pushing the current shape from the hexagonController onto the undoStack
                redoStack.push(hexagonController.getShape()); //Pushing the current shape from the hexagonController onto the redoStack
                hexagonController.resizeShape(hexagonController.getShape().getBounds().width, height); //Resizing the shape in the hexagonController to maintain the current width while setting the height to the specified value.
                hexagonPanel.repaint(); //Repainting the hexagonPanel to update the displayed graphics
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid height input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            } //Catching a NumberFormatException and displaying an error message using JOptionPane if the height input is invalid.
        });

        // Translate X text field and button
        JTextField translateXTf = new JTextField();
        controlPanel.add(translateXTf); //Creating and adding a JTextField named translateXTf
        JButton translateXBtn = new JButton("Move Right or Left");
        //Set style for the height button
        translateXBtn.setForeground(Color.WHITE);
        translateXBtn.setBackground(new Color(48, 57, 82));
        translateXBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel.add(translateXBtn); //Creating and adding a JButton named translateXBtn with the label "Translate X"
        translateXBtn.addActionListener(e -> {
            try {
                int dx = Integer.parseInt(translateXTf.getText());
                if(dx > 45) {
                    JOptionPane.showMessageDialog(this, "Couldn't Go beyond the 85");
                    return;
                }
                redoStack.push(hexagonController.getShape());
                undoStack.push(hexagonController.getShape());
                hexagonController.translateShape(dx, 0);
                hexagonPanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid translation input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Translate Y text field and button
        JTextField translateYTf = new JTextField();
        controlPanel.add(translateYTf); //Creating and adding a JTextField named translateYTf
        JButton translateYBtn = new JButton("Move Up or Down");
        //Set style for the height button
        translateYBtn.setForeground(Color.WHITE);
        translateYBtn.setBackground(new Color(48, 57, 82));
        translateYBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel.add(translateYBtn); //Creating and adding a JButton named translateYBtn with the label "Translate Y"
        translateYBtn.addActionListener(e -> {
            try {
                int dy = Integer.parseInt(translateYTf.getText());
                if(dy > 45) {
                    JOptionPane.showMessageDialog(this, "Couldn't Go beyond the 85");
                    return;
                }
                redoStack.push(hexagonController.getShape());
                undoStack.push(hexagonController.getShape());
                hexagonController.translateShape(0, dy);
                hexagonPanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid translation input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // create a new JButton for navigate to the polygons
        JButton backBtn = new JButton("Visualize Hexagon in 3D");
        //Set style for the color button
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(48, 57, 82));
        backBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        // add the color chooser JButton to the control panel
        controlPanel.add(backBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HexagonalPrism objHexagonalPrism = new HexagonalPrism();
                objHexagonalPrism.main(new String[0]);

            }
        });

        JButton colorBtn = new JButton("Choose Color to Hexagon");
        //Set style for the color button
        colorBtn.setForeground(Color.WHITE);
        colorBtn.setBackground(new Color(48, 57, 82));
        colorBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel.add(colorBtn); //Creating and adding a JButton named colorBtn with the label "Choose Color"
        colorBtn.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Choose a color", hexagonPanel.getColor());
            if (newColor != null) {
                undoStack.push(hexagonController.getShape());
                redoStack.push(hexagonController.getShape());
                hexagonPanel.setColor(newColor);
                hexagonPanel.repaint();
            }
        }); //Adding an action listener to the colorBtn button. When the button is clicked, a color chooser dialog is displayed, allowing the user to select a color. If a color is selected (not null), the hexagonPanel's color is set to the selected color.
    }
}
