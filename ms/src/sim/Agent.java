
/**
 * 
 */
package sim;

import util.WeightedStructure;


public abstract class Agent<S> {

	private String name;

	public Agent() {
		this("Agent");
	}

	public Agent(String name) {
		this.name = name;
	}

	public abstract WeightedStructure<Activity> getActivities(S data);

	@Override
	public String toString() {
		return "<" + name + ">";
	}

}
