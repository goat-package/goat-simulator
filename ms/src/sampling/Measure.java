
package sampling;


public interface Measure<S> {

	public double measure(S t);

	public String getName();

}
