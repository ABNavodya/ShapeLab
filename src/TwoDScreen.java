package src;
import src.CustomShapes.CustomShapeFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoDScreen extends JFrame {
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JLabel lblHeader;
    private JButton btnShapes;
    private JButton btnPolygons;
    private JLabel lblShapesLabel;
    private JLabel lblPolygonsLabel;
    private JLabel lblLogo;
    private JButton homebtn;
    private JButton btnTriangles;
    private JLabel lblName;
    private JButton btnCustomShapes;

    public TwoDScreen() {

        // Set Panels colors
        mainPanel.setBackground(new Color(48, 57, 82)); // Dark blue
        sidePanel.setBackground(new Color(34, 40, 49)); // Dark blue-gray
        sidePanel.setPreferredSize(new Dimension(255, sidePanel.getHeight())); // replace '200' with your desired width

        headerPanel.setBackground(new Color(48, 57, 82)); // Dark blue
        bodyPanel.setBackground(new Color(48, 57, 82)); // Dark blue

        // Set header styles
        lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblHeader.setForeground(Color.WHITE);

        //Set Name Style
        lblName.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblName.setForeground(Color.WHITE);

        // Set Shapes button label styles
        lblShapesLabel.setFont(new Font("poppins", Font.BOLD, 12));
        lblShapesLabel.setForeground(new Color(176, 196, 222));

        // Set Polygons button label styles
        lblPolygonsLabel.setFont(new Font("poppins", Font.BOLD, 12));
        lblPolygonsLabel.setForeground(new Color(176, 196, 222));

        // Set button background color
        btnShapes.setBackground(Color.WHITE);
        btnPolygons.setBackground(Color.WHITE);
        homebtn.setBackground(Color.WHITE);
        btnTriangles.setBackground(Color.WHITE);
        btnCustomShapes.setBackground(Color.WHITE);

        // Set button text color
        btnShapes.setForeground(Color.BLACK);
        btnPolygons.setForeground(Color.BLACK);
        homebtn.setForeground(Color.BLACK);
        btnTriangles.setForeground(Color.BLACK);
        btnCustomShapes.setForeground(Color.BLACK);

        // Set buttons styles
        btnShapes.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnPolygons.setFont(new Font("Times New Roman", Font.BOLD, 16));
        homebtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnTriangles.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnCustomShapes.setFont(new Font("Times New Roman", Font.BOLD, 16));

        //Navigation to the Irregular Shapes
        btnShapes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shapes objShapes = new Shapes();
                objShapes.setVisible(true);
            }
        });

        //Navigation to the Home Screen
        homebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeScreen objHomeScreen = new HomeScreen();
                objHomeScreen.setVisible(true);
            }
        });

        //Navigation to the Custom Shape Screen
        btnCustomShapes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomShapeFrame objCustomShapeFrame = new CustomShapeFrame();
                objCustomShapeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objCustomShapeFrame.setVisible(true);
            }
        });
        //Navigation to the Triangle Screen
        btnTriangles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Set to the triangle Screen
                TrianglesScreen objTrianglesScreen = new TrianglesScreen();
                objTrianglesScreen.setVisible(true);
            }
        });
        //Navigation to the Regular Shapes
        btnPolygons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polygons objPolygons = new Polygons();
                objPolygons.setVisible(true);
            }
        });

        setContentPane(mainPanel);
        setTitle("ShapeLab");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {

        TwoDScreen twoDScreen = new TwoDScreen();
    }

    private void createUIComponents() {
        ImageIcon logoIcon = new ImageIcon("Images/logo.png"); // Replace "path/to/your/image.png" with the actual file path of your image
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        lblLogo = new JLabel(resizedIcon);
    }
}
