
package sim;

import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.commons.math3.random.RandomGenerator;
import sampling.SamplingFunction;
import sampling.SimulationTimeSeries;
import util.WeightedElement;
import util.WeightedStructure;


public class SimulationEnvironment<S extends ModelI> {

	protected RandomGenerator random;
	private SimulationFactory<S> factory;
	private S model;
	private SamplingFunction<S> sampling_function;
	private int iterations = 0;

	public SimulationEnvironment(SimulationFactory<S> factory) {
		if (factory == null) {
			throw new NullPointerException();
		}
		this.factory = factory;
		this.random = new DefaultRandomGenerator();
	}

	public void seed(long seed) {
		random.setSeed(seed);
	}

	public void setSampling(SamplingFunction<S> sampling_function) {
		this.sampling_function = sampling_function;
	}

	public synchronized void simulate(SimulationMonitor monitor , int iterations, double deadline) {
		RandomGeneratorRegistry rgi = RandomGeneratorRegistry.getInstance();
		rgi.register(random);
		for (int i = 0; (((monitor == null)||(!monitor.isCancelled()))&&(i < iterations)) ; i++) {
			if (monitor != null) {
				monitor.startIteration( i );
			}
			System.out.print('<');
			if ((i + 1) % 50 == 0) {
				System.out.print(i + 1);
			}
			System.out.flush();
			doSimulate(monitor,deadline);
			if (monitor != null) {
				monitor.endSimulation( i );
			}
			System.out.print('>');
			if ((i + 1) % 50 == 0) {
				System.out.print("\n");
			}
			System.out.flush();
			this.iterations++;
		}
		rgi.unregister();		
	}
	
	public synchronized void simulate(int iterations, double deadline) {
		simulate( null , iterations , deadline );
	}

	public synchronized double simulate(double deadline) {
		RandomGeneratorRegistry rgi = RandomGeneratorRegistry.getInstance();
		rgi.register(random);
		double result = doSimulate(deadline);
		rgi.unregister();
		return result;
	}

	private double doSimulate(SimulationMonitor monitor , double deadline) {
		this.model = this.factory.getModel();
		double time = 0.0;
		if (sampling_function != null) {
			sampling_function.start();
			sampling_function.sample(time, model);
		}
		while (((monitor == null)||(!monitor.isCancelled()))&&(time < deadline)) {
			double dt = doAStep(time);
			if (dt <= 0) {
				if (sampling_function != null) {
					sampling_function.end(time);
				}
				return time;
			}
			time += dt;
			this.model.timeStep(dt);
			if (monitor != null && !monitor.isCancelled()) {
				monitor.update(time);
			}
			if (sampling_function != null) {
				sampling_function.sample(time, model);
			}
		}
		
		if (sampling_function != null) {
			sampling_function.end(time);
		}
		return time;	
	}
	private double doSimulate(double deadline) {
		return doSimulate(null,deadline);
	}

	private double doAStep(double time) {
		WeightedStructure<Activity> agents = this.model.getActivities( random );
		double totalRate = agents.getTotalWeight();
		if (totalRate == 0.0) {
			return 0.0;
		}
		double dt = (1.0 / totalRate) * Math.log(1 / (random.nextDouble()));
		double select = random.nextDouble() * totalRate;
		WeightedElement<Activity> wa = agents.select(select);
		if (wa == null) {
			return 0.0;
		}
		wa.getElement().execute(random, time, dt);
		return dt;
	}

	public double nextDouble() {
		return random.nextDouble();
	}

	public int nextInt(int zones) {
		return random.nextInt(zones);
	}

	public LinkedList<SimulationTimeSeries> getTimeSeries( ) {
		if (sampling_function == null) {
			return null;
		}
		return sampling_function.getSimulationTimeSeries( iterations );
	}
}