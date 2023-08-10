package src.CustomShapes;

import src.HomeScreen;
import src.Polygons;
import src.Shapes;
import src.TrianglesScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomShapeToolBar extends JPanel{

    //declare a private variable regarding to the JButton called pencilButton
    private JButton pencilButton;
    //declare a private variable regarding to the JButton called colorButton
    private JButton colorButton;
    //declare a public variable regarding to the JButton called homeButton
    public JButton homeButton;
    //declare a public variable regarding the JButton called polygonButton
    public JButton polygonButton;
    //declare a public variable regarding to the JButton called triangleButton
    public JButton triangleButton;
    //declare a public variable regarding to the JButton called irregular shapes buttons
    public JButton irregularShapesButton;
    //declare a private custom class called "CustomShapeCanvas" with a custom variable called customShapeCanvas
    private  CustomShapeCanvas customShapeCanvas;


    //create a parameterized constructor called "CustomShapeCanvas that assigns a parameter called customShapeCanvas
    public CustomShapeToolBar(CustomShapeCanvas customShapeCanvas){
        //gets the current customShapeCanvas context
        this.customShapeCanvas = customShapeCanvas;

        setLayout(new BorderLayout());

        // Create a new panel for the label
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setBackground(new Color(48, 57, 82));
        JLabel titleLabel = new JLabel("Custom Shapes Screen");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));// Add styles to the title
        titleLabel.setForeground(Color.WHITE);
        labelPanel.add(titleLabel);
        add(labelPanel, BorderLayout.CENTER);

        // Create a new panel for the tool buttons
        JPanel toolPanel = new JPanel();
        pencilButton = createToolButton("Select Pencil", CustomShapeCanvas.Tool.PENCIL);
        pencilButton.setForeground(Color.WHITE);
        pencilButton.setBackground(new Color(18,97,128));
        pencilButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        colorButton = new JButton("Give Colour");
        colorButton.setForeground(Color.WHITE);
        colorButton.setBackground(new Color(18,97,128));
        colorButton.setFont(new Font("Times New Roman", Font.BOLD, 16));

        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(CustomShapeToolBar.this, "Give a Colour", Color.BLACK);
                customShapeCanvas.setCurrentColor(selectedColor);
            }
        });
        toolPanel.add(pencilButton);
        toolPanel.add(colorButton);


        //Create a new Panel to navigate to the Home, polygons and Triangles
        JPanel bottomNavigationPanel = new JPanel();
        bottomNavigationPanel.setBackground(new Color(	250, 249, 246));
        homeButton = new JButton("Home");
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(new Color(48,57,82));
        homeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        homeButton.setToolTipText("Back to Home Screen");
        bottomNavigationPanel.add(homeButton);
        homeButton.addActionListener(e ->{
            //set to home screen
            HomeScreen objHomeScreen = new HomeScreen();
            objHomeScreen.setVisible(true);

        });
        polygonButton = new JButton("Polygons");
        polygonButton.setForeground(Color.WHITE);
        polygonButton.setBackground(new Color(48,57,82));
        polygonButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        polygonButton.setToolTipText("Back to Polygons Screen");
        bottomNavigationPanel.add(polygonButton);
        polygonButton.addActionListener(e ->{
            //set to polygons screen
            Polygons objPolygons = new Polygons();
            objPolygons.setVisible(true);
        });
        triangleButton = new JButton("Triangles");
        triangleButton.setForeground(Color.WHITE);
        triangleButton.setBackground(new Color(48,57,82));
        triangleButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        triangleButton.setToolTipText("Back to Triangles Screen");
        bottomNavigationPanel.add(triangleButton);
        triangleButton.addActionListener(e-> {
            //set to triangles screen
            TrianglesScreen objTrianglesScreen = new TrianglesScreen();
            objTrianglesScreen.setVisible(true);
        });

        irregularShapesButton = new JButton("Irregular Shapes");
        irregularShapesButton.setForeground(Color.WHITE);
        irregularShapesButton.setBackground(new Color(48,57,82));
        irregularShapesButton.setFont(new Font("Times New Roman",Font.BOLD, 16));
        irregularShapesButton.setToolTipText("Back to Irregular Shapes Screen");
        bottomNavigationPanel.add(irregularShapesButton);
        irregularShapesButton.addActionListener(e-> {
            //set to irregular shapes screen
            Shapes objSHapes = new Shapes();
            objSHapes.setVisible(true);
        });

        //combination of the tool panel and the bottomNavigationPanel into a single panel
        JPanel toolNavigPanel = new JPanel(new BorderLayout());
        toolNavigPanel.add(toolPanel,BorderLayout.SOUTH);
        toolNavigPanel.add(bottomNavigationPanel, BorderLayout.NORTH);
        add(toolNavigPanel, BorderLayout.SOUTH);
    }
    private JButton createToolButton(String label, final CustomShapeCanvas.Tool tool) {
        JButton button = new JButton(label);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customShapeCanvas.setCurrentTool(tool);
            }
        });
        return button;
    }
}
