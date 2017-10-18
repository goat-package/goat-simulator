
/**
 * 
 */
package util;

import java.util.List;

public interface WeightedStructure<S> {

	public double getTotalWeight();

	public WeightedElement<S> select(double w);

	public WeightedStructure<S> add(double w, S s);

	public WeightedStructure<S> add(WeightedStructure<S> s);

	public List<WeightedElement<S>> getAll();
	
}
