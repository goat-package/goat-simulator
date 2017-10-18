/**
 * 
 */
package simulator;

import java.util.LinkedList;

import sampling.Measure;


public class MinMessageInterval implements Measure<AbCSystem> {

	@Override
	public double measure(AbCSystem t) {
		return t.getMessageIntevalStatistics().getMin();
	}

	@Override
	public String getName() {
		return "MIN MESSAGE INTERVAL";
	}

}
