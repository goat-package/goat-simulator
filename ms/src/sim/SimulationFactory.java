
package sim;

import sampling.Measure;



public interface SimulationFactory<S extends ModelI> {

	public S getModel();

	public Measure<S> getMeasure( String name );
	
}
