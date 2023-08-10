package src;
import src.Circle.CircleFrame;
import src.CustomShapes.CustomShapeFrame;
import src.Oval.OvalFrame;
import src.SemiCircle.SemiCircleFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Shapes extends JFrame{
    private JPanel jPnlMain;
    private JPanel jPnlHead;
    private JLabel lblShapes;
    private JPanel jPnlBack;
    private JPanel jPnlCircle;
    private JPanel jPnlOval;
    private JPanel jPnlSemi;
    private JButton btnCircle;
    private JButton btnOval;
    private JButton btnSemi;
    private JLabel lblCircle;
    private JPanel jPnlSide;
    private JLabel lblOval;
    private JButton btnSideMenu;
    private JLabel lblSemi;
    private JLabel lblCircleT;
    private JLabel lblSemiT;
    private JLabel lblOvalT;
    private JButton btnTwoD;
    private JButton btnIrregular;
    private JButton btnRegular;
    private JButton btnTriangle;
    private JPanel JPnlLogo;
    private JLabel JLblLogo;
    private JLabel JLblLName;
    private JButton btnHome;
    private JButton btnCustom;


    public Shapes()
    {
        setContentPane(jPnlMain);
        setTitle("Shapes");
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        //Set panel colors
        jPnlMain.setBackground(new Color(48, 57, 82)); // Dark blue
        jPnlSide.setBackground(new Color(34, 40, 49)); // Dark blue-gray
        JPnlLogo.setBackground(new Color(34, 40, 49)); // Dark blue-gray
        jPnlHead.setBackground(new Color(48, 57, 82)); // Dark blue
        jPnlBack.setBackground(new Color(48, 57, 82)); // Dark blue
        jPnlCircle.setBackground(new Color(48, 57, 82)); // Dark blue
        jPnlOval.setBackground(new Color(48, 57, 82)); // Dark blue
        jPnlSemi.setBackground(new Color(48, 57, 82)); // Dark blue

        //set panel size
        //jPnlSide.setPreferredSize(new Dimension(150,jPnlSide.getHeight()));

        //set header style
        lblShapes.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblShapes.setForeground(Color.WHITE);

        //Set Name Style
        JLblLName.setFont(new Font("Times New Roman", Font.BOLD, 30));
        JLblLName.setForeground(Color.WHITE);


        //Set button background color
        btnCircle.setBackground(Color.WHITE);
        btnOval.setBackground(Color.WHITE);
        btnSemi.setBackground(Color.WHITE);
        btnTwoD.setBackground(Color.WHITE);
        btnRegular.setBackground(Color.WHITE);
        btnTriangle.setBackground(Color.WHITE);
        btnHome.setBackground(Color.WHITE);
        btnCustom.setBackground(Color.WHITE);


        //Set button text color
        btnCircle.setForeground(Color.BLACK);
        btnOval.setForeground(Color.BLACK);
        btnSemi.setForeground(Color.BLACK);
        btnTwoD.setForeground(Color.BLACK);
        btnRegular.setForeground(Color.BLACK);
        btnTriangle.setForeground(Color.BLACK);
        btnHome.setForeground(Color.BLACK);
        btnCustom.setForeground(Color.BLACK);

        //Set button text style
        btnCircle.setFont(new Font("Times New Roman", Font.BOLD,16));
        btnOval.setFont(new Font("Times New Roman", Font.BOLD,16));
        btnSemi.setFont(new Font("Times New Roman", Font.BOLD,16));
        btnTwoD.setFont(new Font("Times New Roman", Font.BOLD,16));
        btnRegular.setFont(new Font("Times New Roman", Font.BOLD,16));
        btnTriangle.setFont(new Font("Times New Roman", Font.BOLD,16));
        btnHome.setFont(new Font("Times New Roman", Font.BOLD,16));
        btnCustom.setFont(new Font("Times New Roman", Font.BOLD,16));

        //Navigation to the home Screen
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeScreen objHomeScreen = new HomeScreen();
                objHomeScreen.setVisible(true);
            }
        });
        //Navigation to the 2D Screen
        btnTwoD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwoDScreen objTwoDScreen = new TwoDScreen();
                objTwoDScreen.setVisible(true);

            }
        });
        //Navigation to the Regular Shapes
        btnRegular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polygons objPolygons = new Polygons();
                objPolygons.setVisible(true);
            }
        });
        //Navigation to the Triangle Screen
        btnTriangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TrianglesScreen objTriangleScreen = new TrianglesScreen();
                objTriangleScreen.setVisible(true);

            }
        });
        //Navigation to the Custom Shapes
        btnCustom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomShapeFrame objCustomShapeFrame = new CustomShapeFrame();
                objCustomShapeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objCustomShapeFrame.setVisible(true);

            }
        });

        //Navigation to the Semi Circle Screen
        btnSemi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SemiCircleFrame objSemiCircleFrame = new SemiCircleFrame();
                objSemiCircleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objSemiCircleFrame.setVisible(true);

            }
        });
        //Navigation to the Oval Screen
        btnOval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OvalFrame objOvalFrame = new OvalFrame();
                objOvalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objOvalFrame.setVisible(true);
            }
        });
        //Navigation to the Circle Screen
        btnCircle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CircleFrame objCircleFrame = new CircleFrame();
                objCircleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objCircleFrame.setVisible(true);
            }
        });


        //Set circle label styles
        lblCircleT.setFont(new Font("poppins", Font.BOLD, 12));
        lblCircleT.setForeground(new Color(176, 196, 222));

        //Set semi-circle button label styles
        lblSemiT.setFont(new Font("poppins", Font.BOLD, 12));
        lblSemiT.setForeground(new Color(176,196,222));

        //Set oval/eclipse button label styles
        lblOvalT.setFont(new Font("poppins", Font.BOLD, 12));
        lblOvalT.setForeground(new Color(176,196,222));


    }

    public static void main(String[] args) {
        Shapes myFrame = new Shapes();
        myFrame.setLocationRelativeTo(null);

    }
        private void createUIComponents() {
        // TODO: place custom component creation code here

        ImageIcon originalIcon = new ImageIcon("images/circle.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        lblCircle = new JLabel(resizedIcon);

        ImageIcon originalIconOval = new ImageIcon("images/oval.png");
        Image scaledImageOval = originalIconOval.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIconOval = new ImageIcon(scaledImageOval);

        lblOval = new JLabel(resizedIconOval);

        ImageIcon originalIconSemi = new ImageIcon("images/semic.png");
        Image scaledImageSemi = originalIconSemi.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIconSemi = new ImageIcon(scaledImageSemi);

        lblSemi = new JLabel(resizedIconSemi);

        ImageIcon originalIconLogo = new ImageIcon("images/logo.png");
        Image scaledImageLogo = originalIconLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconLogo = new ImageIcon(scaledImageLogo);

        JLblLogo = new JLabel(resizedIconLogo);

    }

}
