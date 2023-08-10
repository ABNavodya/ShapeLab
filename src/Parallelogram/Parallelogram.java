package src.Parallelogram;

import javax.swing.*;

// Define the class
public class Parallelogram {

    // The main method of the program
    public static void main(String[] args) {
        // Use SwingUtilities to ensure the GUI runs on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create a new instance of the ParallelogramFrame class
            JFrame frame = new ParallelogramFrame();
            // Set the default close operation of the frame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Set the location of the frame to be centered on the screen
            frame.setLocationRelativeTo(null);
            // Make the frame visible
            frame.setVisible(true);
        });
    }
}
