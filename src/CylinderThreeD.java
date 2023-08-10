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
import src.Circle.CircleFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CylinderThreeD implements GLEventListener{
    //Create new instance of the GLU because it provides functions to supplement the base OpenGL functionalities.
    private GLU glu = new GLU();
    //private floating variable to get the current(initial or the default) rotation angle of the cylinder
    private float rotation = 0.0f;

    // this renders the cube when each time that the display is updated
    @Override
    public void display(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        //clears the colors and depth buffers for the next set of operations.
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        //this reset the current matrix to the identity matrix done before doing a next transformation
        gl.glLoadIdentity();

        //scale the cube along x,y,z axis uniformly.
        gl.glScalef(0.4f,0.4f,0.4f);
        //translates the cube along yaxis as 0.1f and z axis at -2.0f
        gl.glTranslatef(0,0.0f,-5.0f);
        //Rotate the cuboid
        gl.glRotatef(rotation, 1.0f,1.0f,1.0f);

        //define radius to the cylinder
        final float radius = 1.0f;
        //define height to the cylinder
        final float height = 1.5f;
        //define slices to the cylinder
        final int slices = 20;
        //coordinates of the cylinder
        float x,y,z;
        //iterates over the number of slices which is 20 and calculates the x and y coordinates of each point base of the cylinder using the radius of the current angle theta
        for (int i=0; i<=slices; i++){
            float theta = (float) (2.0f * Math.PI * (float) i/ (float) slices);
            x = radius * (float) Math.cos(theta);
            y = radius * (float) Math.sin(theta);
            z = height;// height of the cylinder

            //drawing the quadrilateral
            gl.glBegin(GL2.GL_QUADS);
            //sets the color for the vertices
            gl.glColor3f((float) i / slices, 0f, 1f - (float) i / slices);
            //two vertices of the current quad and one at the bottom of the cylinder.
            gl.glVertex3f(x, y, -z);
            //two vertices of the current quad and one at the top of the cylinder.
            gl.glVertex3f(x, y, z);
            //set colors for the vertices
            gl.glColor3f((float) (i + 1) / slices, 0f, 1f - (float) (i + 1) / slices);
            //vertices are calculated to a certain angle around the circle from the first two vertices. this allows the quad  which means the 360/20 = 18 degrees
            gl.glVertex3f(radius * (float) Math.cos(theta + 2.0f * Math.PI / slices), radius * (float) Math.sin(theta + 2.0f * Math.PI / slices), z);
            gl.glVertex3f(radius * (float) Math.cos(theta + 2.0f * Math.PI / slices), radius * (float) Math.sin(theta + 2.0f * Math.PI / slices), -z);
            gl.glEnd();
        }

        //increments the rotation angle by increasing 0.6 degrees
        rotation += 0.6f;
        //checks the drawing commands are executed
        gl.glFlush();
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
        //left clipping plane has -1.0f and the right clipping plane has 1.0f using this the view volume is extending equal to left and right
        //up clipping plane has -1.0f and the down clipping plane has 1.0f using this the view volume is extending equal to up and down
        //near clipping plane has 1.5f and the far clipping plane has 20.0f using this the view volume is extending equal to near and far
        gl.glFrustumf(-1.0f, 1.0f, -1.0f, 1.0f, 1.5f, 20.0f);
        //switch current matrix mode to the modelview.
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        //sets the current matrix to the identity matrix
        gl.glLoadIdentity();
    }
    public static void main(String[] args){

        //gets an OpenGL profile
        final GLProfile  glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        //create an OpenGlCanvas
        final GLCanvas glCanvas = new GLCanvas(glCapabilities);
        //calling the CylinderThreeD class
        CylinderThreeD objCylinderThreeD = new CylinderThreeD();
        //gets the OpenGL events such as display, dispose, init and reshape
        glCanvas.addGLEventListener(objCylinderThreeD);
        //sets the canvas size
        glCanvas.setSize(400,600);
        //creates a Java Frame
        final JFrame frame = new JFrame("3D Cylinder");

        //create a title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(48,57,82));

        //create a title Label
        JLabel titleLabel = new JLabel("3D Cylinder Visualization", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        //create a button to the circle
        JButton circleButton = new JButton("Back");
        circleButton.setBackground(new Color(48, 57,82));
        circleButton.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CircleFrame objCircleFrame = new CircleFrame();
                objCircleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                objCircleFrame.setVisible(true);

            }
        });
        //set the circle button of the titlePanel to the left side
        titlePanel.add(circleButton, BorderLayout.WEST);
        //set the title Panel to the up.
        frame.add(titlePanel,BorderLayout.NORTH);
        //add glCanvas to the frame
        frame.add(glCanvas);
        //set the frame size
        frame.setSize(1000,700);
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
