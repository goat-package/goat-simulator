
package sim;

import java.util.Random;

import org.apache.commons.math3.random.AbstractRandomGenerator;

public class DefaultRandomGenerator extends AbstractRandomGenerator {

	private Random random = new Random();

	@Override
	public void setSeed(long seed) {
		clear();
		random.setSeed(seed);
	}

	@Override
	public double nextDouble() {
		return random.nextDouble();
	}

}
