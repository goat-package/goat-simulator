/**
 * 
 */
package simulator;

public class SharedCounter {

	private int counter;
	
	public SharedCounter() {
		this.counter = 0;
	}
	
	public int getAndUpdate() {
		return counter++;
	}
	
	public int get() {
		return counter;
	}
	
}
