package src;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;

import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.FPSAnimator;
import src.Circle.CircleFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sphere implements GLEventListener{
    //Create new instance of the GLU because it provides functions to supplement the base OpenGL functionalities.
    private GLU glu = new GLU();
    //private floating variable to get the current(initial or the default) rotation angle of the Pyramid
    private float rotation = 0.0f;
    // this renders the pyramid when each time that the display is updated
    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        //clears the colors and depth buffers for the next set of operations.
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        //this reset the current matrix to the identity matrix done before doing a next transformation (translate, scale,rotation)
        gl.glLoadIdentity();
        //translates the pyramid along xaxis 0f yaxis as 0f and z axis at -5.0f
        gl.glTranslatef(0f, 0f, -5.0f);
        //Rotate the Sphere
        gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
        //creates sphere radius of 1.0 and divided into 50 slices and 50 stacks
        GLUquadric sphere = glu.gluNewQuadric();
        glu.gluSphere(sphere, 1.0, 50, 50);
        //calls to free the functions related to the quadratic object
        glu.gluDeleteQuadric(sphere);
        //checks the drawing commands are executed
        gl.glFlush();
        //increments the rotation angle by increasing 0.6 degrees
        rotation += 0.6f;
    }
    //part of the GLeventListener Interface, this creates when the OpenGl context is about to get destroyed.
    @Override
    public void dispose( GLAutoDrawable drawable ) {

    }
    //part of the GLeventListener Interface, this creates when the OpenGl context is about to get initialized.
    @Override
    public void init( GLAutoDrawable drawable ) {

    }
    //this called when the window is resized
    @Override
    public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {
        //gets the GL2 instance from drawable
        final GL2 gl = drawable.getGL().getGL2();
        //In this conditional statement if the screen is minimized it automatically sets to 1.
        if( height <= 0 )
            height = 1;
        //calculates the aspect ratio of the window of its height and width
        final float h = ( float ) width / ( float ) height;
        //sets the view port of the whole window -> viewport is the area that the cuboid is drawn
        gl.glViewport( 0, 0, width, height );
        //switches current matrix to the projection
        gl.glMatrixMode( GL2.GL_PROJECTION );
        //resets the current matrix to the identity matrix
        gl.glLoadIdentity();
        //checks whether how 3D co-ordinates transformed into 2D
        glu.gluPerspective( 45.0f, h, 1.0, 20.0 );
        //switch current matrix mode to the modelview.
        gl.glMatrixMode( GL2.GL_MODELVIEW );
        //sets the current matrix to the identity matrix
        gl.glLoadIdentity();
    }

    public static void main( String[] args ) {
        //gets an OpenGL profile
        final GLProfile glProfile = GLProfile.get( GLProfile.GL2 );
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);

        //create an OpenGlCanvas
        final GLCanvas glCanvas = new GLCanvas(glCapabilities);
        //calling the Spehere class
        Sphere sphere = new Sphere();
        //gets the OpenGL events such as display, dispose, init and reshape
        glCanvas.addGLEventListener( sphere );
        //sets the canvas size
        glCanvas.setSize( 400, 600 );
        //creates a Java Frame
        final JFrame frame = new JFrame( " 3D cube" );

        //create a title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(48, 57, 82));
        //create a title label
        JLabel titleLabel  = new JLabel("3D Cube Visualization", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel,BorderLayout.CENTER);

        //create a button to the square page
        JButton CircleButton = new JButton("Back");
        CircleButton.setBackground(new Color(48, 57, 82));
        CircleButton.setForeground(Color.WHITE);
        CircleButton.setToolTipText("Back to Circle Screen");
        CircleButton.addActionListener(event ->{
            //set to the  circle screen
            CircleFrame objCircleFrame = new CircleFrame();
            objCircleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            objCircleFrame.setVisible(true);

        });
        titlePanel.add(CircleButton, BorderLayout.WEST);
        frame.add(titlePanel, BorderLayout.NORTH);

        frame.add(glCanvas);
        frame.setSize(1000,700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        final FPSAnimator animator = new FPSAnimator(glCanvas, 200,true);
        animator.start();
    }
}
