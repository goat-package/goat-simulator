
package sim;

import org.apache.commons.math3.random.RandomGenerator;


public interface Activity {
	
	public String getName();

	public boolean execute(RandomGenerator r, double starting_time, double duration);

}
