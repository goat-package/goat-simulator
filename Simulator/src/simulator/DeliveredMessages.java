/**
 * 
 */
package simulator;

import java.util.LinkedList;

import sampling.Measure;


public class DeliveredMessages implements Measure<AbCSystem> {

	@Override
	public double measure(AbCSystem t) {
		return t.getDeliveredMessages();
	}

	@Override
	public String getName() {
		return "AVERAGE DELIVERY TIME";
	}

}
