package src;

import src.CustomShapes.CustomShapeFrame;
import src.EquilateralTriangle.EquilateralTriangleFrame;
import src.IsocelesTriangle.IsocelesTriangleFrame;
import src.ObtuseTriangle.ObtuseTriangleFrame;
import src.RightAngledTriangle.RightAngledTriangleFrame;
import src.Scalene.ScaleneFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrianglesScreen extends JFrame{
    private JPanel MainPanel;
    private JPanel SideMenuPanel;
    private JPanel HeaderPanel;
    private JPanel BodyPanel;
    private JButton btnSideMenu;
    private JLabel lblHeader;
    private JButton btnETri;
    private JButton btnRTri;
    private JButton btnITri;
    private JButton btnObTri;
    private JButton btnSTri;
    private JLabel lblETri;
    private JLabel lblRTri;
    private JLabel lblITri;
    private JLabel lblOTri;
    private JLabel lblSTri;
    private JButton btnHome;
    private JButton btn2D;
    private JButton btnRegular;
    private JButton btnCustomShapes;
    private JLabel lblLogo;
    private JLabel lblLogoName;
    private JPanel pnlLogo;

    public TrianglesScreen(){

        //Set Panels colors
        MainPanel.setBackground(new Color(48, 57, 82)); // Dark blue
        SideMenuPanel.setBackground(new Color(34, 40, 49)); // Dark blue-gray
        SideMenuPanel.setPreferredSize(new Dimension(255, SideMenuPanel.getHeight())); // replace '200' with your desired width
        HeaderPanel.setBackground(new Color(48, 57, 82)); // Dark blue
        BodyPanel.setBackground(new Color(48, 57, 82)); // Dark blue
        pnlLogo.setBackground(new Color(34, 40, 49)); // Dark blue-gray

        //Set header styles
        lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblHeader.setForeground(Color.WHITE);

        //Set Name Style
        lblLogoName.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblLogoName.setForeground(Color.WHITE);


        //Set button background color
        btnETri.setBackground(Color.WHITE);
        btnRTri.setBackground(Color.WHITE);
        btnITri.setBackground(Color.WHITE);
        btnObTri.setBackground(Color.WHITE);
        btnSTri.setBackground(Color.WHITE);
        btnHome.setBackground(Color.WHITE);
        btn2D.setBackground(Color.WHITE);
        btnRegular.setBackground(Color.WHITE);
        btnCustomShapes.setBackground(Color.WHITE);

        //Set button text color
        btnETri.setForeground(Color.BLACK);
        btnRTri.setForeground(Color.BLACK);
        btnITri.setForeground(Color.BLACK);
        btnObTri.setForeground(Color.BLACK);
        btnSTri.setForeground(Color.BLACK);
        btnHome.setForeground(Color.BLACK);
        btn2D.setForeground(Color.BLACK);
        btnRegular.setForeground(Color.BLACK);
        btnCustomShapes.setForeground(Color.BLACK);

        btnETri.setText("<html><span style='font-size:16px; font-weight:bold; text-shadow: 1px 1px #000;'>Equilateral Triangle</span></html>");
        btnRTri.setText("<html><span style='font-size:16px; font-weight:bold; text-shadow: 1px 1px #000;'>Right-angled Triangle</span></html>");
        btnITri.setText("<html><span style='font-size:16px; font-weight:bold; text-shadow: 1px 1px #000;'>Isosceles Triangle</span></html>");
        btnObTri.setText("<html><span style='font-size:16px; font-weight:bold; text-shadow: 1px 1px #000;'>Obtuse-angled Triangle </span></html>");
        btnSTri.setText("<html><span style='font-size:16px; font-weight:bold; text-shadow: 1px 1px #000;'>Scalene Triangle</span></html>");


        //Set buttons styles
        btnETri.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnRTri.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnITri.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnObTri.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnSTri.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnHome.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btn2D.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnRegular.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnCustomShapes.setFont(new Font("Times New Roman", Font.BOLD, 16));

        //Set to the home Screen
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeScreen objHomeScreen = new HomeScreen();
                objHomeScreen.setVisible(true);
            }
        });
        //set to the 2D Screen
        btn2D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwoDScreen objTwoDScreen = new TwoDScreen();
                objTwoDScreen.setVisible(true);
            }
        });
        //set to the regular
        btnRegular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polygons objPolygons = new Polygons();
                objPolygons.setVisible(true);
            }
        });

        //set to the Custom Shapes Screen
        btnCustomShapes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomShapeFrame objCustomShapeFrame = new CustomShapeFrame();
                objCustomShapeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objCustomShapeFrame.setVisible(true);
            }
        });
        //set to the equilateral Screen
        btnETri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EquilateralTriangleFrame objEquilateralTriangleFrame = new EquilateralTriangleFrame();
                objEquilateralTriangleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objEquilateralTriangleFrame.setVisible(true);
            }
        });
        //set to the Right-angled Triangle Screen
        btnRTri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RightAngledTriangleFrame objRightAngledTriangleFrame = new RightAngledTriangleFrame();
                objRightAngledTriangleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objRightAngledTriangleFrame.setVisible(true);
            }
        });
        //set to the Isosceles Triangle Screen
        btnITri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IsocelesTriangleFrame objIsoscelesTriangleFrame = new IsocelesTriangleFrame();
                objIsoscelesTriangleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objIsoscelesTriangleFrame.setVisible(true);
            }
        });
        //set to the Obtuse angled Triangle Screen
        btnObTri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ObtuseTriangleFrame objObtuseTriangleFrame = new ObtuseTriangleFrame();
                objObtuseTriangleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objObtuseTriangleFrame.setVisible(true);
            }
        });
        //set to the Scalene Triangle Screen
        btnSTri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScaleneFrame objScaleneFrame = new ScaleneFrame();
                objScaleneFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objScaleneFrame.setVisible(true);
            }
        });

        setContentPane(MainPanel);
        setTitle("ShapeLab");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[]args){
        TrianglesScreen trianglesScreen = new TrianglesScreen();
        trianglesScreen.setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        ImageIcon originalIcon = new ImageIcon("images/equi.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        lblETri = new JLabel(resizedIcon);

        ImageIcon originalIconRight = new ImageIcon("images/right.png");
        Image scaledImageRight = originalIconRight.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH);
        ImageIcon resizedIconRight = new ImageIcon(scaledImageRight);

        lblRTri = new JLabel(resizedIconRight);

        ImageIcon originalIconIsos = new ImageIcon("images/isos.png");
        Image scaledImageIsos = originalIconIsos.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH);
        ImageIcon resizedIconIsos = new ImageIcon(scaledImageIsos);

        lblITri = new JLabel(resizedIconIsos);

        ImageIcon originalIconObtuse = new ImageIcon("images/obtuse.png");
        Image scaledImageObtuse = originalIconObtuse.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH);
        ImageIcon resizedIconObtuse = new ImageIcon(scaledImageObtuse);

        lblOTri = new JLabel(resizedIconObtuse);

        ImageIcon originalIconScalene = new ImageIcon("images/scalene.png");
        Image scaledImageScalene = originalIconScalene.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH);
        ImageIcon resizedIconScalene = new ImageIcon(scaledImageScalene);

        lblSTri = new JLabel(resizedIconScalene);

        ImageIcon originalIconLogo = new ImageIcon("images/logo.png"); // Replace "path/to/your/image.png" with the actual file path of your image
        Image scaledImageLogo = originalIconLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconLogo = new ImageIcon(scaledImageLogo);

        lblLogo = new JLabel(resizedIconLogo);

    }
}
