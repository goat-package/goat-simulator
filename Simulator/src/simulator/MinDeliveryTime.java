/**
 * 
 */
package simulator;

import java.util.LinkedList;

import sampling.Measure;


public class MinDeliveryTime implements Measure<AbCSystem> {

	@Override
	public double measure(AbCSystem t) {
		return t.getMinDeliveryTime();
	}

	@Override
	public String getName() {
		return "MINIMUM DELIVERY TIME";
	}

}
