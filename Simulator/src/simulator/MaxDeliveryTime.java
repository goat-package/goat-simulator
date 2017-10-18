/**
 * 
 */
package simulator;

import java.util.LinkedList;

import sampling.Measure;

public class MaxDeliveryTime implements Measure<AbCSystem> {

	@Override
	public double measure(AbCSystem t) {
		return t.getMaxDeliveryTime();
	}

	@Override
	public String getName() {
		return "MAXIMUM DELIVERY TIME";
	}

}
