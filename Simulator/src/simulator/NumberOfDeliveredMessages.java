/**
 * 
 */
package simulator;

import java.util.LinkedList;

import sampling.Measure;

public class NumberOfDeliveredMessages implements Measure<AbCSystem> {

	@Override
	public double measure(AbCSystem t) {
		return t.getDeliveryTime().size();
	}

	@Override
	public String getName() {
		return "NUMBER OF DELIVERED MESSAGES";
	}

}
