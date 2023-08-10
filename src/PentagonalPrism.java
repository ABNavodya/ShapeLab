package src;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;
import com.jogamp.opengl.util.FPSAnimator;
import src.Pentagon.PentagonFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PentagonalPrism implements GLEventListener{
    //Create new instance of the GLU because it provides functions to supplement the base OpenGL functionalities.
    private GLU glu = new GLU();
    //private floating variable to get the current(initial or the default) rotation angle of the Hexagonal Prism
    private float rotation = 0.0f;

    // Define the 10 vertices of the pentagonal prism.
    private float[][] vertices = {
            {-1, -1, -1}, // 0
            {1, -1, -1},  // 1
            {2, 0, -1},   // 2
            {1, 1, -1},   // 3
            {-1, 1, -1},  // 4
            {-1, -1, 1},  // 5
            {1, -1, 1},   // 6
            {2, 0, 1},    // 7
            {1, 1, 1},    // 8
            {-1, 1, 1}    // 9
    };
    // this renders the hexagonal prism when each time that the display is updated
    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        //clears the colors and depth buffers for the next set of operations.
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        //this reset the current matrix to the identity matrix done before doing a next transformation (translate, scale,rotation)
        gl.glLoadIdentity();
        //translates the hexagonal Prism along xaxis 0f yaxis as 0f and z axis at -5.0f
        gl.glTranslatef(0f, 0f, -5.0f);
        //scale the cube along x,y,z axis uniformly.
        gl.glScalef(0.7f, 0.7f, 0.7f); // scale down
        //Rotate the hexagonal Prism
        gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);

        // Draw the first the pentagonal prism.
        gl.glBegin(GL2.GL_POLYGON);
        gl.glColor3f(1.0f, 0.0f, 0.0f); // set the color
        //first 5 vertices of the array
        for (int i = 0; i < 5; i++) {
            gl.glVertex3fv(vertices[i], 0);
        }
        //completes the specification of the polygon
        gl.glEnd();
        // Draw the second pentagon face
        gl.glBegin(GL2.GL_POLYGON);
        gl.glColor3f(0.0f, 1.0f, 0.0f); // set color to Green
        //second six vertices of the array which means altogether 10 vertices
        for (int i = 5; i < 10; i++) {
            gl.glVertex3fv(vertices[i], 0);//define the vertex in the 3D and  3fv means three floating values for x,y, and z i refers to the vertices in the array.
        }
        //completes the specification of the polygon
        gl.glEnd();
        //connects the vertices of the top and bottom and draws the 5 rectangular faces of the prism. These faces are drawn as the quadrilaterals.
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // set the color
        for (int i = 0; i < 5; i++) {
            gl.glVertex3fv(vertices[i], 0);// set to the first vertex of the quadrilateral.
            gl.glVertex3fv(vertices[(i + 1) % 5], 0);// set to the second vertex of the quadrilateral.
            gl.glVertex3fv(vertices[(i + 1) % 5 + 5], 0);// set to the third vertex of the quadrilateral
            gl.glVertex3fv(vertices[i + 5], 0);// set to the fourth vertex of the quadrilateral.
        }
        gl.glEnd();// end of the sequence of drawing hexagonal prism
        //checks the drawing commands are executed
        gl.glFlush();
        //increments the rotation angle by increasing 0.6 degrees
        rotation += 0.6f;
    }
    //part of the GLeventListener Interface, this creates when the OpenGl context is about to get destroyed.
    public void dispose(GLAutoDrawable drawable) {
        // method body
    }
    //part of the GLeventListener Interface, this creates when the OpenGl context is about to get initialized.
    @Override
    public void init(GLAutoDrawable drawable) {
        // method body
    }
    //this called when the window is resized
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //gets the GL2 instance from drawable
        final GL2 gl = drawable.getGL().getGL2();
        //In this conditional statement if the screen is minimized it automatically sets to 1.
        if (height <= 0)
            height = 1;
        //calculates the aspect ratio of the window of its height and width
        final float h = (float) width / (float) height;
        //sets the view port of the whole window -> viewport is the area that the cuboid is drawn
        gl.glViewport(0, 0, width, height);
        //switches current matrix to the projection
        gl.glMatrixMode(GL2.GL_PROJECTION);
        //resets the current matrix to the identity matrix
        gl.glLoadIdentity();
        //checks whether how 3D co-ordinates transformed into 2D
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        //switch current matrix mode to the modelview.
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        //sets the current matrix to the identity matrix
        gl.glLoadIdentity();
    }
    public static void main(String[] args){
        //gets an OpenGL profile
        final GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        //create an OpenGlCanvas
        final GLCanvas glCanvas = new GLCanvas(glCapabilities);
        //calling the PentagonalPrism class
        PentagonalPrism pentagonalPrism = new PentagonalPrism();

        //gets the OpenGL events such as display, dispose, init and reshape
        glCanvas.addGLEventListener(pentagonalPrism);
        //sets the canvas size
        glCanvas.setSize(400, 400);

        //creates a Java Frame
        final JFrame frame = new JFrame("3D Pentagonal Prism");
        //create a title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(48, 57, 82));
        // create a title label
        JLabel titleLabel = new JLabel("3D Pentagonal Prism Visualization", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // create a button to the Pentagon  page
        JButton PentagonButton = new JButton("Back");
        PentagonButton.setBackground(new Color(48, 57, 82));
        PentagonButton.setForeground(Color.WHITE);
        PentagonButton.setToolTipText("Back to Pentagon Screen");
        PentagonButton.addActionListener(event -> {
            // set to the pentagon screen
            PentagonFrame objPentagonFrame = new PentagonFrame();
            objPentagonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            objPentagonFrame.setVisible(true);
        });
        //set the hexagon button of the titlePanel to the left side
        titlePanel.add(PentagonButton, BorderLayout.WEST);
        //set the title Panel to the up.
        frame.add(titlePanel, BorderLayout.NORTH);
        //add glCanvas to the frame
        frame.add(glCanvas);
        //set the frame size
        frame.setSize(1000, 700);
        //load the frame to the center when the user runs
        frame.setLocationRelativeTo(null);
        //set frame to be visible
        frame.setVisible(true);
        //creates new FPSAnimator object called "animator".
        final FPSAnimator animator = new FPSAnimator(glCanvas, 200, true);
        //start then animation
        animator.start();
    }
}
