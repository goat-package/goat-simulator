/**
 * 
 */
package simulator;

import java.util.LinkedList;

import sampling.Measure;


public class AverageInputQueueSize implements Measure<AbCSystem> {

	@Override
	public double measure(AbCSystem t) {
		LinkedList<Integer> sizeList = t.getInputQueueSize();
		if (sizeList.isEmpty()) {
			return 0.0;
		}
		double tot = 0.0;
		for (Integer v : sizeList) {
			tot += v;
		}
		return tot/sizeList.size();
	}

	@Override
	public String getName() {
		return "AVERAGE INPUT QUEUE SIZE";
	}

}
