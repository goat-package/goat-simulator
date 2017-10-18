/**
 * 
 */
package simulator;

import java.util.LinkedList;

import sampling.Measure;

public class AverageMessageInterval implements Measure<AbCSystem> {

	@Override
	public double measure(AbCSystem t) {
		return t.getMessageIntevalStatistics().getMean();
	}

	@Override
	public String getName() {
		return "AVERAGE MESSAGE INTERVAL";
	}

}
