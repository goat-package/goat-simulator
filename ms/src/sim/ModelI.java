
package sim;

import org.apache.commons.math3.random.RandomGenerator;
import util.WeightedStructure;


public interface ModelI {

	public WeightedStructure<Activity> getActivities( RandomGenerator r );

	public void timeStep(double dt);
	
}
