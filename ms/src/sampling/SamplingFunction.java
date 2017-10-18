
package sampling;

import java.util.LinkedList;

public interface SamplingFunction<S> {

	public void sample(double time, S context);

	public void end(double time);

	public void start();

	public LinkedList<SimulationTimeSeries> getSimulationTimeSeries( int replications );
}
