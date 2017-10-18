/**
 * 
 */
package simulator;

import java.util.LinkedList;

import sampling.Measure;

public class AverageDeliveryTime implements Measure<AbCSystem> {

	@Override
	public double measure(AbCSystem t) {
		return t.getAverageDeliveryTime();
	}

	@Override
	public String getName() {
		return "AVERAGE DELIVERY TIME";
	}

}
