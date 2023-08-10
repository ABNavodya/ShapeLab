package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame{
    private JPanel MainPanel;
    private JPanel HeaderPanel;
    private JPanel BodyPanel;
    private JLabel lblHeader;
    private JLabel lblLogo;
    private JButton btnTwoD;
    private JLabel lbl2dText;

    //Constructor Created
    public HomeScreen(){

        //Set Panels colors
        MainPanel.setBackground(new Color(48, 57, 82)); // Dark blue
        BodyPanel.setBackground(new Color(48, 57, 82));//Dark blue
        HeaderPanel.setBackground(new Color(48, 57, 82)); // Dark blue

        //Set header styles
        lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblHeader.setForeground(Color.WHITE);

        //Set button background color
        btnTwoD.setBackground(Color.WHITE);
        btnTwoD.setPreferredSize(new Dimension(150, 30));

        //Set text label
        lbl2dText.setFont(new Font("poppins", Font.BOLD, 12));
        lbl2dText.setForeground(new Color(176, 196, 222));


        //Set button text color
        btnTwoD.setForeground(Color.BLACK);


        //Set buttons styles
        btnTwoD.setFont(new Font("Times New Roman", Font.BOLD, 16)); // set the font
        btnTwoD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwoDScreen objTwoDScreen = new TwoDScreen();
                objTwoDScreen.setVisible(true);
            }
        });

        setContentPane(MainPanel);
        setTitle("ShapeLab");
        setSize(1000,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);


        lblHeader.setPreferredSize(new Dimension(200, lblHeader.getHeight()));//Adjust the width of a label
    }


    public static void main(String[]args){

        HomeScreen homescreen = new HomeScreen();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        ImageIcon originalIconLogo = new ImageIcon("images/logo.png");
        Image scaledImageLogo = originalIconLogo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Adjust the width and height as per your requirements
        ImageIcon resizedIconLogo = new ImageIcon(scaledImageLogo);

        lblLogo = new JLabel(resizedIconLogo);

    }
}
