package src.Square;

import src.CubeThreeD;
import src.CuboidThreeD;
import src.Polygons;
import src.Shapes;

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

public class SquareFrame extends JFrame {

    private SquareController squareController;
    private SquarePanel squarePanel;

    private Stack<Shape> undoStack = new Stack<>();
    private Stack<Shape> redoStack = new Stack<>();

    private boolean newScaleInputProvided = false;

    public SquareFrame(){

        setTitle("Square Screen");
        setSize(1000, 700);
        setLocationRelativeTo(null);

        //create  objectClass
        squareController = new SquareController();
        squarePanel = new SquarePanel(squareController);
        squarePanel.setPreferredSize(new Dimension(600, 600));
        add(squarePanel, BorderLayout.CENTER);

        //Menu Bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(48, 57, 82)); // Set the background color of the menu bar
        setJMenuBar(menuBar);

        // Create topPanel with BorderLayout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(255, 255, 255));

        // Add backBtn to the left (west) of the topPanel
        JButton backBtn = new JButton("Back");

        //Set style for the rotate button
        backBtn.setForeground(Color.BLACK);
        backBtn.setBackground(new Color(255, 255, 255));
        backBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        // Remove the border of the button
        backBtn.setBorderPainted(false);

        backBtn.setToolTipText("Back to Polygons"); // Set tool tip text which will display on hover

        //set to the Regular Shapes Screen
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polygons objPolygons = new Polygons();
                objPolygons.setVisible(true);
            }
        });

        topPanel.add(backBtn, BorderLayout.WEST);
        //backBtn.setBounds();

        // Add pageTitle to the center of the topPanel
        JLabel pageTitle = new JLabel("Square", SwingConstants.CENTER); // Center the text in the JLabel
        pageTitle.setFont(new Font("Times New Roman", Font.BOLD, 30)); // Add styles to the title
        pageTitle.setForeground(Color.BLACK); // Set title color
        topPanel.add(pageTitle, BorderLayout.CENTER); // Add the label to the center of the top panel

        add(topPanel, BorderLayout.NORTH); // Add topPanel to the north of the frame

        //MenuBar content
        JButton saveBtn = new JButton("Save");

        // Set the font color and style of the button
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setBackground(new Color(48, 57, 82));
        saveBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        // Remove the border of the button
        saveBtn.setBorderPainted(false);

        menuBar.add(saveBtn);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shape shape = squareController.getShape();
                // Create a new buffered image with the size of the shape
                BufferedImage image = new BufferedImage(squarePanel.getWidth(), squarePanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = image.createGraphics();
                // Make sure the transformation is set to the top left corner of the shape
                g2.translate(-shape.getBounds().x, -shape.getBounds().y);
                squarePanel.paint(g2);
                g2.dispose();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");
                int userSelection = fileChooser.showSaveDialog(SquareFrame.this);

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
        JMenu editMenu = new JMenu("Edit");

        //Set styles to the edit menu
        editMenu.setForeground(Color.WHITE);
        editMenu.setBackground(new Color(48, 57, 82));
        editMenu.setFont(new Font("Times New Roman", Font.BOLD, 16));

        menuBar.add(editMenu);

        //Copy
        JMenuItem copy = new JMenuItem("Copy");

        //Set styles to the copy button inside the menu
        copy.setForeground(Color.WHITE);
        copy.setBackground(new Color(48, 57, 82));
        copy.setFont(new Font("Times New Roman", Font.BOLD, 16));

        editMenu.add(copy);
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //copy operation
                if (squareController.getShape() != null) {
                    // Create a copy of the current shape
                    Shape copiedShape = (Shape) squareController.copyShape(); //need to implement the clone method in the Shape class
                    // Add the copy to the system clipboard
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    Transferable transferable = new SquareTransferable(copiedShape);
                    clipboard.setContents(transferable, null);
                }
            }
        });

        //Cut
        JMenuItem cut = new JMenuItem("Cut");

        //Set styles to the cut button inside the menu
        cut.setForeground(Color.WHITE);
        cut.setBackground(new Color(48, 57, 82));
        cut.setFont(new Font("Times New Roman", Font.BOLD, 16));

        editMenu.add(cut);
        cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cut operation
                if (squareController.getShape() != null) {
                    undoStack.push(squareController.getShape());
                    redoStack.push(squareController.getShape());

                    // Store the cut shape temporarily
                    Shape cutShape = squareController.getShape();

                    squareController.deleteShape();
                    squarePanel.repaint();

                    // Set the cut shape as the clipboard content
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    Transferable transferable = new SquareTransferable(cutShape);
                    clipboard.setContents(transferable, null);
                }
            }
        });

        //paste
        JMenuItem paste = new JMenuItem("Paste");

        //Set styles to the paste button inside the menu
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

                if (transferable != null && transferable.isDataFlavorSupported(SquareTransferable.SHAPE_DATA_FLAVOR)) {
                    try {
                        // Retrieve the shape from the clipboard content
                        Shape pastedShape = (Shape) transferable.getTransferData(SquareTransferable.SHAPE_DATA_FLAVOR);

                        // Create a new copy of the pasted shape
                        AffineTransform at = new AffineTransform();
                        Rectangle bounds = pastedShape.getBounds();
                        int x = 100; // Set the desired x position for the paste operation
                        int y = 100; // Set the desired y position for the paste operation
                        at.translate(x - bounds.x, y - bounds.y);
                        Shape translatedShape = at.createTransformedShape(pastedShape);

                        // Set the translated copy as the current shape
                        squareController.setShape(translatedShape);
                        squarePanel.repaint();
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
                redoStack.push(squareController.getShape());
                undoStack.pop();
                if (!undoStack.isEmpty()) {
                    squareController.setShape(undoStack.peek());
                } else {
                    squareController.deleteShape();
                }
                squarePanel.repaint();
            }

        });

        JButton redoBtn = new JButton("Redo");

        // Set the font color and style of the button
        redoBtn.setForeground(Color.WHITE);
        redoBtn.setBackground(new Color(48, 57, 82));
        redoBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        // Remove the border of the button
        redoBtn.setBorderPainted(false);

        menuBar.add(redoBtn);
        redoBtn.addActionListener(e -> {
            if (!redoStack.isEmpty()) {
                undoStack.push(squareController.getShape());
                squareController.setShape(redoStack.pop());
                squarePanel.repaint();
            }
        });

        //To make detail panel edges rounded
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
        wrapperPanel.add(Box.createRigidArea(new Dimension(0, 50)));


        // Detail Panel
        JPanel squareDetailPanel = new JPanel();
        squareDetailPanel.setLayout(new BorderLayout());
        squareDetailPanel.setOpaque(false);

        // Add detail label
        JLabel detailLabel = new JLabel("<html>Square Polygon Details:<br>"
                + "1. A Square polygon has 4 sides and 4 vertices.<br>"
                + "2. A Square polygons sides are equal in length.<br></html>");

        // Set font size
        detailLabel.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Font name, style, and size
        squareDetailPanel.add(detailLabel, BorderLayout.CENTER);

        //set font color
        detailLabel.setForeground(new Color(0, 0, 0));

        wrapperPanel.add(squareDetailPanel);
        wrapperPanel.add(Box.createVerticalGlue());

        add(wrapperPanel, BorderLayout.EAST);

        //new panel to create the buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(255, 255, 255));//Set the background color for the control Panel
        controlPanel.setLayout(new GridLayout(0, 2));
        add(controlPanel, BorderLayout.SOUTH);

        //create create button
        JButton createBtn = new JButton("Create Square");

        //Set style for the create button
        createBtn.setForeground(Color.WHITE);
        createBtn.setBackground(new Color(48, 57, 82));
        createBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(createBtn);
        createBtn.addActionListener(e -> {
            undoStack.push(squareController.getShape());
            redoStack.push(squareController.getShape());
            squareController.createShape();
            squarePanel.repaint();
        });

        //create delete button
        JButton deleteBtn = new JButton("Delete Square");

        //Set style for the delete button
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setBackground(new Color(48, 57, 82));
        deleteBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(deleteBtn);
        deleteBtn.addActionListener(e -> {
            undoStack.push(squareController.getShape());
            redoStack.push(squareController.getShape());
            squareController.deleteShape();
            squarePanel.repaint();
        });

        //create scale button
        JTextField scaleTf = new JTextField();
        controlPanel.add(scaleTf);

        //create scale button
        JButton scaleBtn = new JButton("Scale Square");

        //Set style for the scale button
        scaleBtn.setForeground(Color.WHITE);
        scaleBtn.setBackground(new Color(48, 57, 82));
        scaleBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(scaleBtn);

        scaleTf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                newScaleInputProvided = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                newScaleInputProvided = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

                newScaleInputProvided = true;
            }
        });

        scaleBtn.addActionListener(e -> {
            if (newScaleInputProvided) {
                try {
                    double scale = Double.parseDouble(scaleTf.getText());
                    if (scale >= 3) {
                        JOptionPane.showMessageDialog(this, "Maximum scale value is 3. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        undoStack.push(squareController.getShape());
                        redoStack.push(squareController.getShape());
                        squareController.scaleShape(scale);
                        squarePanel.repaint();
                        newScaleInputProvided = false;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid scale input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Rotate text field and button
        JTextField rotateTf = new JTextField();
        controlPanel.add(rotateTf);
        JButton rotateBtn = new JButton("Rotate Square");

        //Set style for the rotate button
        rotateBtn.setForeground(Color.WHITE);
        rotateBtn.setBackground(new Color(48, 57, 82));
        rotateBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(rotateBtn);
        rotateBtn.addActionListener(e -> {
            try {
                double angle = Double.parseDouble(rotateTf.getText());
                redoStack.push(squareController.getShape());
                undoStack.push(squareController.getShape());
                squareController.rotateShape(angle);
                squarePanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid rotation input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Width text field and button
        JTextField widthTf = new JTextField();
        controlPanel.add(widthTf);
        JButton widthBtn = new JButton("Give Width to Square");

        //Set style for the width button
        widthBtn.setForeground(Color.WHITE);
        widthBtn.setBackground(new Color(48, 57, 82));
        widthBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(widthBtn);
        widthBtn.addActionListener(e -> {
            try {
                int width = Integer.parseInt(widthTf.getText());
                if (width > 340) {
                    JOptionPane.showMessageDialog(this, "Maximum width is 340. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                undoStack.push(squareController.getShape());
                redoStack.push(squareController.getShape());
                squareController.resizeShape(width, squareController.getShape().getBounds().height);
                squarePanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid width input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Height text field and button
        JTextField heightTf = new JTextField();
        controlPanel.add(heightTf);
        JButton heightBtn = new JButton("Give Height to Square");

        //Set style for the height button
        heightBtn.setForeground(Color.WHITE);
        heightBtn.setBackground(new Color(48, 57, 82));
        heightBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(heightBtn);
        heightBtn.addActionListener(e -> {
            try {
                int height = Integer.parseInt(heightTf.getText());
                if (height > 340) {
                    JOptionPane.showMessageDialog(this, "Maximum height is 340. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                undoStack.push(squareController.getShape());
                redoStack.push(squareController.getShape());
                squareController.resizeShape(squareController.getShape().getBounds().width, height);
                squarePanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid height input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Translate/move left or right text field and button
        JTextField translateXTf = new JTextField();
        controlPanel.add(translateXTf);
        JButton translateXBtn = new JButton("Move Left or Right the Square");

        //Set style for the translate left or right button
        translateXBtn.setForeground(Color.WHITE);
        translateXBtn.setBackground(new Color(48, 57, 82));
        translateXBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(translateXBtn);
        translateXBtn.addActionListener(e -> {
            try {
                int dx = Integer.parseInt(translateXTf.getText());
                if(dx >= 45) {
                    JOptionPane.showMessageDialog(this, "Couldn't Go beyond 45");
                    return;
                }
                redoStack.push(squareController.getShape());
                undoStack.push(squareController.getShape());
                squareController.translateShape(dx, 0);
                squarePanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid translation input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Translate/move up or down text field and button
        JTextField translateYTf = new JTextField();
        controlPanel.add(translateYTf);
        JButton translateYBtn = new JButton("Move Up or Down the Square");

        //Set style for the translate up or down button
        translateYBtn.setForeground(Color.WHITE);
        translateYBtn.setBackground(new Color(48, 57, 82));
        translateYBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(translateYBtn);
        translateYBtn.addActionListener(e -> {
            try {
                int dy = Integer.parseInt(translateYTf.getText());
                if(dy >= 45) {
                    JOptionPane.showMessageDialog(this, "Couldn't Go beyond 45");
                    return;
                }
                redoStack.push(squareController.getShape());
                undoStack.push(squareController.getShape());
                squareController.translateShape(0, dy);
                squarePanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid translation input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // create a new JButton to visualize Square in 3D
        JButton visualizeBtn = new JButton("Visualize Square in 3D ");
        //Set style for the color button
        visualizeBtn.setForeground(Color.WHITE);
        visualizeBtn.setBackground(new Color(48, 57, 82));
        visualizeBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        // add the color chooser JButton to the control panel
        controlPanel.add(visualizeBtn);

        visualizeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CubeThreeD objCubeThreeD = new CubeThreeD();
                objCubeThreeD.main(new String[0]);
            }
        });

        JButton colorBtn = new JButton("Choose Color to the Square");

        //Set style for the color button
        colorBtn.setForeground(Color.WHITE);
        colorBtn.setBackground(new Color(48, 57, 82));
        colorBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));

        controlPanel.add(colorBtn);
        colorBtn.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Choose a color", squarePanel.getColor());
            if (newColor != null) {
                redoStack.push(squareController.getShape());
                undoStack.push(squareController.getShape());
                squarePanel.setColor(newColor);
                squarePanel.repaint();
            }
        });

    }
}
