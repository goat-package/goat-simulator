
package sim;

import java.util.LinkedList;

import org.apache.commons.math3.random.RandomGenerator;
import util.WeightedLinkedList;
import util.WeightedStructure;

public class AgentBasedModel<S> implements ModelI {

	protected LinkedList<Agent<S>> agents;
	protected S data;

	public AgentBasedModel(S data) {
		this.data = data;
		this.agents = new LinkedList<Agent<S>>();
	}

	@Override
	public WeightedStructure<Activity> getActivities( RandomGenerator r ) {
		WeightedStructure<Activity> toReturn = new WeightedLinkedList<Activity>();
		for (Agent<S> agent : agents) {
			WeightedStructure<Activity> local = agent.getActivities(data);
			if (local != null) {
				toReturn = toReturn.add(local);
			}
		}
		return toReturn;
	}

	public S getData() {
		return data;
	}

	public void addAgent(Agent<S> agent) {
		this.agents.add(agent);
	}

	public boolean removeAgent(Agent<S> agent) {
		return this.agents.remove(agent);
	}

	@Override
	public void timeStep(double dt) {
	}

}
