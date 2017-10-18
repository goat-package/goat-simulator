
package ds;

import org.apache.commons.math3.random.RandomGenerator;
import sim.Activity;
import ds.TupleSpace.Node;

public class GetActivity implements Activity {

	private Node node;

	public GetActivity(Node node) {
		this.node = node;
	}

	public Tuple getTuple() {
		return node.t;
	}

	@Override
	public boolean execute(RandomGenerator r, double starting_time, double duration) {
		if (node.occurrences <= 0) {
			return false;
		}
		node.occurrences--;
		return true;
	}

	@Override
	public String getName() {
		return "";
	}

}
