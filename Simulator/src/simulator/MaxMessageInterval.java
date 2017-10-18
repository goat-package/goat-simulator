/**
 * 
 */
package simulator;

import java.util.LinkedList;

import sampling.Measure;


public class MaxMessageInterval implements Measure<AbCSystem> {

	@Override
	public double measure(AbCSystem t) {
		return t.getMessageIntevalStatistics().getMax();
	}

	@Override
	public String getName() {
		return "MAX MESSAGE INTERVAL";
	}

}
