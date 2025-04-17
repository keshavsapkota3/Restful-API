package sample.handlerthreads;

/**
 * Date modified: 29.03.2024
 * The purpose of this java file is to create UltrasonicSensorHandlerThread class for Robot.
 * 
 * @author Sebastian W.
 * 
 */

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

/**
 * Description for class UltrasonicSensorHandlerThread
 * 
 * The purpose of this UltrasonicSensorHandlerThread class is to provide Robot class a thread
 * object, that checks continuously whether there is an obstacle within 25 cm distance from the
 * ultrasonic sensor.
 * 
 * The Robot class only has to worry about:
 * 
 * 1. Creating the handler thread object:
 * UltrasonicSensorHandlerThread usht = new UltrasonicSensorHandlerThread();
 * 
 * 2. Starting the thread:
 * usht.start();
 * 
 * 3. Getting the most recent information about the obstacle detection:
 * usht.detectsObstacle(); // returns boolean
 * (It returns true, if an obstacle is detected, otherwise it returns false.
 * 
 * 4. Stopping the infinite thread loop using:
 * usht.exit();
 */
public class UltrasonicSensorHandlerThread extends HandlerThread {
	// Maximum distance on which the robot detects object in centimetres.
	private final int DETECTION_DISTANCE = 15;
	
	private EV3UltrasonicSensor ultrasonicSensor;
	private SampleProvider sampleProvider;
	
	private float[] sample;
	private float recentSample;
	
	
	// Constructors:
	public UltrasonicSensorHandlerThread() {
		this.ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S3);
		this.sampleProvider = ultrasonicSensor.getDistanceMode();
		this.sample = new float[this.sampleProvider.sampleSize()];
		this.recentSample = this.sample[0];
	}
	
	
	// Getters:
	public float[] getSample() {
		return this.sample;
	}
	
	public float getRecentSample() {
		return this.recentSample;
	}
	
	public float getDetectionDistance() {
		return this.DETECTION_DISTANCE;
	}
	
	// Special getter
	public boolean detectsObstacle() {
		if (this.recentSample * 100 < this.DETECTION_DISTANCE) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	
	// Thread's special method run:
	public void run() {
		while (this.getExitCondition()) {
			this.sampleProvider.fetchSample(this.sample,  0);
			this.recentSample = this.sample[0];
		}
	}
}