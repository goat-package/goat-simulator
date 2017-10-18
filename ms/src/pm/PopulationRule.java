/**
 * 
 */
package pm;

import java.util.LinkedList;

import org.apache.commons.math3.random.RandomGenerator;

public interface PopulationRule<S,T extends PopulationState<S>> {
	
	/**
	 * 
	 * 
	 * @param r
	 * @param state
	 * @return
	 */
	public LinkedList<PopulationTransition<S,T>> apply( RandomGenerator r , T state );
	
}
