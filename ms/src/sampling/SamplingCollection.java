
package sampling;

import java.util.LinkedList;


public class SamplingCollection<S> implements SamplingFunction<S> {

	private LinkedList<SamplingFunction<S>> functions;

	public SamplingCollection() {
		this.functions = new LinkedList<SamplingFunction<S>>();
	}

	@SafeVarargs
	public SamplingCollection(SamplingFunction<S>... functions) {
		this.functions = new LinkedList<SamplingFunction<S>>();
		for (SamplingFunction<S> f : functions) {
			this.functions.add(f);
		}
	}

	public void addSamplingFunction(SamplingFunction<S> function) {
		functions.add(function);
	}

	@Override
	public void sample(double time, S context) {
		for (SamplingFunction<S> f : functions) {
			f.sample(time, context);
		}
	}

	@Override
	public void end(double time) {
		for (SamplingFunction<S> f : functions) {
			f.end(time);
		}
	}

	@Override
	public void start() {
		for (SamplingFunction<S> f : functions) {
			f.start();
		}
	}
	
	public int size(){
		return this.functions.size();
	}
	
	public SamplingFunction<S> get(int i){
		return this.functions.get(i);
	}

	@Override
	public LinkedList<SimulationTimeSeries> getSimulationTimeSeries( int replications ) {
		LinkedList<SimulationTimeSeries> toReturn = new LinkedList<>();
		for (SamplingFunction<S> f : functions) {
			toReturn.addAll(f.getSimulationTimeSeries( replications ));
		}
		return toReturn;
	}

}
