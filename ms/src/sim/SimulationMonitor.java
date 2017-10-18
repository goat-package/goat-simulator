/**
 * 
 */
package sim;


public interface SimulationMonitor {

	void startIteration(int i);

	void endSimulation(int i);

	boolean isCancelled();
	
	default void update(double time) { };
}
