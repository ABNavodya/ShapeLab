package src.ObtuseTriangle;

import javax.swing.*;

// Main method for the ObtuseTriangle program
public class ObtuseTriangle {
    public static void main(String[] args) {
        // Using SwingUtilities to invoke the creation of the frame on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Create a new frame instance of ObtuseTriangleFrame
            JFrame frame = new ObtuseTriangleFrame();
            // Set the default close operation for the frame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Set the location of the frame to the center of the screen
            frame.setLocationRelativeTo(null);
            // Make the frame visible
            frame.setVisible(true);
        });
    }
}
