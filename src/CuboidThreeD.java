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
import src.Rectangle.RectangleFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CuboidThreeD implements GLEventListener{

    //Create new instance of the GLU because it provides functions to supplement the base OpenGL functionalities.
    private GLU glu = new GLU();
    //private floating variable to get the current(initial or the default) rotation angle of the cube
    private float rotation = 0.0f;

    // this renders the cuboid when each time that the display is updated
    @Override
    public void display(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        //clears the colors and depth buffers for the next set of operations.
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        //this reset the current matrix to the identity matrix done before doing a next transformation
        gl.glLoadIdentity();
        //translates the cuboid along yaxis as 0.1f and z axis at -2.0f
        gl.glTranslatef(0f,0.1f,-2.0f);
        //scale the cuboid along y,y,z axis uniformly.
        gl.glScalef(0.5f,0.7f,1.0f);
        //Rotate the cuboid
        gl.glRotatef(rotation, 1.0f,1.0f,1.0f);
        //drawing the quadrilateral
        gl.glBegin(GL2.GL_QUADS);
        //sets the color for the vertices
        gl.glColor3f(0f,0f,1f);


        //Top Quadrilateral
        gl.glVertex3f(0.5f, 0.5f, -0.5f); //Upper Right
        gl.glVertex3f(-0.5f, 0.5f, -0.5f); // Upper Left
        gl.glVertex3f(-0.5f, 0.5f, 0.5f); // Bottom Left
        gl.glVertex3f(0.5f, 0.5f, 0.5f); // Bottom Right
        //Below Quadrilateral
        gl.glColor3f(1f, 0f, 0f); //Red color
        gl.glVertex3f(0.5f, -0.5f, 0.5f); // Upper Right
        gl.glVertex3f(-0.5f, -0.5f, 0.5f); // Upper Left
        gl.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom Left
        gl.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom Right
        //Front Quadrilateral
        gl.glColor3f(0f, 1f, 0f); //Green color
        gl.glVertex3f(0.5f, 0.5f, 0.5f); // Upper Right
        gl.glVertex3f(-0.5f, 0.5f, 0.5f); // Upper Left
        gl.glVertex3f(-0.5f, -0.5f, 0.5f); // Bottom Left
        gl.glVertex3f(0.5f, -0.5f, 0.5f); // Bottom Right
        //Back Quadrilateral
        gl.glColor3f(1f, 1f, 0f); //Yellow
        gl.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom Left
        gl.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom Right
        gl.glVertex3f(-0.5f, 0.5f, -0.5f); // Upper Right
        gl.glVertex3f(0.5f, 0.5f, -0.5f); // Upper Left
        //Left Quadrilateral
        gl.glColor3f(1f, 0f, 1f); //Purple
        gl.glVertex3f(-0.5f, 0.5f, 0.5f); // Upper Right
        gl.glVertex3f(-0.5f, 0.5f, -0.5f); // Upper Left
        gl.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom Left
        gl.glVertex3f(-0.5f, -0.5f, 0.5f); // Bottom Right
        //Right Quadrilateral
        gl.glColor3f(0f, 1f, 1f); //Cyan
        gl.glVertex3f(0.5f, 0.5f, -0.5f); // Upper Right
        gl.glVertex3f(0.5f, 0.5f, 0.5f); // Upper Left
        gl.glVertex3f(0.5f, -0.5f, 0.5f); // Bottom Left
        gl.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom Right

        //ends the drawing of the cube
        gl.glEnd();
        //checks the drawing commands are executed
        gl.glFlush();
        //increments the rotation angle by increasing 0.6 degrees
        rotation += 0.6f;
    }

    //part of the GLeventListener Interface, this creates when the OpenGl context is about to get destroyed.
    @Override
    public void dispose(GLAutoDrawable drawable){

    }

    //part of the GLeventListener Interface, this creates when the OpenGl context is about to get initialized.
    @Override
    public void init(GLAutoDrawable drawable){

    }
    //this called when the window is resized
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){
        //gets the GL2 instance from drawable
        final GL2 gl = drawable.getGL().getGL2();
        //In this conditional statement if the screen is minimized it automatically sets to 1.
        if(height <=0)
            height = 1;
        //calculates the aspect ratio of the window of its height and width
        final float h = (float) width / (float) height;
        //sets the view port of the whole window -> viewport is the area that the cuboid is drawn
        gl.glViewport(0,0,width,height);
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
        //calling the CuboidThreeD class
        CuboidThreeD objCuboidThreeD = new CuboidThreeD();

        //gets the OpenGL events such as display, dispose, init and reshape
        glCanvas.addGLEventListener(objCuboidThreeD);
        //sets the canvas size
        glCanvas.setSize(400,600);

        //creates a Java Frame
        final JFrame frame = new JFrame("3D Cuboid");

        //create a top panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(48,57,82));
        //create a title Label
        JLabel titleLabel = new JLabel("3D Cuboid Visualization",SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD,30));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel,BorderLayout.CENTER);

        //Create a button to the Rectangle Screen
        JButton rectangleButton = new JButton("Back");
        rectangleButton.setBackground(new Color(48,57,82));
        rectangleButton.setForeground(Color.WHITE);
        rectangleButton.setToolTipText("Back to Rectangle Screen");

        //set to the Rectangle Screen
        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RectangleFrame objRecatangleFrame = new RectangleFrame();
                objRecatangleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objRecatangleFrame.setVisible(true);

            }
        });
        //set the rectangle button of the titlePanel to the left side
        titlePanel.add(rectangleButton, BorderLayout.WEST);
        //set the title Panel to the up.
        frame.add(titlePanel, BorderLayout.NORTH);

        //add glCanvas to the frame
        frame.add(glCanvas);
        //set the frame size
        frame.setSize(1000,700);
        //load the frame to the center when the user runs
        frame.setLocationRelativeTo(null);
        //set frame to be visible
        frame.setVisible(true);
        //creates new FPSAnimator object called "animator".
        final FPSAnimator animator = new FPSAnimator(glCanvas,200,true);
        //start then animation
        animator.start();

    }
}
