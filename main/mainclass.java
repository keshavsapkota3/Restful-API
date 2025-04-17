package main;

import robot.Robot;
import lejos.hardware.Sound;

/**
 * MainClass is the entry point for the program.
 * This application controls an EV3 robot's movements using sensor inputs to follow a line, detect obstacles, and adjust its path.
 * It features handler threads for sensor management, allowing smooth navigation while avoiding obstacles.
 * Additionally, it includes functions for setting wheel parameters and playing sounds.
 * 
 * @author Yasmin Ebrahimi
 * @author Rebekka Kankanpaa
 * @author Sebastian Weckstrom
 * @author Axel Petrelius
 * @date 07/03/2024
 */


public class MainClass {
    
    /**
     * The main method of the program.
     * It initializes a robot, plays a sound, and starts the robot.
     * 
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        // Initialize a robot
        Robot robot = new Robot();
        
        // Play a sound
        Sound.twoBeeps();
        
        // Start the robot
        robot.start();
    }
}