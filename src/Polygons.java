package src;

import src.CustomShapes.CustomShapeFrame;
import src.Decagon.DecagonFrame;
import src.Heptagon.HeptagonFrame;
import src.Hexagon.HexagonFrame;
import src.Nonagon.NonagonFrame;
import src.Octagon.OctagonFrame;
import src.Parallelogram.ParallelogramFrame;
import src.Pentagon.PentagonFrame;
import src.Rectangle.RectangleFrame;
import src.Rhombus.RhombusFrame;
import src.Square.SquareFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Polygons extends JFrame {
    private JPanel pnlMain;
    private JPanel pnlSide;
    private JPanel pnlLogo;
    private JLabel lblLogo;
    private JLabel lblName;
    private JButton btnHome;
    private JButton btn2D;
    private JButton btnIrregular;
    private JButton btnCustom;
    private JPanel pnlHeader;
    private JPanel pnlBody;
    private JScrollPane sPnlBody;
    private JPanel pnlButtons;
    private JPanel pnlTriangle;
    private JButton btnTriangles;
    private JButton btnSquare;
    private JButton btnHeptagon;
    private JButton btnDecagon;
    private JButton btnOctagon;
    private JButton btnRhombus;
    private JButton btnNonagon;
    private JButton btnHexagon;
    private JButton btnPentagon;
    private JButton btnRectangle;
    private JButton btnParalellogram;
    private JLabel lblScreenName;
    private JLabel lblTriangle;
    private JLabel lblSquare;
    private JLabel lblRectangle;
    private JLabel lblParalellogram;
    private JLabel lblRhombus;
    private JLabel lblPentagon;
    private JLabel lblHexagon;
    private JLabel lblHeptagon;
    private JLabel lblOctagon;
    private JLabel lblNonagon;
    private JLabel lblDecagon;
    private JPanel pnlRectangle;
    private JPanel pnlSquare;
    private JPanel pnlParalellogram;
    private JPanel pnlRhombus;
    private JPanel pnlPentagon;
    private JPanel pnlHexagon;
    private JPanel pnlHeptagon;
    private JPanel pnlOctagon;
    private JPanel pnlNonagon;
    private JPanel pnlDecagon;


    public Polygons() {
        add(pnlMain);
        setSize(1000, 700);
        setContentPane(pnlMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Set header styles
        lblScreenName.setFont(new Font("Times New Roman",Font.BOLD, 30));
        lblScreenName.setForeground(Color.BLACK);

        //Set Name Style
        lblScreenName.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblScreenName.setForeground(Color.WHITE);

        //Set Name Style
        lblName.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblName.setForeground(Color.WHITE);

        //Set button background color
        btnTriangles.setBackground(Color.WHITE);
        btnOctagon.setBackground(Color.WHITE);
        btnHexagon.setBackground(Color.WHITE);
        btnRhombus.setBackground(Color.WHITE);
        btnRectangle.setBackground(Color.WHITE);
        btnPentagon.setBackground(Color.WHITE);
        btnSquare.setBackground(Color.WHITE);
        btnHeptagon.setBackground(Color.WHITE);
        btnNonagon.setBackground(Color.WHITE);
        btnDecagon.setBackground(Color.WHITE);
        btnParalellogram.setBackground(Color.WHITE);
        btnCustom.setBackground(Color.WHITE);
        btnHome.setBackground(Color.WHITE);
        btn2D.setBackground(Color.WHITE);
        btnIrregular.setBackground(Color.WHITE);


        //set button font color
        btnTriangles.setForeground(Color.BLACK);
        btnOctagon.setForeground(Color.BLACK);
        btnHexagon.setForeground(Color.BLACK);
        btnRhombus.setForeground(Color.BLACK);
        btnRectangle.setForeground(Color.BLACK);
        btnPentagon.setForeground(Color.BLACK);
        btnSquare.setForeground(Color.BLACK);
        btnHeptagon.setForeground(Color.BLACK);
        btnNonagon.setForeground(Color.BLACK);
        btnDecagon.setForeground(Color.BLACK);
        btnParalellogram.setForeground(Color.BLACK);
        btnCustom.setForeground(Color.BLACK);
        btnHome.setForeground(Color.BLACK);
        btn2D.setForeground(Color.BLACK);
        btnIrregular.setForeground(Color.BLACK);

        //Set buttons styles
        btnTriangles.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnTriangles.setPreferredSize(new Dimension(200, 30));
        btnOctagon.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnOctagon.setPreferredSize(new Dimension(200, 30));
        btnHexagon.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnRhombus.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnRhombus.setPreferredSize(new Dimension(200, 30));
        btnRectangle.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnPentagon.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnSquare.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnHeptagon.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnHeptagon.setPreferredSize(new Dimension(200, 30));
        btnNonagon.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnNonagon.setPreferredSize(new Dimension(200, 30));
        btnDecagon.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnDecagon.setPreferredSize(new Dimension(200, 30));
        btnParalellogram.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnCustom.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnHome.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btn2D.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnIrregular.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font


        pnlMain.setBackground(new Color(48, 57, 82)); // Dark blue
        pnlSide.setBackground(new Color(34, 40, 49)); // Dark blue-gray
        pnlSide.setPreferredSize(new Dimension(255, pnlSide.getHeight())); // replace '200' with your desired width
        pnlLogo.setBackground(new Color(34, 40, 49)); // Dark blue-gray
        pnlHeader.setBackground(new Color(48, 57, 82)); // Dark blue
        pnlBody.setBackground(new Color(48, 57, 82)); // Dark blue
        pnlButtons.setBackground(new Color(48, 57, 82)); // Dark blue
        pnlBody.setPreferredSize(new Dimension(700,600));

        //set to the home Screen
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeScreen objHomeScreen = new HomeScreen();
                objHomeScreen.setVisible(true);
            }
        });
        //set to the Two D Screen
        btn2D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwoDScreen objTwoDScreen = new TwoDScreen();
                objTwoDScreen.setVisible(true);
            }
        });
        //set to the Irregular Shapes
        btnIrregular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shapes objShapes = new Shapes();
                objShapes.setVisible(true);
            }
        });
        //set to the Custom Shape
        btnCustom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomShapeFrame objCustomShapeFrame = new CustomShapeFrame();
                objCustomShapeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objCustomShapeFrame.setVisible(true);
            }
        });
        //set to the Triangles Screen
        btnTriangles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TrianglesScreen objTrianglesScreen = new TrianglesScreen();
                objTrianglesScreen.setVisible(true);
            }
        });
        // set to the Square Screen
        btnSquare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SquareFrame objSquareFrame = new SquareFrame();
                objSquareFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objSquareFrame.setVisible(true);
            }
        });
        //set to the rectangle Screen
        btnRectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RectangleFrame objRectangleFrame = new RectangleFrame();
                objRectangleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objRectangleFrame.setVisible(true);
            }
        });
        //set to the Parallelogram Screen
        btnParalellogram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParallelogramFrame objParalellogramFrame = new ParallelogramFrame();
                objParalellogramFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objParalellogramFrame.setVisible(true);
            }
        });
        //set to the Rhombus Screen
        btnRhombus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RhombusFrame objRhombusFrame = new RhombusFrame();
                objRhombusFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objRhombusFrame.setVisible(true);
            }
        });
        //set to the Pentagon Screen
        btnPentagon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PentagonFrame objPentagonFrame = new PentagonFrame();
                objPentagonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objPentagonFrame.setVisible(true);
            }
        });
        //set to the Hexagon Screen
        btnHexagon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HexagonFrame objHexagonFrame = new HexagonFrame();
                objHexagonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objHexagonFrame.setVisible(true);
            }
        });
        //set to the Heptagon Screen
        btnHeptagon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HeptagonFrame objHexagonFrame = new HeptagonFrame();
                objHexagonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objHexagonFrame.setVisible(true);
            }
        });
        //set to the Octagon Screen
        btnOctagon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OctagonFrame objOcatagonFrame = new OctagonFrame();
                objOcatagonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objOcatagonFrame.setVisible(true);
            }
        });
        //set to the Nonagon Screen
        btnNonagon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NonagonFrame objNaonagonFrame = new NonagonFrame();
                objNaonagonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objNaonagonFrame.setVisible(true);
            }
        });
        //set to the Decagon Screen
        btnDecagon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DecagonFrame objDecagonFrame = new DecagonFrame();
                objDecagonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objDecagonFrame.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        Polygons polygons = new Polygons();
        polygons.setVisible(true);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here

        ImageIcon originalIconLogo = new ImageIcon("images/logo.png");
        Image scaledImageLogo = originalIconLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconLogo = new ImageIcon(scaledImageLogo);

        lblLogo = new JLabel(resizedIconLogo);

        ImageIcon originalIconRhombus = new ImageIcon("images/Rhombus.png");
        Image scaledImageRhombus = originalIconRhombus.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconRhombus = new ImageIcon(scaledImageRhombus);

        lblRhombus = new JLabel(resizedIconRhombus);

        ImageIcon originalIconTriangles = new ImageIcon("images/triangle.png");
        Image scaledImageTriangles = originalIconTriangles.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconTriangles = new ImageIcon(scaledImageTriangles);

        lblTriangle = new JLabel(resizedIconTriangles);

        ImageIcon originalIconSquare = new ImageIcon("images/Square.png");
        Image scaledImageSquare = originalIconSquare.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconSquare = new ImageIcon(scaledImageSquare);

        lblSquare = new JLabel(resizedIconSquare);

        ImageIcon originalIconRectangle = new ImageIcon("images/Rectangle.png");
        Image scaledImageRectangle = originalIconRectangle.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconRectangle = new ImageIcon(scaledImageRectangle);

        lblRectangle = new JLabel(resizedIconRectangle);

        ImageIcon originalIconParalellogram = new ImageIcon("images/Parallelogram.png");
        Image scaledImageParalellogram = originalIconParalellogram.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconParalellogram = new ImageIcon(scaledImageParalellogram);

        lblParalellogram = new JLabel(resizedIconParalellogram);

        ImageIcon originalIconPentagon = new ImageIcon("images/Pentagon.png");
        Image scaledImagePentagon = originalIconPentagon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconPentagon = new ImageIcon(scaledImagePentagon);

        lblPentagon = new JLabel(resizedIconPentagon);

        ImageIcon originalIconHexagon = new ImageIcon("images/Hexagon.png");
        Image scaledImageHexagon = originalIconHexagon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconHexagon = new ImageIcon(scaledImageHexagon);

        lblHexagon = new JLabel(resizedIconHexagon);

        ImageIcon originalIconHeptagon = new ImageIcon("images/Heptagon.png");
        Image scaledImageHeptagon = originalIconHeptagon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconHeptagon = new ImageIcon(scaledImageHeptagon);

        lblHeptagon = new JLabel(resizedIconHeptagon);

        ImageIcon originalIconOctagon = new ImageIcon("images/octagon.png");
        Image scaledImageOctagon = originalIconOctagon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconOctagon = new ImageIcon(scaledImageOctagon);

        lblOctagon = new JLabel(resizedIconOctagon);

        ImageIcon originalIconNonagon = new ImageIcon("images/Nonagon.png");
        Image scaledImageNonagon = originalIconNonagon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconNonagon = new ImageIcon(scaledImageNonagon);

        lblNonagon = new JLabel(resizedIconNonagon);

        ImageIcon originalIconDecagon = new ImageIcon("images/Decagon.png");
        Image scaledImageDecagon = originalIconDecagon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconDecagon = new ImageIcon(scaledImageDecagon);

        lblDecagon = new JLabel(resizedIconDecagon);
    }
}
