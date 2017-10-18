
package sampling;

import java.util.LinkedList;

public class SamplingLog<S> implements SamplingFunction<S> {

	private double dt;
	private double last_time = 0.0;

	public SamplingLog(double dt) {
		this.dt = dt;
	}

	@Override
	public void sample(double time, S context) {
		while (time >= last_time) {
			System.out.println(last_time + ": " + context.toString());
			this.last_time += dt;
		}
	}

	@Override
	public void end(double time) {
		System.out.println(time + ": END");
	}

	@Override
	public void start() {
		this.last_time = 0.0;
	}

	@Override
	public LinkedList<SimulationTimeSeries> getSimulationTimeSeries( int replications ) {
		return new LinkedList<>();
	}

}
