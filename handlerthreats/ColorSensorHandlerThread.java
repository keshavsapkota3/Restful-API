package sample.handlerthreads;
 
/**
* Date modified: 29.03.2024
* The purpose of this java file is to create ColorSensorHandlerThread class for Robot.
* 
* @author Sebastian W.
* 
*/
 
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
 
/**
* Description for class ColorSensorHandlerThread
* 
* The purpose of this ColorSensorHandlerThread class is to provide Robot class a thread
* object, that checks continuously whether it is on a line with a color less or more than 0.26.
* 
* 
* The Robot class only has to worry about:
* 
* 1. Creating the handler thread object:
* ColorSensorHandlerThread csht = new ColorSensorHandlerThread();
* 
* 2. Starting the thread:
* csht.start();
* 
* 3. Getting the most recent information about the examined color:
* csht.getRecentSample(); // returns float
* Or:
* csht.isOnLine(); // returns boolean
* (This returns true if the robot is on line, otherwise false.)
* 
* 4. Stopping the infinite thread loop using:
* csht.exit();
*/
public class ColorSensorHandlerThread extends HandlerThread {
	/**
	 * The purpose of this thread class is to handle 
	 */
	// Private constants:
	private final float COLOR_THRESHOLD = 0.26f;
	// Private attributes:
	private EV3ColorSensor colorSensor;
	// sampleProvider replaces colorProvider
	private SampleProvider sampleProvider;
	/* 
	 * I don't know why sample exists and why it's an array.
	 * I'm keeping it for now, but using recentSample instead (same as sample[0]).
	*/
	private float[] sample;
	private float recentSample = 0;

	// Constructors:
	public ColorSensorHandlerThread() {
		this.colorSensor = new EV3ColorSensor(SensorPort.S2);
		this.sampleProvider = this.colorSensor.getMode("Red");
		this.sample = new float[sampleProvider.sampleSize()];
		this.recentSample = this.sample[0];
	}

	// Getters:
	public float[] getSample() {
		return this.sample;
	}
	public float getRecentSample() {
		return this.recentSample;
	}
	public float getColorThreshold() {
		return this.COLOR_THRESHOLD;
	}
	// Special getter:
	public boolean isOnLine() {
		// Checks whether the most recent sample is 
		if (this.recentSample < this.COLOR_THRESHOLD) {
			return true;
		}
		else {
			return false;
		}
	}

	// Thread's special method run:
	public void run() {
		while (this.getExitCondition()) {
			sampleProvider.fetchSample(this.sample, 0);
			this.recentSample = this.sample[0];
		}
	}
}