/**
 * 
 */
package simulator;

import java.util.LinkedList;

import sampling.Measure;


public class TravellingMessages implements Measure<AbCSystem> {

	@Override
	public double measure(AbCSystem t) {
		return t.getSendingTime().size();
	}

	@Override
	public String getName() {
		return "NUMBER OF DELIVERED MESSAGES";
	}

}
