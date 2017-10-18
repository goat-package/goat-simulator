
package util;


public interface Weighter<T> {

	public double weight(T t, int occurrences);

	public double weight(T t);

}
